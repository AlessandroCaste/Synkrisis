package core.graphModels;

import core.graphModels.storing.GraphsCollection;
import core.graphModels.verticesAndEdges.TransitionEdge;
import core.graphModels.verticesAndEdges.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.io.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class TransitionDotImporter {

    private static Logger logger = Logger.getLogger("Report");
    private String modelName;

    // I associate an ID to each state
    private HashMap<String,Integer> hashToId = new HashMap<>();
    private int vertexID = 0;

    // Associates each properties to a unique ID for PRISM
    private HashMap<String,Integer> markerMap;

    // Classes to translate DOT specification into vertices and edges
    private VertexProvider<TransitionVertex> vertexProvider;
    private EdgeProvider<TransitionVertex, TransitionEdge> edgeProvider;
    private ComponentUpdater<TransitionVertex> vertexUpdater;

    private GraphsCollection graphsCollection = GraphsCollection.getInstance();

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph;

    public TransitionDotImporter(String modelName,boolean transitionOnly) {
        this.modelName = modelName;
        processTransition(transitionOnly);
    }

    private void processTransition(boolean transitionOnly) {

        transitionGraph = new DirectedWeightedPseudograph<>(TransitionEdge.class);

        // I retrieve the list of markers together with their IDs
        markerMap = GraphsCollection.getInstance().getMarkerMap();

        // The following methods specify how to create vertices, edges, and update them during parsing
        // Dot translation uses a special kind of vertex that stores an ID, a label and a list of properties
        vertexProvider = (s, map) -> {
            int currentID;
            // Extracting attributes from dot representation
            Attribute label = map.get("label");
            Attribute propertiesAttribute = map.get("properties");
            String labelString;
            ArrayList<String> markerLabels;
            TreeSet<Integer> markersID = new TreeSet<>();

            if (label != null)
                labelString = label.toString();
            else
                labelString = "";
            if (propertiesAttribute != null) {
                markerLabels = new ArrayList<>(Arrays.asList(propertiesAttribute.toString().split("\\s*,\\s*")));
                markersID = new TreeSet<>();
                for (String propertyName : markerLabels)
                    markersID.add(markerMap.get(propertyName));
            }
            if (hashToId.containsKey(s)) {
                currentID = hashToId.get(s);
            } else {
                hashToId.put(s, vertexID);
                currentID = vertexID;
                vertexID++;
            }
            return new TransitionVertex(currentID, labelString, markersID);
        };

        edgeProvider = (v1, v2, s2, map) -> {
            double weight = graphsCollection.getReactionWeight(s2);
            TransitionEdge te = new TransitionEdge(s2);
            transitionGraph.setEdgeWeight(te,Math.round(weight*1e3)/1e3);
            return te;
        };

        vertexUpdater = (vertex, map) -> {
            Attribute label = map.get("label");
            Attribute propertiesAttribute = map.get("properties");
            ArrayList<String> markersLabels;

            if (label != null)
                vertex.setLabel(label.toString());
            if (propertiesAttribute != null) {
                TreeSet<Integer> markersID;
                markersLabels = new ArrayList<>(Arrays.asList(propertiesAttribute.toString().split("\\s*,\\s*")));
                markersID = new TreeSet<>();
                for (String propertyName : markersLabels)
                    markersID.add(markerMap.get(propertyName));
                vertex.setProperties(markersID);
            }
        };

        try {
            DOTImporter<TransitionVertex, TransitionEdge> importer = new DOTImporter<>(vertexProvider, edgeProvider, vertexUpdater);
            //TODO cambiare questa cosa
            FileReader transitionFile;
            if(transitionOnly)
                transitionFile = new FileReader(modelName + "transition.dot");
            else
                transitionFile = new FileReader(modelName + "/" + "transition.dot");
            importer.importGraph(transitionGraph, transitionFile);
            logger.log(Level.INFO,".dot transition file correctly translated to jgraph model");
            graphsCollection.addTransition(transitionGraph);
        } catch (FileNotFoundException fe) {
            System.out.println("[FATAL ERROR] Transition system hasn't been successfully created; problems with the model checker?");
            logger.log(Level.SEVERE, "Missing transition file; something went wrong when reading the output of the model checker (bigmc?) and printing it to file\nStack trace: " + fe.getMessage());
            exit(1);
        } catch (ImportException ie) {
            System.out.println("[FATAL ERROR] Error while importing dot transition file: check for syntax problems");
            logger.log(Level.SEVERE, "JgraphT parsing of dot file failed; this may have to do with vertex/edge providers, but it probably boils down to wrong dot specifications\nStack trace: " + ie.getMessage());
        }
    }

}
