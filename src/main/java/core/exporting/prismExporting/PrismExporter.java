package core.exporting.prismExporting;

import core.graphModels.storing.GraphsCollection;
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

    private boolean mdp = false;

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
            Set<String> set = edges.stream().map(TransitionEdge::getLabel).collect(Collectors.toSet());
            if (set.size() < edges.size())
                mdp = true;
        }
    }

    // No string concatenation has been made for better write performance
    private void writeTransitionFile() {
        System.out.println("Writing the PRISM .tra file");
        logger.log(Level.INFO,"Writing .tra file");
        try {
            BufferedWriter traWriter = new BufferedWriter(new FileWriter(path + modelName + ".tra",false));
            if(!mdp) {
                // First line
                traWriter.write(transitionGraph.vertexSet().size());
                traWriter.write(" ");
                traWriter.write(transitionGraph.edgeSet().size());
                traWriter.write("\n");
                // DTMC Case
                for(TransitionVertex v : transitionGraph.vertexSet())
                    for(TransitionEdge e : transitionGraph.outgoingEdgesOf(v)) {
                        // Transitions specification
                        traWriter.write(Integer.toString(v.getVertexID()));
                        traWriter.write(" ");
                        traWriter.write(transitionGraph.getEdgeTarget(e).getVertexID());
                        traWriter.write(" ");
                        traWriter.write(Double.toString(transitionGraph.getEdgeWeight(e)));
                        traWriter.write("\n");
                    }
            } else {
                // Number of choices can be omitted in PRISM -mdp specification, so we avoid an extra loop

                // First line
                traWriter.write(Integer.toString(transitionGraph.vertexSet().size()));
                traWriter.write(" 0 ");
                traWriter.write(Integer.toString(transitionGraph.edgeSet().size()));
                traWriter.write("\n");

                // List of MDP Reacta per non-deterministic reaction
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
                        buildPermutations(traWriter, tv.getVertexID(), lists, dReacta, 0, new ArrayList<>());
                    }
                    else
                        for(MDPReactum mdpr : dReacta)
                            writeDeterministicReacta(traWriter,tv.getVertexID(),mdpr);
                }

            }
            traWriter.close();
            GraphsCollection.getInstance().printPrismTransition(transitionGraph);
        } catch (IOException e) {
            System.out.println("Can't output the transition (.tra) file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private void buildPermutations(BufferedWriter traWriter, int currentID, ArrayList<ArrayList<MDPReactum>> lists, ArrayList<MDPReactum> dReacta, int depth, ArrayList<MDPReactum> ndReacta) throws IOException {
        if (depth == lists.size()) {
            for(MDPReactum mdpr : ndReacta) {
                writeDeterministicReacta(traWriter,currentID,mdpr);
            }
            for(MDPReactum mdpr: dReacta)
                writeDeterministicReacta(traWriter,currentID,mdpr);
            choice++;
            return;
        }
        for (int i = 0; i < lists.get(depth).size(); i++) {
            ArrayList<MDPReactum> ndReactaReset = new ArrayList<>(ndReacta);
            ndReactaReset.add(lists.get(depth).get(i));
            buildPermutations(traWriter,currentID,lists, dReacta, depth + 1, ndReactaReset);
        }
    }

    private void writeDeterministicReacta(BufferedWriter traWriter, int id, MDPReactum mdpr) throws IOException {
        traWriter.write(Integer.toString(id));
        traWriter.write(" ");
        traWriter.write(Integer.toString(choice));
        traWriter.write(" ");
        traWriter.write(mdpr.reactionToString());
        traWriter.write("\n");
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
                if(!v.getPropertiesString().equals("")) {
                    labWriter.write(Integer.toString(v.getVertexID()));
                    labWriter.write(": ");
                    labWriter.write(v.getPropertiesString());
                    labWriter.write("\n");
                }
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
        }
        GraphsCollection.getInstance().printPrismTransition(transitionGraph);
    }

}
