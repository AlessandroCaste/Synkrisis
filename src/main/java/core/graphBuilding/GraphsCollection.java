package core.graphBuilding;

import core.graphVisualization.CreateGraphvizImages;
import org.apache.commons.collections4.BidiMap;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraphsCollection {

    private static Logger logger = Logger.getLogger("Report");

    // Model Info
    private String modelName;
    private ArrayList<String> reactionNames;

    // Graphs
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

    void setModelName(String modelName) {
        this.modelName = modelName;
    }

    void setReactionNames(ArrayList<String> reactionNames) {
        this.reactionNames = reactionNames;
    }

    public ArrayList<GraphReaction> getReactionsList() {
        return reactionsList;
    }


    public void exportToPrism() {

        writeTransitionFile(transitionGraph);
        writeLabelFile(transitionGraph);

    }

    private void writeTransitionFile(DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> graph) {
        logger.log(Level.INFO,"Writing .tra file");
        try {
            BufferedWriter traWriter = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".tra",false));
            traWriter.write(graph.vertexSet().size() + " " + graph.edgeSet().size() + "\n");
            for(TransitionVertex v : graph.vertexSet()) {
                for(EdgeTransitionGraph e : graph.outgoingEdgesOf(v)) {
                    traWriter.write(v.getVertexID() + " " + graph.getEdgeTarget(e).getVertexID() + " " + graph.getEdgeWeight(e) + "\n");
                }
            }
            traWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the transition file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private void writeLabelFile(DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> graph) {
        logger.log(Level.INFO,"Writing .lab file");
        try {
            BufferedWriter labWriter = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".lab",false));
            for(String marker : markerMap.values())
                labWriter.write(markerMap.inverseBidiMap().get(marker) + "=\"" + marker + "\" ");
            labWriter.write("\n");
            for(TransitionVertex v : graph.vertexSet())
                if(!v.getProperties().equals(""))
                    labWriter.write(v.getVertexID() + ": " + v.getProperties() + "\n");
            labWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the label file!");
            logger.log(Level.SEVERE, "Can't write .lab file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }


    public void exportToSpot() {
        logger.log(Level.INFO,"Writing .hoa file");
        try {
            BufferedWriter hoaWriter = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".hoa",false));
            hoaWriter.write("Hoa: v1\n");
            hoaWriter.write("name: \"" + modelName + "\"\n");
            hoaWriter.write("States: " + transitionGraph.vertexSet().size() + "\n");
            hoaWriter.write("AP : " + reactionsList.size() + " " + reactionNamesFormatted() + "\n");
            hoaWriter.write("acc-name: generalized-co-Buchi 1\n"); //transitionGraph.vertexSet().size());
            hoaWriter.write("Acceptance: 1 Fin(0)\n");
            hoaWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the transition file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private String reactionNamesFormatted() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String s : reactionNames)
            stringBuilder.append("\"").append(s).append("\" ");
        return stringBuilder.toString();
    }

}
