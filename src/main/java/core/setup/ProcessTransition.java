package core.setup;

import core.graphBuilding.EdgeTransitionGraph;
import core.graphBuilding.GraphsCollection;
import core.graphBuilding.VertexTransitionGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.io.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessTransition {

    private static Logger logger = Logger.getLogger("Report");
    private String modelName;

    private HashMap<String,Integer> hashToId = new HashMap<>();
    private int vertexID = 0;

    public ProcessTransition(String modelName) {
        this.modelName = modelName;
        processTransition();
    }

    private void processTransition() {
        try {

            DirectedMultigraph<VertexTransitionGraph, EdgeTransitionGraph> graph = new DirectedMultigraph<>(EdgeTransitionGraph.class);
            // The following methods specify how to create vertices, edges, and update them during parsing
            // Dot translation uses a special kind of vertex that stores an ID, a label and a list of properties
            VertexProvider<VertexTransitionGraph> vertexProvider = (s, map) -> {
                int currentID;
                // Extracting attributes from dot representation
                Attribute label = map.get("label");
                Attribute properties = map.get("properties");
                String labelString;
                ArrayList<String> propertiesList;
                if(label != null)
                    labelString = label.toString();
                else
                    labelString = "";
                if(properties != null)
                    propertiesList = new ArrayList<>(Arrays.asList(properties.toString().split("\\s*,\\s*")));
                else
                    propertiesList = null;
                if(hashToId.containsKey(s) ) {
                    currentID = hashToId.get(s);
                } else {
                    hashToId.put(s,vertexID);
                    currentID = vertexID;
                    vertexID++;
                }
                return new VertexTransitionGraph(currentID,labelString,propertiesList);
            };

            EdgeProvider<VertexTransitionGraph, EdgeTransitionGraph> edgeProvider = (v1, v2, s2, map) -> {
                String label = (map.get("label")).toString();
                float weight = GraphsCollection.getInstance().getReactionWeight(label);
                return new EdgeTransitionGraph(label,weight);
            };

            ComponentUpdater<VertexTransitionGraph> vertexUpdater = (vertex, map) -> {
                Attribute label = map.get("label");
                Attribute properties = map.get("properties");
                ArrayList<String> propertiesList;
                if(label != null) {
                    vertex.setLabel(label.toString());
                   // hashToId.
                  //  GraphsCollection.getInstance().addToTransitionMap(currentID,label.toString());
                }
                if(properties != null) {
                    propertiesList = new ArrayList<>(Arrays.asList(properties.toString().split("\\s*,\\s*")));
                    vertex.setProperties(propertiesList);
                }
            };

            DOTImporter<VertexTransitionGraph, EdgeTransitionGraph> importer = new DOTImporter<>(vertexProvider, edgeProvider, vertexUpdater);
            FileReader transitionFile = new FileReader(modelName+"/"+modelName+".transition");
            importer.importGraph(graph, transitionFile);
            logger.log(Level.INFO,".dot transition file correctly translated to jgraph model");
            GraphsCollection.getInstance().addTransition(graph);
            GraphsCollection.getInstance().printTransition();

            writeTransitionFile(graph);

        } catch (FileNotFoundException fe) {
            System.out.println("[FATAL ERROR] Transition system hasn't been successfully created; problems with the model checker?");
            logger.log(Level.SEVERE, "Missing transition file; something went wrong when reading the output of the model checker (bigmc?) and printing it to file\nStack trace: " + fe.getMessage());
        } catch (ImportException ie) {
            System.out.println("[FATAL ERROR] Error while importing dot transition file: check for syntax problems");
            logger.log(Level.SEVERE, "JgraphT parsing of dot file failed; this may have to do with vertex/edge providers, but it probably boils down to wrong dot specifications\n Stack trace: " + ie.getMessage());
        }
    }

    private void writeTransitionFile(DirectedMultigraph<VertexTransitionGraph, EdgeTransitionGraph> graph) {
        logger.log(Level.INFO,"Writing .tra file");
        try {
            BufferedWriter traWriter = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".tra",true));
            traWriter.write(graph.vertexSet().size() + " " + graph.edgeSet().size() + "\n");
            for(VertexTransitionGraph v : graph.vertexSet()) {
                for(EdgeTransitionGraph e : graph.edgesOf(v)) {
                    traWriter.write(v.getVertexID() + " " + graph.getEdgeTarget(e).getVertexID() + " " + graph.getEdgeWeight(e) + "\n");
                }
            }
            traWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
