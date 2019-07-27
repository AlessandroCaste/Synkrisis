package core.graphModels.exporting;

import core.graphModels.GraphsCollection;
import core.graphModels.verticesAndEdges.TransitionEdge;
import core.graphModels.verticesAndEdges.TransitionVertex;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Prints files required by prism
public class PrismExporter {

    private static Logger logger = Logger.getLogger("Report");

    private static PrismExporter instance;

    private boolean mdp = false;
    private int choices = 0;

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph;
    private String modelName;
    private String propertiesString;
    private HashMap<String,Integer> markerMap;
    private String path;

    private int choice = 0;

    public PrismExporter(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph, String modelName, HashMap<String,Integer> markerMap, String propertiesString) {
        System.out.println("PRISM exporting started");
        this.transitionGraph = new DirectedWeightedPseudograph<>(TransitionEdge.class);
        Graphs.addAllEdges(this.transitionGraph,transitionGraph,transitionGraph.edgeSet());
        this.modelName = modelName;
        // markerMap ordering
        this.markerMap =
                markerMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
        this.propertiesString = propertiesString;
        path = modelName + "/prism/";
        File prismPath = new File(path);
        if(!prismPath.exists()) {
            if(!prismPath.mkdir()) {
                System.out.println("Can't create a new 'prism' directory");
                logger.log(Level.WARNING,"Couldn't create a 'prism' folder for model outputting");
            }
        }

    }

    public void writePrismFiles() {
        normalization();
        checkMDP();
        writeTransitionFile();
        writeLabelFile();
        writeProperties();
    }

    // I check if all edges are unique (= MDP)
    private void checkMDP() {
        // I also track the number of possible choices
        for(TransitionVertex tv : transitionGraph.vertexSet()) {
            ArrayList<TransitionEdge> edges = new ArrayList<>(transitionGraph.outgoingEdgesOf(tv));
            Set<String> set = edges.stream().map(s -> s.getLabel()).collect(Collectors.toSet());
            if (set.size() < edges.size())
                mdp = true;
                //choices += edges.size() - set.size();
        }

    }

