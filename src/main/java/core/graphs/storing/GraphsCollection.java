package core.graphs.storing;

import core.graphs.customized.TransitionGraph;
import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import core.graphs.customized.vertices.Vertex;
import core.graphs.visualization.PrintModel;
import core.graphs.visualization.PrintReaction;
import core.graphs.visualization.PrintTransition;
import org.apache.commons.io.FilenameUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class GraphsCollection {

    // Graphs
    private String modelName;
    private DirectedMultigraph<Vertex, DefaultEdge> model;
    private ArrayList<RedexReactumPair> reactionsList = new ArrayList<>();
    private TransitionGraph transitionGraph;

    // Hashmap to track the probability of reactions
    private HashMap<String,Float> rulesWeightMap = new HashMap<>();

    // Hashmap to track markers
    private HashMap<String,Integer> markersMap;

    // Multithreading printing
    private ThreadPoolExecutor executor;

    private static GraphsCollection instance;


    private GraphsCollection() {}

    public static GraphsCollection getInstance() {
        if(instance == null)
            return (instance = new GraphsCollection());
        return instance;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void printModel() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        PrintModel printModel = new PrintModel(model,modelName) ;
        executor.execute(printModel);
        for(RedexReactumPair gr : reactionsList) {
            PrintReaction printReaction = new PrintReaction(gr,modelName);
            executor.execute(printReaction);
        }
        executor.shutdown();
    }

    public void printTransition() {
        if(transitionGraph.size() < 50) {
            new Thread(new PrintTransition(transitionGraph)).start();
        }
        else
            System.out.println("[WARNING] Transition graph is too big (>= 100 nodes), no printing shall be made by Synkrisis");
    }

    public DirectedMultigraph<Vertex, DefaultEdge> getModel() {
        return model;
    }

    public void addReactionWeight(String reaction, float probability) {
        rulesWeightMap.put(reaction,probability);
    }

    public void setMarkersMap(HashMap<String,Integer> markersMap){
        this.markersMap = markersMap;
    }

    public HashMap<String, Integer> getMarkersMap(){
        return markersMap;
    }

    public float getReactionWeight(String reaction) {
        return rulesWeightMap.get(reaction);
    }

    public void addTransition(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph, String modelName, HashMap<String,Integer> markersMap) {
        this.transitionGraph = new TransitionGraph(transitionGraph, FilenameUtils.getBaseName(modelName),markersMap);
    }

    public TransitionGraph getTransitionGraph() {
        return this.transitionGraph;
    }

    public void addModel(DirectedMultigraph<Vertex, DefaultEdge> model) {
        this.model = model;
    }

    public void addReaction(RedexReactumPair reaction) {
        reactionsList.add(reaction);
    }

    public ArrayList<RedexReactumPair> getReactionsList() {
        return reactionsList;
    }

}
