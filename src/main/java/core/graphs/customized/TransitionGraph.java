package core.graphs.customized;

import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TransitionGraph {

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> graph;
    private HashMap<String,Integer> markersMap;
    private String modelName;
    private ArrayList<String> reactionNames;

    public TransitionGraph(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> graph, String modelName, HashMap<String,Integer> markersMap) {
        this.graph = graph;
        this.markersMap = markersMap;
        this.modelName = modelName;
        HashSet<String> reactions = new HashSet<>();
        for(TransitionEdge te : graph.edgeSet())
            reactions.add(te.getLabel());
        this.reactionNames = new ArrayList<>(reactions);
    }

    public DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> getGraph() {
        return graph;
    }

    public HashMap<String, Integer> getMarkersMap() {
        return markersMap;
    }

    public boolean vertexContainsMarker(TransitionVertex tv, String s) {
        return tv.getProperties().contains(markersMap.get(s));
    }

    public String markersInVertices(TransitionVertex tv){
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
        return graph.vertexSet().size();
    }
}
