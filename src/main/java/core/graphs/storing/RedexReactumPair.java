package core.graphs.storing;

import core.graphs.customized.vertices.Vertex;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

public class RedexReactumPair {

    private DirectedMultigraph<Vertex, DefaultEdge> redex;
    private DirectedMultigraph<Vertex, DefaultEdge> reactum;
    private String rulename;

    public RedexReactumPair(DirectedMultigraph<Vertex, DefaultEdge> redex, DirectedMultigraph<Vertex, DefaultEdge> reactum, String rulename) {
        this.redex = redex;
        this.reactum = reactum;
        this.rulename = rulename;
    }

    public DirectedMultigraph<Vertex, DefaultEdge> getRedex() {
        return redex;
    }

    public DirectedMultigraph<Vertex, DefaultEdge> getReactum() {
        return reactum;
    }

    public String getRulename() {
        return rulename;
    }
}