    private void writeTransitionFile() {
        System.out.println("Writing the PRISM .tra file");
        logger.log(Level.INFO,"Writing .tra file");
        try {
            BufferedWriter traWriter = new BufferedWriter(new FileWriter(path + modelName + ".tra",false));
            if(!mdp) {
                traWriter.write(transitionGraph.vertexSet().size() + " " + transitionGraph.edgeSet().size() + "\n");
                // DTMC Case
                for(TransitionVertex v : transitionGraph.vertexSet())
                    for(TransitionEdge e : transitionGraph.outgoingEdgesOf(v))
                        traWriter.write(v.getVertexID() + " " + transitionGraph.getEdgeTarget(e).getVertexID() + " " + transitionGraph.getEdgeWeight(e) + "\n");
            } else {
                //TODO Inserire il giusto numero di choices non sembra contare
                traWriter.write(transitionGraph.vertexSet().size() + " 0 " + transitionGraph.edgeSet().size() + "\n");
                ArrayList<ArrayList<MDPReactum>> lists;
                // For each vertex we find the non-deterministic choices
                for(TransitionVertex tv : transitionGraph.vertexSet()) {
                    choice = 0;
                    ArrayList<String> nonDeterminism = new ArrayList<>();
                    ArrayList<String> visitedLabels = new ArrayList<>();
                    lists = new ArrayList<>();
                    for (TransitionEdge te : transitionGraph.outgoingEdgesOf(tv))
                        if (visitedLabels.contains(te.getLabel()))
                            nonDeterminism.add(te.getLabel());
                        else
                            visitedLabels.add(te.getLabel());

                    // We separate reacta produced by non-deterministic reactions and states produced by all other reactions
                    ArrayList<MDPReactum> dReacta = new ArrayList<>();
                    ArrayList<MDPReactum> ndReacta = new ArrayList<>();

                    for (String s : nonDeterminism) {
                        for (TransitionEdge te : transitionGraph.outgoingEdgesOf(tv))
                            if (s.equals(te.getLabel()))
                                ndReacta.add(new MDPReactum(transitionGraph.getEdgeTarget(te).getVertexID(), transitionGraph.getEdgeWeight(te)));
                        if(!ndReacta.isEmpty())
                            lists.add(ndReacta);
                        ndReacta = new ArrayList<>();
                    }
                    for (TransitionEdge te : transitionGraph.outgoingEdgesOf(tv))
                        if (!nonDeterminism.contains(te.getLabel()))
                            dReacta.add(new MDPReactum(transitionGraph.getEdgeTarget(te).getVertexID(), transitionGraph.getEdgeWeight(te)));
                    // We generate permutations of arrival states with a recursive function (basically, a permutation)
                    if(!lists.isEmpty()) {
                        buildPermutations(traWriter, tv.getVertexID(), lists, dReacta, 0, "");
                    }
                    else
                        for(MDPReactum mdpr : dReacta)
                            traWriter.write(tv.getVertexID() + " " + choice + " " + mdpr.reactionToString() + "\n");

                }

            }
            traWriter.close();
            GraphsCollection.getInstance().printPrismTransition(transitionGraph);
        } catch (IOException e) {
            System.out.println("Can't output the transition (.tra) file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private void buildPermutations(BufferedWriter traWriter, int currentID, ArrayList<ArrayList<MDPReactum>> listone, ArrayList<MDPReactum> dReactions, int depth, String current) throws IOException {
        String outputString;
        if (depth == listone.size()) {
                outputString = current;
                for(MDPReactum s : dReactions)
                    outputString += currentID + " " + choice + " " + s.reactionToString() + "\n";
                traWriter.write(outputString);
            choice++;
            return;
        }

        for (int i = 0; i < listone.get(depth).size(); i++) {
            buildPermutations(traWriter,currentID,listone, dReactions, depth + 1, current + (currentID + " " + choice + " " + listone.get(depth).get(i).reactionToString()) + "\n");
        }

    }

    private void writeLabelFile() {
        System.out.println("Writing the PRISM .lab file");
        logger.log(Level.INFO,"Writing .lab file");
        try {
            BufferedWriter labWriter = new BufferedWriter(new FileWriter(path + modelName + ".lab",false));
            // I write by deafult built-in properties
            labWriter.write("0=\"init\" 1=\"deadlock\" ");
            for(String marker : markerMap.keySet())
                labWriter.write(markerMap.get(marker) + "=\"" + marker + "\" ");
            labWriter.write("\n0: 0\n");
            for(TransitionVertex v : transitionGraph.vertexSet())
                if(!v.getPropertiesString().equals(""))
                    labWriter.write(v.getVertexID() + ": " + v.getPropertiesString() + "\n");
            labWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the label (.lab) file!");
            logger.log(Level.SEVERE, "Can't write .lab file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private void writeProperties() {
        if(propertiesString.replaceAll("\\s+","").equals(""))
            System.out.println("No PRISM properties have been specified");
        else {
            System.out.println("Writing the PRISM .prop file");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path + modelName + ".prop"), false));
                writer.write(propertiesString);
                writer.close();
            } catch (IOException e) {
                System.out.println("Can't output the prism property (.prop) file!");
                logger.log(Level.SEVERE, "Error with properties buffer writer. Something's off with the file, possibly authorization\nStack trace " + e.getMessage());
            }
        }
    }

    private void normalization() {
        System.out.println("Normalizing edge weights");
        ArrayList<String> reactionsAlreadyChecked;

        for(TransitionVertex tv : transitionGraph.vertexSet()) {
            reactionsAlreadyChecked = new ArrayList<>();
            float weightSum = 0;
            for (TransitionEdge te : transitionGraph.outgoingEdgesOf(tv)) {
                if (!reactionsAlreadyChecked.contains(te.getLabel())) {
                    reactionsAlreadyChecked.add(te.getLabel());
                    weightSum += transitionGraph.getEdgeWeight(te);
                }
            }
            if(weightSum < 1) {
                double weight = (1 - weightSum);
                weight = (double) Math.round(weight * 1e3) / 1e3;
                TransitionEdge te = new TransitionEdge("loop");
                transitionGraph.addEdge(tv, tv, te);
                transitionGraph.setEdgeWeight(te,weight);
            }
            else if(weightSum > 1) {
                for (TransitionEdge te : transitionGraph.outgoingEdgesOf(tv)) {
                    double weight = transitionGraph.getEdgeWeight(te)/weightSum;
                    weight = Math.round(weight*1e2)/1e2;
                    transitionGraph.setEdgeWeight(te,weight);
                }
            }
            //GraphsCollection.getInstance().printPrismTransition(transitionGraph);
        }
    }

}

