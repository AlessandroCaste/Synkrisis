package core.graphBuilding;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class GraphReaction {

    private SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> redex;
    private SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> reactum;
    private String rulename;

    GraphReaction(SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> redex, SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> reactum, String rulename) {
        this.redex = redex;
        this.reactum = reactum;
        this.rulename = rulename;
    }

    public SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> getRedex() {
        return redex;
    }

    public SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> getReactum() {
        return reactum;
    }

    public String getRulename() {
        return rulename;
    }
}
