package core.graphModels;

import core.exporting.prismExporting.PrismExporter;
import core.exporting.spotExporting.SpotExporter;
import core.exporting.spotExporting.SpotInfo;
import core.graphModels.verticesAndEdges.RedexReactumPair;
import core.graphModels.verticesAndEdges.TransitionEdge;
import core.graphModels.verticesAndEdges.TransitionVertex;
import core.graphModels.verticesAndEdges.Vertex;
import core.graphVisualization.CreateGraphvizImages;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class GraphsCollection {

    private static Logger logger = Logger.getLogger("Report");

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
        CreateGraphvizImages.getInstance().createTransition(transitionGraph);
    }

    public Multigraph<Vertex, DefaultEdge> getModel() {
        return model;
    }

    void addReactionWeight(String reaction, float probability) {
        rulesWeightMap.put(reaction,probability);
    }

    float getReactionWeight(String reaction) {
        return rulesWeightMap.get(reaction);
    }


    HashMap<String,Integer> getMarkerMap() {
        return markerMap;
    }


    void addTransition(DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph) {
        this.transitionGraph = transitionGraph;
    }

    public void printPrismTransition(DirectedWeightedPseudograph<TransitionVertex,TransitionEdge> transitionPrismGraph) {
        CreateGraphvizImages.getInstance().createTransition(transitionPrismGraph);
    }

    void addModel(Multigraph<Vertex, DefaultEdge> model) {
        this.model = model;
    }

    void addReaction(RedexReactumPair reaction) {
        reactionsList.add(reaction);
    }

    void addMarkerMap(HashMap<String,Integer> markerMap) { this.markerMap = markerMap; }

    void setModelName(String modelName) {
        this.modelName = modelName;
    }

    void setReactionNames(ArrayList<String> reactionNames) {
        this.reactionNames = reactionNames;
    }

    public ArrayList<RedexReactumPair> getReactionsList() {
        return reactionsList;
    }

    public void exportToPrism(String propertiesString) {
        new PrismExporter(transitionGraph,modelName,markerMap,propertiesString).writePrismFiles();
    }

    public void exportToSpot(SpotInfo spotInfo) {
        // Check if it's not a w-automaton
        boolean result = true;
        for(TransitionVertex v : transitionGraph.vertexSet())
            if(!Graphs.vertexHasSuccessors(transitionGraph,v))
                result = false;
        if(!result)
            System.out.println("[SPOT-TRANSLATION] Can't translate to SPOT: transition graph is not an w-automaton");
        if(spotInfo == null)
            System.out.println("[SPOT-TRANSLATION] No spot Acceptance has been specified");
        else
            new SpotExporter(transitionGraph,modelName,reactionNames,spotInfo);
    }

}
