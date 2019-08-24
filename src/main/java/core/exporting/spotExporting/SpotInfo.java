package core.exporting.spotExporting;

import java.util.ArrayList;

public class SpotInfo {

    private int numberAcceptanceSets;
    private String acc_name;
    private ArrayList<SpotAcceptanceState> acceptanceStates;
    private boolean isSuccessful = true;

    // If the condition is just the boolean
    private boolean degenerateSpot = false;

    // The string actually written in the file
    private String outputString;

    public SpotInfo(String acc_name) {
        this.acc_name = acc_name;
        this.acceptanceStates = new ArrayList<>();
    }

    public void addAcceptanceState(SpotAcceptanceState acceptanceState) {
        acceptanceStates.add(acceptanceState);
    }

    public void setNumberAcceptanceSets (int numberAcceptanceSets) {
        this.numberAcceptanceSets = numberAcceptanceSets;
    }

    public void setAcceptanceStatesSpecification(String specification) {
        this.outputString = specification;
    }

    int getNumberAcceptanceSets() {
        return numberAcceptanceSets;
    }

    String getAcc_name() {
        return acc_name;
    }

    String getOutputString() {
        return outputString;
    }

    public ArrayList<SpotAcceptanceState> getAcceptanceStates() {
        return acceptanceStates;
    }

    public void setBooleanAcceptanceState(){
        degenerateSpot = true;
    }


    // Check that the number of acceptance states is coherent
    public boolean checkStatesNumber() {
        if (degenerateSpot) {
            if (acceptanceStates.size() != 1 || numberAcceptanceSets != 0) {
                isSuccessful = false;
                System.out.println("Degenerate conditions require 0 acceptance states!");
            }
        } else if (acceptanceStates.size() != numberAcceptanceSets) {
                isSuccessful = false;
        }
        return isSuccessful;
    }
}
