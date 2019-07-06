package core.graphBuilding;

import core.graphVisualization.CreateGraphvizModel;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphsCollection {

    private Multigraph<Vertex, DefaultEdge> model;
    private ArrayList<GraphReaction> reactionsList = new ArrayList<>();

    // Hashmap to track the probability of reactions
    private HashMap<String,Float> rulesWeightMap = new HashMap<>();

    private static GraphsCollection instance;

    private GraphsCollection() {}

    public static GraphsCollection getInstance() {
        if(instance == null)
            return (instance = new GraphsCollection());
        return instance;
    }

    void addModel(Multigraph<Vertex, DefaultEdge> model) {
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

    public Multigraph<Vertex, DefaultEdge> getModel() {
        return model;
    }

    void addReactionWeight(String reaction, float probability) {
        rulesWeightMap.put(reaction,probability);
    }

    public ArrayList<GraphReaction> getReactionsList() {
        return reactionsList;
    }
}
