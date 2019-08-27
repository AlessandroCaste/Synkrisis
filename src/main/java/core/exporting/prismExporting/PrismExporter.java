package core.exporting.prismExporting;

import core.graphs.transitiongraph.TransitionEdge;
import core.graphs.transitiongraph.TransitionGraph;
import core.graphs.transitiongraph.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Prints files required by prism
public class PrismExporter {

    private static Logger logger = Logger.getLogger("Report");

    // Importing the transition graph
    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionJgraph;
    private String modelName;
    private HashMap<String,Integer> markersMap;
    private String path;
    private boolean isSuccessful;

    // These maps are required for correct mapping
    private HashMap<TransitionVertex,String> idMap;
    private HashMap<TransitionVertex,TreeSet<Integer>> vertexMarkersMap;

    /*
    PRISM exporting requires 3 files.
    This class prints out only the .tra and .lab file. The .prop file is print out by default with Synkrisis behavior.
    During execution the transition jgraph is retrieved and to each vertex are assigned two maps:
     - One links TransitionVertex to unique integer IDs
     - One maps each TransitionVertex to a TreeSet of shifted markers IDs (because 'init' and 'deadlock' are to be taken into account)
     */
    public PrismExporter(TransitionGraph transitionGraph, String path) {
        System.out.println("PRISM exporting started");
        this.transitionJgraph = new DirectedWeightedPseudograph<>(TransitionEdge.class);
        for(TransitionVertex tv : transitionGraph.getTransitionJgraph().vertexSet())
            transitionJgraph.addVertex(tv);
        for(TransitionEdge te : transitionGraph.getTransitionJgraph().edgeSet()){
            TransitionEdge newEdge = new TransitionEdge(te.getLabel());
            transitionJgraph.addEdge(transitionGraph.getTransitionJgraph().getEdgeSource(te),
                                     transitionGraph.getTransitionJgraph().getEdgeTarget(te),
                                     newEdge);
            transitionJgraph.setEdgeWeight(newEdge,transitionGraph.getTransitionJgraph().getEdgeWeight(te));
        }
        this.modelName = transitionGraph.getModelName();
        this.markersMap = new HashMap<>(transitionGraph.getMarkersMap());
        this.path = path;
        this.idMap = transitionGraph.getIDMap();
        this.vertexMarkersMap = new HashMap<>();
    }

    public boolean translate() {
        isSuccessful = true;
        normalization();
        writeTransitionFile();
        writeLabelFile();
        return isSuccessful;
    }

    // No string concatenation has been made for better write performance
    private void writeTransitionFile() {
        System.out.println("Writing the PRISM .tra file");
        logger.log(Level.INFO,"Writing .tra file");
        try {
            BufferedWriter traWriter;
            traWriter = new BufferedWriter(new FileWriter(path + "/" + modelName + "_dtmc.tra",false));
            // First line
            traWriter.write(Integer.toString(transitionJgraph.vertexSet().size()));
            traWriter.write(" ");
            traWriter.write(Integer.toString(transitionJgraph.edgeSet().size()));
            traWriter.write("\n");
            // DTMC Case
            for(TransitionVertex tv : transitionJgraph.vertexSet())
                for(TransitionEdge e : transitionJgraph.outgoingEdgesOf(tv)) {
                    // Transitions specification
                    traWriter.write(idMap.get(tv));
                    traWriter.write(" ");
                    traWriter.write(idMap.get(transitionJgraph.getEdgeTarget(e)));
                    traWriter.write(" ");
                    traWriter.write(Double.toString(transitionJgraph.getEdgeWeight(e)));
                    traWriter.write("\n");
                }
            traWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the transition (.tra) file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
            isSuccessful = false;
        }
    }

    private void writeLabelFile() {
        System.out.println("Writing the PRISM .lab file");
        logger.log(Level.INFO,"Writing .lab file");
        try {
            BufferedWriter labWriter = new BufferedWriter(new FileWriter(path + "/" + modelName + ".lab",false));
            // I write by deafult built-in properties
            labWriter.write("0=\"init\" 1=\"deadlock\" ");
            for(String marker : markersMap.keySet())
                labWriter.write(markersMap.get(marker) + "=\"" + marker + "\" ");
            labWriter.write("\n0: 0\n");
            for(TransitionVertex tv : transitionJgraph.vertexSet()) {

                if (!tv.getProperties().isEmpty()) {
                    labWriter.write(idMap.get(tv));
                    labWriter.write(": ");
                    labWriter.write(vertexMarkersMap.get(tv).toString()
                                                    .replaceAll("\\[","")
                                                    .replaceAll("]","")
                                                    .replaceAll(", "," "));
                    labWriter.write("\n");
                }
            }
            labWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the label (.lab) file!");
            logger.log(Level.SEVERE, "Can't write .lab file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
            isSuccessful = false;
        }
    }

    private void normalization() {
        normalizeMarkers();
        normalizeEdges();
    }

    // I update markers with correct values
    private void normalizeMarkers(){
        // Shifting all markers by 2 since 'init' and 'deadlock' must be added
        for (Map.Entry<String, Integer> entry : markersMap.entrySet())
            entry.setValue(entry.getValue()+2);
        // markers ordering
        this.markersMap =
                markersMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));

