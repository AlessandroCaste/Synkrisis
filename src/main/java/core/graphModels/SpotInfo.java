package core.graphModels;

import java.util.ArrayList;

public class SpotInfo {

    private int numberAcceptanceSets;
    private String acc_name;
    private ArrayList<SpotAcceptanceState> acceptanceStates;
    // The string actually writen in the file
    private String outputString;

    SpotInfo(String acc_name) {
        this.acc_name = acc_name;
        this.acceptanceStates = new ArrayList<>();
    }

    void addAcceptanceState(SpotAcceptanceState acceptanceState) {
        acceptanceStates.add(acceptanceState);
    }

    void setNumberAcceptanceSets (int numberAcceptanceSets) {
        this.numberAcceptanceSets = numberAcceptanceSets;
    }

    void setAcceptanceStatesSpecification(String specification) {
        this.outputString = specification;
    }

    public int getNumberAcceptanceSets() {
        return numberAcceptanceSets;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public String getOutputString() {
        return outputString;
    }

    public ArrayList<SpotAcceptanceState> getAcceptanceStates() {
        return acceptanceStates;
    }
}
