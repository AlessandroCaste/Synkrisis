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

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph;
    private String modelName;
    private String propertiesString;
    private HashMap<String,Integer> markerMap;
    private String path;

    public PrismExporter(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph, String modelName, HashMap<String,Integer> markerMap, String propertiesString) {
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
        for(TransitionVertex tv : transitionGraph.vertexSet()) {
            ArrayList<TransitionEdge> edges = new ArrayList<>(transitionGraph.outgoingEdgesOf(tv));
            Set<String> set = edges.stream().map(s -> s.getLabel()).collect(Collectors.toSet());
            if (set.size() < edges.size())
                mdp = true;
        }

    }

    private void writeTransitionFile() {
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
                System.out.println("MDP");
            }
            traWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the transition (.tra) file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private void writeLabelFile() {
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
        if(propertiesString != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path + modelName + ".prop"),false));
                writer.write(propertiesString);
                writer.close();
            } catch (IOException e) {
                System.out.println("Can't output the prism property (.prop) file!");
                logger.log(Level.SEVERE, "Error with properties buffer writer. Something's off with the file, possibly authorization\nStack trace " + e.getMessage());
            }
        }
    }

    private void normalization() {
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
            GraphsCollection.getInstance().printPrismTransition(transitionGraph);
        }
    }

}

