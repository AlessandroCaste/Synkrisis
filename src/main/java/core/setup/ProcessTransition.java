package core.setup;

import core.graphBuilding.VertexTransitionGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.io.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessTransition {

    private static Logger logger = Logger.getLogger("Report");
    private String modelName;

    public ProcessTransition(String modelName) {
        this.modelName = modelName;
        processTransition();
    }

    private void processTransition() {
        try {
            DirectedMultigraph<VertexTransitionGraph, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);

            // The following methods specify how to create vertices, edges, and update them during parsing
            // Dot translation uses a special kind of vertex that stores an ID, a label and a list of properties
            VertexProvider<VertexTransitionGraph> vertexProvider = (s, map) -> {
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
                return new VertexTransitionGraph(s,labelString,propertiesList);
            };
            EdgeProvider<VertexTransitionGraph, DefaultEdge> edgeProvider = (v1, v2, s2, map) -> new DefaultEdge();
            ComponentUpdater<VertexTransitionGraph> vertexUpdater = (vertex, map) -> {
                Attribute label = map.get("label");
                Attribute properties = map.get("properties");
                ArrayList<String> propertiesList;
                if(label != null)
                    vertex.setLabel(label.toString());
                if(properties != null) {
                    propertiesList = new ArrayList<>(Arrays.asList(properties.toString().split("\\s*,\\s*")));
                    vertex.setProperties(propertiesList);
                }
            };

            DOTImporter<VertexTransitionGraph, DefaultEdge> importer = new DOTImporter<>(vertexProvider, edgeProvider, vertexUpdater);
            FileReader transitionFile = new FileReader(modelName+"/"+modelName+".transition");
            importer.importGraph(graph, transitionFile);
            logger.log(Level.INFO,".dot transition file correctly translated to jgraph model");

        } catch (FileNotFoundException fe) {
            System.out.println("[FATAL ERROR] Transition system hasn't been successfully created; problems with the model checker?");
            logger.log(Level.SEVERE, "Missing transition file; something went wrong when reading the output of the model checker (bigmc?) and printing it to file\nStack trace: " + fe.getMessage());
        } catch (ImportException ie) {
            System.out.println("[FATAL ERROR] Error while importing dot transition file: check for syntax problems");
            logger.log(Level.SEVERE, "JgraphT parsing of dot file failed; this may have to do with vertex/edge providers, but it probably boils down to wrong dot specifications\n Stack trace: " + ie.getMessage());
        }
    }
}
