package core.graphs.transitiongraph;

import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TransitionGraph {

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionJgraph;
    private HashMap<String,Integer> markersMap;
    private String modelName;
    private ArrayList<String> reactionNames;

    // Mapping the string ids to integer ids
    private HashMap idMap;

    public TransitionGraph(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionJgraph, String modelName, HashMap<String,Integer> markersMap) {
        this.transitionJgraph = transitionJgraph;
        this.markersMap = markersMap;
        this.modelName = modelName;
        HashSet<String> reactions = new HashSet<>();
        for(TransitionEdge te : transitionJgraph.edgeSet())
            reactions.add(te.getLabel());
        this.reactionNames = new ArrayList<>(reactions);
        createIntegerMap();
    }

    public DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> getTransitionJgraph() {
        return transitionJgraph;
    }

    public HashMap<String, Integer> getMarkersMap() {
        return markersMap;
    }

    public boolean vertexContainsMarker(TransitionVertex tv, String s) {
        if(tv.getProperties()!=null)
            return tv.getProperties().contains(markersMap.get(s));
        else
            return false;
    }

    // I associate to each vertex an integer ID
    private void createIntegerMap(){
        this.idMap = new HashMap<>();
        int counterID = 0;
        for(TransitionVertex tv : transitionJgraph.vertexSet()){
            idMap.put(tv,Integer.toString(counterID));
            counterID++;
        }
    }

    public HashMap<TransitionVertex,String> getIDMap(){
        return idMap;
    }

    public String markerInVertex(TransitionVertex tv){
        StringBuilder sb = new StringBuilder();
        for(String marker : markersMap.keySet()){
            if(tv.getProperties().contains(markersMap.get(marker)))
                sb.append(marker).append(" ");
        }
        return sb.toString();
    }

    public String getModelName() {
        return modelName;
    }

    public ArrayList<String> getReactionNames(){
        return this.reactionNames;
    }

    public int size() {
        return transitionJgraph.vertexSet().size();
    }
}