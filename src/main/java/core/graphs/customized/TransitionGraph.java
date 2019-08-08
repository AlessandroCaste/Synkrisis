package core.graphs.customized;

import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.util.HashMap;

public class TransitionGraph {

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> graph;
    private HashMap<String,Integer> markersMap;
    private String modelName;

    public TransitionGraph(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> graph, String modelName, HashMap<String,Integer> markersMap) {
        this.graph = graph;
        this.markersMap = markersMap;
        this.modelName = modelName;
    }

    public DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> getGraph() {
        return graph;
    }

    public HashMap<String, Integer> getMarkersMap() {
        return markersMap;
    }

    public String getModelName() {
        return modelName;
    }

    public int size() {
        return graph.vertexSet().size();
    }
}
