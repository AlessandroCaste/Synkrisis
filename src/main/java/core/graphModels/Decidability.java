package core.graphModels;

import core.graphModels.verticesAndEdges.RedexReactumPair;
import core.graphModels.verticesAndEdges.Vertex;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;

public class Decidability {

    public boolean isNonDeleting() {

        RedexReactumPair reactionGraph = GraphsCollection.getInstance().getReactionsList().get(0);
        Multigraph<Vertex, DefaultEdge> redex = reactionGraph.getRedex();
        Multigraph<Vertex,DefaultEdge> reactum = reactionGraph.getReactum();

        // If vertex/edges set are bigger than reactum ones then a deletion took place
        if(redex.vertexSet().size() > reactum.vertexSet().size())
            return false;
        if(redex.edgeSet().size() > reactum.edgeSet().size())
            return false;

        // All nodes in redex must be contained in reactum
        ArrayList<Vertex> redexVertices = new ArrayList<>(redex.vertexSet());
        ArrayList<Vertex> reactumVertices = new ArrayList<>(reactum.vertexSet());



        return true;
    }
}
