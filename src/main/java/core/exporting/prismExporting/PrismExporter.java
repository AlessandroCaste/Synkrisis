package core.exporting.prismExporting;

import core.graphs.transitiongraph.TransitionEdge;
import core.graphs.transitiongraph.TransitionGraph;
import core.graphs.transitiongraph.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Prints files required by prism
public class PrismExporter {

    private static Logger logger = Logger.getLogger("Report");

    private boolean mdp = false;

    // Importing the transition graph
    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionJgraph;
    private String modelName;
    private HashMap<String,Integer> markersMap;
    private String path;
    private boolean isSuccessful;

    // These maps are required for correct mapping
    private HashMap<TransitionVertex,String> idMap;
    private HashMap<TransitionVertex,TreeSet<Integer>> vertexMarkersMap;

    // Tracking mdp choices
    private int choice = 0;

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
        for(TransitionVertex tv : transitionGraph.getGraph().vertexSet())
            transitionJgraph.addVertex(tv);
        for(TransitionEdge te : transitionGraph.getGraph().edgeSet()){
            TransitionEdge newEdge = new TransitionEdge(te.getLabel());
            transitionJgraph.addEdge(transitionGraph.getGraph().getEdgeSource(te),
                                     transitionGraph.getGraph().getEdgeTarget(te),
                                     newEdge);
            transitionJgraph.setEdgeWeight(newEdge,transitionGraph.getGraph().getEdgeWeight(te));
        }
        this.modelName = transitionGraph.getModelName();
        this.markersMap = new HashMap<>(transitionGraph.getMarkersMap());
        this.path = path;
        this.idMap = new HashMap<>();
        this.vertexMarkersMap = new HashMap<>();
    }

    public boolean translate() {
        isSuccessful = true;
        normalization();
        checkMDP();
        writeTransitionFile();
        writeLabelFile();
        return isSuccessful;
    }

    // I check if all edges are unique (= MDP)
    private void checkMDP() {
        // I also track the number of possible choices
        for(TransitionVertex tv : transitionJgraph.vertexSet()) {
            ArrayList<TransitionEdge> edges = new ArrayList<>(transitionJgraph.outgoingEdgesOf(tv));
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
            BufferedWriter traWriter;
            if(mdp)
                traWriter = new BufferedWriter(new FileWriter(path + "/" + modelName + "_mdp.tra",false));
            else
                traWriter = new BufferedWriter(new FileWriter(path + "/" + modelName + "_dtmc.tra",false));
            if(!mdp) {
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
            } else {
                // Number of choices can be omitted in PRISM -mdp specification, so we avoid an extra loop

                // First line
                traWriter.write(Integer.toString(transitionJgraph.vertexSet().size()));
                traWriter.write(" 0 ");
                traWriter.write(Integer.toString(transitionJgraph.edgeSet().size()));
                traWriter.write("\n");

                // List of MDP Reacta per non-deterministic reaction
                ArrayList<ArrayList<MDPReactum>> lists;
                // For each vertex we find the non-deterministic choices
                for(TransitionVertex tv : transitionJgraph.vertexSet()) {
                    choice = 0;
                    ArrayList<String> nonDeterminism = new ArrayList<>();
                    ArrayList<String> visitedLabels = new ArrayList<>();
                    lists = new ArrayList<>();
                    for (TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv))
                        if (visitedLabels.contains(te.getLabel()))
                            nonDeterminism.add(te.getLabel());
                        else
                            visitedLabels.add(te.getLabel());

                    // We separate reacta produced by non-deterministic reactions and states produced by all other reactions
                    ArrayList<MDPReactum> dReacta = new ArrayList<>();
                    ArrayList<MDPReactum> ndReacta = new ArrayList<>();

                    for (String s : nonDeterminism) {
                        for (TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv))
                            if (s.equals(te.getLabel()))
                                ndReacta.add(new MDPReactum(idMap.get(transitionJgraph.getEdgeTarget(te)), transitionJgraph.getEdgeWeight(te)));
                        if(!ndReacta.isEmpty())
                            lists.add(ndReacta);
                        ndReacta = new ArrayList<>();
                    }
                    for (TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv))
                        if (!nonDeterminism.contains(te.getLabel()))
                            dReacta.add(new MDPReactum(idMap.get(transitionJgraph.getEdgeTarget(te)), transitionJgraph.getEdgeWeight(te)));
                    // We generate permutations of arrival states with a recursive function (basically, a permutation)
                    if(!lists.isEmpty()) {
                        buildPermutations(traWriter, idMap.get(tv), lists, dReacta, 0, new ArrayList<>());
                    }
                    else
                        for(MDPReactum mdpr : dReacta)
                            writeDeterministicReacta(traWriter,idMap.get(tv),mdpr);
                }
            }
            traWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the transition (.tra) file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
            isSuccessful = false;
        }
    }

    private void buildPermutations(BufferedWriter traWriter, String currentID, ArrayList<ArrayList<MDPReactum>> lists, ArrayList<MDPReactum> dReacta, int depth, ArrayList<MDPReactum> ndReacta) throws IOException {
        if (depth == lists.size()) {
            for(MDPReactum mdpr : ndReacta) {
                //TODO DISTINGUERE (mdp) o (dtmc)
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

    private void writeDeterministicReacta(BufferedWriter traWriter, String id, MDPReactum mdpr) throws IOException {
        traWriter.write(id);
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
        normalizeID();
        normalizeMarkers();
        normalizeEdges();
    }

    // I associate to each vertex an integer ID to comply with the transition file
    private void normalizeID(){
        int id = 0;
        for(TransitionVertex tv : transitionJgraph.vertexSet()){
            idMap.put(tv,Integer.toString(id));
            id++;
        }
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
        // Edge Weights
        System.out.println("Normalizing edge weights");
        ArrayList<String> reactionsAlreadyChecked;

        for(TransitionVertex tv : transitionJgraph.vertexSet()) {
            reactionsAlreadyChecked = new ArrayList<>();
            float weightSum = 0;
            for (TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv)) {
                if (!reactionsAlreadyChecked.contains(te.getLabel())) {
                    reactionsAlreadyChecked.add(te.getLabel());
                    weightSum += transitionJgraph.getEdgeWeight(te);
                }
            }
            if(weightSum < 1) {
                double weight = (1 - weightSum);
                weight = (double) Math.round(weight * 1e3) / 1e3;
                TransitionEdge te = new TransitionEdge("loop");
                transitionJgraph.addEdge(tv, tv, te);
                transitionJgraph.setEdgeWeight(te,weight);
            }
            else if(weightSum > 1) {
                for (TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv)) {
                    double weight = transitionJgraph.getEdgeWeight(te)/weightSum;
                    weight = Math.round(weight*1e2)/1e2;
                    transitionJgraph.setEdgeWeight(te,weight);
                }
            }
        }
    }

}

