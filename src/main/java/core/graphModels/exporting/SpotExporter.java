package core.graphModels.exporting;

import core.graphModels.SpotAcceptanceState;
import core.graphModels.SpotInfo;
import core.graphModels.verticesAndEdges.EdgeTransitionGraph;
import core.graphModels.verticesAndEdges.TransitionVertex;
import org.jgrapht.graph.DirectedMultigraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpotExporter {

    private static Logger logger = Logger.getLogger("Report");


    private DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> transitionGraph;
    private String modelName;
    private ArrayList<String> reactionNames;
    private HashMap<String,Integer> markerMap;
    private int numberAcceptanceSets;
    private String acc_name;
    private ArrayList<SpotAcceptanceState> acceptanceStates;
    private String outputString;


    public SpotExporter(DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> transitionGraph, String modelName,
                        ArrayList<String> reactionNames, HashMap<String,Integer> markerMap, SpotInfo spotInfo) {
        this.transitionGraph = transitionGraph;
        this.modelName = modelName;
        this.reactionNames = reactionNames;
        this.markerMap = markerMap;
        this.acc_name = spotInfo.getAcc_name();
        this.numberAcceptanceSets = spotInfo.getNumberAcceptanceSets();
        this.acceptanceStates = spotInfo.getAcceptanceStates();
        this.outputString = spotInfo.getOutputString();

        translate();
    }

    private void translate() {
        logger.log(Level.INFO,"Writing .hoa file");

        try {
            BufferedWriter hoaWriter = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".hoa",false));
            hoaWriter.write("Hoa: v1\n");
            hoaWriter.write("name: \"" + modelName + "\"\n");
            hoaWriter.write("States: " + transitionGraph.vertexSet().size() + "\n");
            hoaWriter.write("AP : " + reactionNames.size() + " " + reactionNamesFormatted() + "\n");
            hoaWriter.write(acc_name + "\n"); //transitionGraph.vertexSet().size());
            hoaWriter.write("Acceptance: " + numberAcceptanceSets + outputString + "\n");
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
