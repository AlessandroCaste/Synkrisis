package core.graphs.storing;

import core.exporting.spotExporting.SpotExporter;
import core.exporting.spotExporting.SpotInfo;
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

    // Model Info
    private String modelName;
    private ArrayList<String> reactionNames;

    // Graphs
    private Multigraph<Vertex, DefaultEdge> model;
    private ArrayList<RedexReactumPair> reactionsList = new ArrayList<>();
    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph;

    // Hashmap to track the probability of reactions
    private HashMap<String,Float> rulesWeightMap = new HashMap<>();

    // Hashmap for markers ID
    private HashMap<String,Integer> markerMap;

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
        if(transitionGraph.vertexSet().size() < 100)
            CreateGraphvizImages.getInstance().createTransition(transitionGraph);
        else
            System.out.println("[WARNING] Transition graph is too big, no printing shall be made by Synkrisis");
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


    public HashMap<String,Integer> getMarkerMap() {
        return markerMap;
    }

    public DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> getTransitionGraph() {
        return transitionGraph;
    }

    public void addTransition(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph) {
        this.transitionGraph = transitionGraph;
    }

    public void printPrismTransition(DirectedWeightedPseudograph<TransitionVertex,TransitionEdge> transitionPrismGraph) {
        CreateGraphvizImages.getInstance().createTransition(transitionPrismGraph);
    }

    public void addModel(Multigraph<Vertex, DefaultEdge> model) {
        this.model = model;
    }

    public void addReaction(RedexReactumPair reaction) {
        reactionsList.add(reaction);
    }

    public void addMarkerMap(HashMap<String,Integer> markerMap) { this.markerMap = markerMap; }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setReactionNames(ArrayList<String> reactionNames) {
        this.reactionNames = reactionNames;
    }

    public ArrayList<RedexReactumPair> getReactionsList() {
        return reactionsList;
    }

    public void exportToSpot(SpotInfo spotInfo) {
        if(spotInfo == null)
            System.out.println("[SPOT-TRANSLATION] No spot Acceptance has been specified");
        else
            new SpotExporter(transitionGraph,modelName,reactionNames,spotInfo);
    }

}
