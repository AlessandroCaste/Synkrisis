package core.graphBuilding;

import core.graphVisualization.CreateGraphvizModel;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;

public class GraphsCollection {

    Multigraph<Vertex, DefaultEdge> model;
    ArrayList<GraphReaction> reactionsList = new ArrayList<>();

    private static GraphsCollection instance;

    private GraphsCollection() {}

    public static GraphsCollection getInstance() {
        if(instance == null)
            return (instance = new GraphsCollection());
        return instance;
    }

    public void addModel(Multigraph<Vertex, DefaultEdge> model) {
        this.model = model;
    }

    public void addReaction(GraphReaction reaction) {
        reactionsList.add(reaction);
    }


    public void printModel() {
        CreateGraphvizModel.getInstance().createModel(model);
        for(GraphReaction gr : reactionsList) {
            CreateGraphvizModel.getInstance().createReactions(gr);
        }
    }




}
