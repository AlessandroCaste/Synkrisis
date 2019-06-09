package core.graphBuilding;

import core.graphVisualization.CreateGraphvizModel;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.ArrayList;

public class GraphsCollection {

    private SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> model;
    private ArrayList<GraphReaction> reactionsList = new ArrayList<>();

    private static GraphsCollection instance;

    private GraphsCollection() {}

    public static GraphsCollection getInstance() {
        if(instance == null)
            return (instance = new GraphsCollection());
        return instance;
    }

    void addModel(SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> model) {
        this.model = model;
    }

    void addReaction(GraphReaction reaction) {
        reactionsList.add(reaction);
    }


    public void printModel() {
        CreateGraphvizModel.getInstance().createModel(model);
        for(GraphReaction gr : reactionsList) {
            CreateGraphvizModel.getInstance().createReactions(gr);
        }
    }

    public SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> getModel() {
        return model;
    }

    public ArrayList<GraphReaction> getReactionsList() {
        return reactionsList;
    }
}
