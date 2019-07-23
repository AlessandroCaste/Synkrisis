package core.graphModels.exporting;

import core.graphModels.verticesAndEdges.EdgeTransitionGraph;
import core.graphModels.verticesAndEdges.TransitionVertex;
import org.jgrapht.graph.DirectedMultigraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

// Prints files required by prism
public class PrismExporter {

    private static Logger logger = Logger.getLogger("Report");

    private static PrismExporter instance;

    private DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> transitionGraph;
    private String modelName;
    private String propertiesString;
    private HashMap<String,Integer> markerMap;
    private String path;

    public PrismExporter(DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> transitionGraph, String modelName, HashMap<String,Integer> markerMap, String propertiesString) {
        this.transitionGraph = transitionGraph;
        this.modelName = modelName;
        this.markerMap = markerMap;
        this.propertiesString = propertiesString;
        path = modelName + "/prism/";

        File prismPath = new File(path);
        if(!prismPath.exists()) {
            if(!prismPath.mkdir()) {
                System.out.println("Can't create a new 'prism' directory");
                logger.log(Level.WARNING,"Couldn't create a 'prism' folder for model outputting");
            }
        }

    }

    public void writePrismFiles() {
        writeTransitionFile();
        writeLabelFile();
        writeProperties();
    }


    private void writeTransitionFile() {
        logger.log(Level.INFO,"Writing .tra file");
        try {
            BufferedWriter traWriter = new BufferedWriter(new FileWriter(path + modelName + ".tra",false));
            traWriter.write(transitionGraph.vertexSet().size() + " " + transitionGraph.edgeSet().size() + "\n");
            for(TransitionVertex v : transitionGraph.vertexSet()) {
                for(EdgeTransitionGraph e : transitionGraph.outgoingEdgesOf(v)) {
                    traWriter.write(v.getVertexID() + " " + transitionGraph.getEdgeTarget(e).getVertexID() + " " + transitionGraph.getEdgeWeight(e) + "\n");
                }
            }
            traWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the transition (.tra) file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private void writeLabelFile() {
        logger.log(Level.INFO,"Writing .lab file");
        try {
            BufferedWriter labWriter = new BufferedWriter(new FileWriter(path + modelName + ".lab",false));
            for(String marker : markerMap.keySet())
                labWriter.write(markerMap.get(marker) + "=\"" + marker + "\" ");
            labWriter.write("\n");
            for(TransitionVertex v : transitionGraph.vertexSet())
                if(!v.getPropertiesString().equals(""))
                    labWriter.write(v.getVertexID() + ": " + v.getPropertiesString() + "\n");
            labWriter.close();
        } catch (IOException e) {
            System.out.println("Can't output the label (.lab) file!");
            logger.log(Level.SEVERE, "Can't write .lab file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
    }

    private void writeProperties() {
        if(propertiesString != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path + modelName + ".prop"),false));
                writer.write(propertiesString);
                writer.close();
            } catch (IOException e) {
                System.out.println("Can't output the prism property (.prop) file!");
                logger.log(Level.SEVERE, "Error with properties buffer writer. Something's off with the file, possibly authorization\nStack trace " + e.getMessage());
            }
        }
    }

}

