package core.spotExporting;

import java.util.ArrayList;

public class SpotInfo {

    private int numberAcceptanceSets;
    private String acc_name;
    private ArrayList<SpotAcceptanceState> acceptanceStates;
    // The string actually writen in the file
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
}
