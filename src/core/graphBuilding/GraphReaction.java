package core.graphBuilding;

import core.graphBuilding.Vertex;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

public class GraphReaction {

    Multigraph<Vertex, DefaultEdge> redex;
    Multigraph<Vertex, DefaultEdge> reactum;
    String rulename;

    public GraphReaction(Multigraph<Vertex, DefaultEdge> redex, Multigraph<Vertex, DefaultEdge> reactum, String rulename) {
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
