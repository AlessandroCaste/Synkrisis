package core.graphs;

import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import core.graphs.storing.GraphsCollection;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.io.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransitionDotImporter {

    private static Logger logger = Logger.getLogger("Report");
    private String transitionPath;
    private String modelName;

    // Tells us if we want to use only the importing module
    private boolean transitionOnly;

    private GraphsCollection graphsCollection = GraphsCollection.getInstance();

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph;

    // Result of transition is saved
    private boolean successfulImporting = true;

    public TransitionDotImporter(String transitionPath,boolean transitionOnly,String modelName) {
        this.transitionPath = transitionPath;
        this.transitionOnly = transitionOnly;
        this.modelName = modelName;
    }

    @SuppressWarnings("Duplicates")
    public void processTransition() {

        transitionGraph = new DirectedWeightedPseudograph<>(TransitionEdge.class);

        VertexProvider vertexProvider = (s, map) -> {
            int currentID;
            // Extracting attributes from dot representation
            String labelString;
            ArrayList<String> markersLabels;
            TreeSet<Integer> markersID = new TreeSet<>();
            GraphDataEncapsulation graphData = GraphDataEncapsulation.getInstance();

            if (map.get("label")!=null)
                labelString = map.get("label").toString();
            else
                labelString = "";
            if (map.get("synkrisis") != null) {
                String propertiesAttribute = map.get("synkrisis").toString();
                markersLabels = new ArrayList<>(Arrays.asList(propertiesAttribute.split("\\s*,\\s*")));
                for (String propertyName : markersLabels) {
                    if(!graphData.markersContainKey(propertyName))
                        graphData.insertMarker(propertyName);
                    markersID.add(graphData.getMarkerID(propertyName));
                }
            }
            if (graphData.hashesContainKey(s)) {
                currentID = graphData.getVertexID(s);
            } else {
                currentID = graphData.getCurrentVertexID();
                graphData.insertHash(s);
            }

            return new TransitionVertex(currentID, labelString, markersID);
        };

        EdgeProvider<TransitionVertex, TransitionEdge> edgeProvider;
        edgeProvider = (transitionVertex, v1, s, map) -> {
            double weight;
            if(!transitionOnly)
                weight = graphsCollection.getReactionWeight(s);
            else {
                if(map.get("weight")!=null)
                    weight = Double.parseDouble(map.get("weight").toString());
                else {
                    //TODO something more?
                    weight = 1.0;
                }
            }
            TransitionEdge te = new TransitionEdge(s);
            //transitionGraph.addEdge(transitionVertex,v1,te);
            transitionGraph.setEdgeWeight(te,Math.round(weight*1e3)/1e3);
            return te;
        };

        ComponentUpdater<TransitionVertex> vertexUpdater = (transitionVertex, map) -> {
            Attribute label = map.get("label");
            Attribute propertiesAttribute = map.get("synkrisis");
            ArrayList<String> markersLabels;
            TreeSet<Integer> markersID = new TreeSet<>();
            GraphDataEncapsulation graphData = GraphDataEncapsulation.getInstance();

            if (label != null)
                transitionVertex.setLabel(label.toString());
            if (propertiesAttribute != null) {
                markersLabels = new ArrayList<>(Arrays.asList(propertiesAttribute.toString().split("\\s*,\\s*")));
                for (String propertyName : markersLabels) {
                    if(!graphData.markersContainKey(propertyName)) {
                        graphData.insertMarker(propertyName);
                    }
                    markersID.add(graphData.getMarkerID(propertyName));
                }
                transitionVertex.setProperties(markersID);
            }
        };

        try {
            DOTImporter<TransitionVertex, TransitionEdge> importer = new DOTImporter<>(vertexProvider, edgeProvider, vertexUpdater);
            FileReader transitionFile;
            transitionFile = new FileReader(transitionPath);
            importer.importGraph(transitionGraph, transitionFile);
            logger.log(Level.INFO,".dot transition file correctly translated to jgraph model");
            graphsCollection.addTransition(transitionGraph, modelName, GraphDataEncapsulation.getInstance().markersMap);
        } catch (FileNotFoundException fe) {
            System.out.println("[FATAL ERROR] Can't find the transition file: problems with the model checker?");
            logger.log(Level.SEVERE, "Missing transition file; something went wrong when reading the output of the model checker (bigmc?) and printing it to file\nStack trace: " + fe.getMessage());
            successfulImporting = false;
        } catch (ImportException ie) {
            System.out.println("[FATAL ERROR] Error while importing dot transition file: check for syntax problems");
            logger.log(Level.SEVERE, "JgraphT parsing of dot file failed; this may have to do with vertex/edge providers, but it probably boils down to wrong dot specifications\nStack trace: " + ie.getMessage());
            successfulImporting = false;
        }
    }

    public boolean isSuccessful() {
        return successfulImporting;
    }


    // A static class encapsulates data so that it can be accessed by the different providers
    static class GraphDataEncapsulation {

        private HashMap<String,Integer> hashToId;
        private int vertexID;
        private HashMap<String,Integer> markersMap;
        private int markerID;
        private boolean transitionOnly;

        private static GraphDataEncapsulation instance;

        static GraphDataEncapsulation getInstance(){
            if(instance == null)
                instance = new GraphDataEncapsulation();
            return instance;
        }

        // The markersMap will be empty in case we just import a transition graph or we haven't specified any marker
        // If no markersMap is found the behavior will be considered transitionOnly(markers are only found in .dot file)
        GraphDataEncapsulation() {
            if(GraphsCollection.getInstance().getMarkersMap()!=null) {
                markersMap = GraphsCollection.getInstance().getMarkersMap();
                transitionOnly = false;
            }
            else {
                markersMap = new HashMap<>();
                transitionOnly = true;
            }
            hashToId  = new HashMap<>();
            vertexID = 0;
            // This "covers" the weird case a user specifies new, previously undefined properties in the transition graph
            if(!transitionOnly)
                markerID = Collections.max(markersMap.values()) + 1;
            else
                markerID = 0;
        }

        boolean markersContainKey(String key) {
            return markersMap.containsKey(key);
        }

        boolean hashesContainKey(String key) {
            return hashToId.containsKey(key);
        }

        void insertHash(String key){
            hashToId.put(key,vertexID);
            vertexID++;
        }

        // I insert a marker only in case of transition graph importing
        void insertMarker(String key) {
            if(!markersMap.containsKey(key) && transitionOnly) {
                markersMap.put(key, markerID);
                markerID++;
            } else if(!markersMap.containsKey(key) && !transitionOnly){
                System.out.println("[WARNING] A marker not defined in specification has been found in the transition file!\nCorrect behavior may note be ensured");
                markersMap.put(key, markerID);
                markerID++;
            }
        }

        int getCurrentVertexID(){
            return vertexID;
        }

        int getVertexID(String key){
            return hashToId.get(key);
        }

        int getMarkerID(String key) {
            return markersMap.get(key);
        }
    }

}