        for(TransitionVertex tv : this.transitionJgraph.vertexSet()){
            // Properties are copied and adjusted (increased) in a custom TreeSet
            ArrayList<Integer> conversionList = new ArrayList<>(tv.getProperties());
            for (int i = 0; i < conversionList.size(); i += 1)
                conversionList.set(i, conversionList.get(i) + 2);
            TreeSet<Integer> copiedProperties = new TreeSet<>(conversionList);
            vertexMarkersMap.put(tv,copiedProperties);
        }
    }

    // Normalizing edges probabilities
    private void normalizeEdges(){

        System.out.println("Normalizing edge weights");


        // First, I read probabilities, then normalize based on the overall weights, then normalize based on the single rule
        for(TransitionVertex tv : transitionJgraph.vertexSet()) {

            // Creating the loop edge in case it was needed
            TransitionEdge loop = new TransitionEdge("s-loop");
            ArrayList<TransitionEdge> loopers = new ArrayList<>();
            if (transitionJgraph.containsEdge(tv, tv))
                loopers = new ArrayList<>(transitionJgraph.getAllEdges(tv, tv));

            // Counting the occurences of edges and their weights
            HashMap<String, Integer> rulesOccurrences = new HashMap<>();
            HashMap<String, Double> rulesWeights = new HashMap<>();
            rulesWeights.put("s-loop", 0.0);
            rulesOccurrences.put("s-loop", 1);

            float weightSum = 0;
            for (TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv)) {
                if (!rulesOccurrences.containsKey(te.getLabel())) {
                    rulesOccurrences.put(te.getLabel(), 1);
                    rulesWeights.put(te.getLabel(), transitionJgraph.getEdgeWeight(te));
                } else
                    rulesOccurrences.put(te.getLabel(), rulesOccurrences.get(te.getLabel()) + 1);
                weightSum += transitionJgraph.getEdgeWeight(te);
            }

            if (weightSum < 1) {
                double residualWeight = (1 - weightSum);
                residualWeight = (double) Math.round(residualWeight * 1e3) / 1e3;
                rulesWeights.put("s-loop", residualWeight);
            } else if (weightSum > 1) {
                double maxUniqueReactionsProbability = rulesWeights.values().stream().mapToDouble(i -> i).sum();
                for (Map.Entry<String, Double> e : rulesWeights.entrySet()) {
                    double value = (double) Math.round((e.getValue() / maxUniqueReactionsProbability) * 1e3) / 1e3;
                    e.setValue(value);
                }
            }

            // Normalizing rules that happen multiple times
            rulesWeights.replaceAll((l, v) -> (double) Math.round((rulesWeights.get(l) / rulesOccurrences.get(l))*1e3) / 1e3);
            double finalWeight = 0;
            for (TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv)) {
                transitionJgraph.setEdgeWeight(te, rulesWeights.get(te.getLabel()));
                finalWeight += rulesWeights.get(te.getLabel());
            }

            // Eliminating multiple self-loops
            if (!loopers.isEmpty()) {
                for (TransitionEdge te : loopers) {
                    rulesWeights.put("s-loop", (double) Math.round ((rulesWeights.get("s-loop") + rulesWeights.get(te.getLabel()))*1e3)/1e3);
                    transitionJgraph.removeEdge(te);
                }
            }
            if(rulesWeights.get("s-loop") != 0.0) {
                transitionJgraph.addEdge(tv, tv, loop);
                transitionJgraph.setEdgeWeight(loop, rulesWeights.get("s-loop"));
            }

            // The error is put inside the loop
            if(finalWeight + rulesWeights.get("s-loop") != 1 ) {
                double residualWeight = (1 - finalWeight);
                residualWeight = (double) Math.round(residualWeight * 1e3) / 1e3;
                if(!transitionJgraph.containsEdge(loop))
                    transitionJgraph.addEdge(tv,tv,loop);
                transitionJgraph.setEdgeWeight(loop,transitionJgraph.getEdgeWeight(loop)+residualWeight);
            }
        }
        // To quickly verify if verything's ok
        //new PrintTransition(transitionJgraph,modelName).run();
    }

}

