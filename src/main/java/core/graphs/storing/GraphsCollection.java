package core.graphs.storing;

import core.graphs.customized.TransitionGraph;
import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import core.graphs.customized.vertices.Vertex;
import core.graphs.visualization.CreateGraphvizImages;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphsCollection {

    // Graphs
    private Multigraph<Vertex, DefaultEdge> model;
    private ArrayList<RedexReactumPair> reactionsList = new ArrayList<>();
    private TransitionGraph transitionGraph;

    // Hashmap to track the probability of reactions
    private HashMap<String,Float> rulesWeightMap = new HashMap<>();

    private static GraphsCollection instance;


    private GraphsCollection() {}

    public static GraphsCollection getInstance() {
        if(instance == null)
            return (instance = new GraphsCollection());
        return instance;
    }

    public void printModel() {
        CreateGraphvizImages.getInstance().createModel(model);
        for(RedexReactumPair gr : reactionsList) {
            CreateGraphvizImages.getInstance().createReactions(gr);
        }
    }

    public void printTransition() {
        if(transitionGraph.size() < 100)
            CreateGraphvizImages.getInstance().createTransition(transitionGraph.getGraph());
        else
            System.out.println("[WARNING] Transition graph is too big (>= 100 nodes), no printing shall be made by Synkrisis");
    }

    public Multigraph<Vertex, DefaultEdge> getModel() {
        return model;
    }

    public void addReactionWeight(String reaction, float probability) {
        rulesWeightMap.put(reaction,probability);
    }

    public float getReactionWeight(String reaction) {
        return rulesWeightMap.get(reaction);
    }

    public void addTransition(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph, String modelName, HashMap<String,Integer> markersMap) {
        this.transitionGraph = new TransitionGraph(transitionGraph,modelName,markersMap);
    }

    public TransitionGraph getTransitionGraph() {
        return this.transitionGraph;
    }

    public void addModel(Multigraph<Vertex, DefaultEdge> model) {
        this.model = model;
    }

    public void addReaction(RedexReactumPair reaction) {
        reactionsList.add(reaction);
    }

    public ArrayList<RedexReactumPair> getReactionsList() {
        return reactionsList;
    }

}
