package core.graphBuilding;

import core.graphVisualization.CreateGraphvizImages;
import org.apache.commons.collections4.BidiMap;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphsCollection {

    private Multigraph<Vertex, DefaultEdge> model;
    private ArrayList<GraphReaction> reactionsList = new ArrayList<>();
    private DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> transitionGraph;

    // Hashmap to track the probability of reactions
    private HashMap<String,Float> rulesWeightMap = new HashMap<>();

    // Hashmap for markers ID
    private BidiMap<Integer,String> markerMap;

    private static GraphsCollection instance;

    private GraphsCollection() {}

    public static GraphsCollection getInstance() {
        if(instance == null)
            return (instance = new GraphsCollection());
        return instance;
    }

    public void printModel() {
        CreateGraphvizImages.getInstance().createModel(model);
        for(GraphReaction gr : reactionsList) {
            CreateGraphvizImages.getInstance().createReactions(gr);
        }
    }

    public void printTransition() {
        CreateGraphvizImages.getInstance().createTransition(transitionGraph);
    }

    public Multigraph<Vertex, DefaultEdge> getModel() {
        return model;
    }

    void addReactionWeight(String reaction, float probability) {
        rulesWeightMap.put(reaction,probability);
    }

    public float getReactionWeight(String reaction) {
        return rulesWeightMap.get(reaction);
    }


    public BidiMap<Integer,String> getMarkerMap() {
        return markerMap;
    }


    public void addTransition(DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> transitionGraph) {
        this.transitionGraph = transitionGraph;
    }


    void addModel(Multigraph<Vertex, DefaultEdge> model) {
        this.model = model;
    }

    void addReaction(GraphReaction reaction) {
        reactionsList.add(reaction);
    }

    void addMarkerMap(BidiMap<Integer,String> markerMap) { this.markerMap = markerMap; }


    public ArrayList<GraphReaction> getReactionsList() {
        return reactionsList;
    }
}
