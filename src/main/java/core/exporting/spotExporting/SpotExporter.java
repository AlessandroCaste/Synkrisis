package core.exporting.spotExporting;

import core.graphs.transitiongraph.TransitionEdge;
import core.graphs.transitiongraph.TransitionGraph;
import core.graphs.transitiongraph.TransitionVertex;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpotExporter {

    private static Logger logger = Logger.getLogger("Report");


    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionJgraph;
    private String modelName;
    private ArrayList<String> reactionNames;
    private HashMap<String,Integer> reactionMap;
    private int numberAcceptanceSets;
    private String acc_name;
    private ArrayList<SpotAcceptanceState> acceptanceStates;
    private String outputString;


    // Mapping name strings to integer ids
    private HashMap<TransitionVertex,String> idMap;



    public SpotExporter(TransitionGraph transitionGraph, SpotInfo spotInfo) {
        this.transitionJgraph = transitionGraph.cloneJgraph();
        this.modelName = transitionGraph.getModelName();
        this.reactionNames = transitionGraph.getReactionNames();
        this.acc_name = spotInfo.getAcc_name();
        this.numberAcceptanceSets = spotInfo.getNumberAcceptanceSets();
        this.acceptanceStates = spotInfo.getAcceptanceStates();
        if(spotInfo.getOutputString()!=null)
            this.outputString = spotInfo.getOutputString();
        else
            this.outputString = "";
        reactionMap = new HashMap<>();
        int counter = 0;
        for(String s : reactionNames) {
            reactionMap.put(s, counter);
            counter++;
        }
        this.idMap = transitionGraph.getIDMap();
        normalization(counter);
    }

    // Check if it's not a w-automaton, in that case add self-loops
    private void normalization(int counter) {
        boolean result = true;
        for(TransitionVertex v : this.transitionJgraph.vertexSet())
            if(!Graphs.vertexHasSuccessors(this.transitionJgraph,v)) {
                result = false;
                this.transitionJgraph.addEdge(v,v,new TransitionEdge("spot self-loop"));
            }
        if(!result) {
            this.reactionNames.add("spot self-loop");
            reactionMap.put("spot self-loop",counter);
            System.out.println("[SPOT-TRANSLATION] Model has been transformed into an ω-automaton by addition of self-loops");
        }
    }

    public boolean translate() {
        boolean isSuccessful = false;
        System.out.println("Spot translation started");
        logger.log(Level.INFO,"Writing .hoa file");
        File path = new File(modelName + "/spot");
        try {
            path.mkdir();
            BufferedWriter hoaWriter = new BufferedWriter(new FileWriter(modelName + "/spot/" + modelName + ".hoa",false));
            hoaWriter.write("HOA: v1\n");

            // Concatenation is avoided for efficiency
            // Model name
            hoaWriter.write("name: \"");
            hoaWriter.write(modelName);
            hoaWriter.write("\"\n");


            // States and start
            hoaWriter.write("States: ");
            hoaWriter.write(Integer.toString(transitionJgraph.vertexSet().size()));
            hoaWriter.write("\n");
            hoaWriter.write("Start: 0\n");

            // AP
            hoaWriter.write("AP: ");
            hoaWriter.write(Integer.toString(reactionNames.size()));
            hoaWriter.write(" ");
            hoaWriter.write(reactionNamesFormatted());
            hoaWriter.write("\n");

            // acc_name
            hoaWriter.write(acc_name);
            hoaWriter.write("\n");

            // Acceptance
            hoaWriter.write("Acceptance: ");
            hoaWriter.write(Integer.toString(numberAcceptanceSets));
            hoaWriter.write(" ");
            hoaWriter.write(outputString);
            hoaWriter.write("\n");

            // Printing states and edges
            System.out.println("Printing SPOT states and edges");
            hoaWriter.write("--BODY--\n");
            for(TransitionVertex tv : transitionJgraph.vertexSet()) {
                hoaWriter.write("State: ");
                hoaWriter.write(idMap.get(tv));
                hoaWriter.write(" ");
                hoaWriter.write(acceptanceString(tv));
                hoaWriter.write("\n");
                for(TransitionEdge te : transitionJgraph.outgoingEdgesOf(tv)) {
                    hoaWriter.write("[");
                    hoaWriter.write(Integer.toString(reactionMap.get(te.getLabel())));
                    hoaWriter.write("]");
                    hoaWriter.write(" ");
                    hoaWriter.write(idMap.get(transitionJgraph.getEdgeTarget(te)));
                    hoaWriter.write("\n");
                }
            }
            hoaWriter.write("--END--\n");

            // Printing the acceptance - state mapping
            if(numberAcceptanceSets > 0)
                hoaWriter.write("\n/* Acceptance states map\n");

            try {
                for (int i = 0; i <= numberAcceptanceSets - 1; i++) {
                    hoaWriter.write(Integer.toString(acceptanceStates.get(i).getAcceptanceStateID()));
                    hoaWriter.write(": ");
                    hoaWriter.write(" ");
                    hoaWriter.write(acceptanceStates.get(i).getAcceptanceStateString());
                    hoaWriter.write("\n");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("[SPOT-ACCEPTANCE ERROR] Number of spot acceptance sets doesn't match with acceptance specifications");
                logger.log(Level.SEVERE,"Can't conclude spot translation, problem with the number of acceptance states specified after the 'Acceptance' keyword\nStack trace: " + e.getMessage());
            }
            if(numberAcceptanceSets > 0)
                hoaWriter.write(("*/\n"));
            hoaWriter.close();
            isSuccessful = true;
        } catch (IOException e) {
            System.out.println("Can't output the transition file!");
            logger.log(Level.SEVERE, "Can't write .tra file, problem with BufferedWriter?\nStack trace: " + e.getMessage());
        }
        return isSuccessful;
    }

    private String reactionNamesFormatted() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String s : reactionNames)
            stringBuilder.append("\"").append(s).append("\" ");
        return stringBuilder.toString();
    }


    private String acceptanceString(TransitionVertex tv) {
        StringBuilder builder = new StringBuilder();
        boolean firstElement = true;

        for(SpotAcceptanceState sap : acceptanceStates)
            if(sap.verify(tv.getMarkers())) {
                tv.addAcceptanceState(sap.getAcceptanceStateID());
                if(firstElement) {
                    builder.append("{");
                    builder.append(sap.getAcceptanceStateID());
                    firstElement = false;
                }
                else
                    builder.append(" ").append(sap.getAcceptanceStateID());
            }
        if(builder.length()>0)
            builder.append("}");
        return builder.toString();
        }

    }




