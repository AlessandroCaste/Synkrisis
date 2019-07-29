package core.graphModels.storing;

import core.graphModels.verticesAndEdges.Vertex;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

public class RedexReactumPair {

    private Multigraph<Vertex, DefaultEdge> redex;
    private Multigraph<Vertex, DefaultEdge> reactum;
    private String rulename;

    public RedexReactumPair(Multigraph<Vertex, DefaultEdge> redex, Multigraph<Vertex, DefaultEdge> reactum, String rulename) {
        this.redex = redex;
        this.reactum = reactum;
        this.rulename = rulename;
    }

    public Multigraph<Vertex, DefaultEdge> getRedex() {
        return redex;
    }

    public Multigraph<Vertex, DefaultEdge> getReactum() {
        return reactum;
    }

    public String getRulename() {
        return rulename;
    }
}
