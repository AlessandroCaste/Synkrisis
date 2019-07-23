package core.graphModels;

import java.util.TreeSet;

public class SpotAcceptanceState {

    private int acceptanceStateID;
    private String acceptanceStateString;
    private TreeSet<Integer> positiveMarker;
    private TreeSet<Integer> negativeMarker;

    SpotAcceptanceState(int acceptanceStateID, String acceptanceStateString, TreeSet<Integer> positiveMarker,TreeSet<Integer> negativeMarker) {
        this.acceptanceStateID = acceptanceStateID;
        this.acceptanceStateString = acceptanceStateString;
        this.positiveMarker = positiveMarker;
        this.negativeMarker = negativeMarker;
    }

    int getAcceptanceStateID() {
        return acceptanceStateID;
    }

    public String getAcceptanceStateString() {
        return acceptanceStateString;
    }

    boolean verify(TreeSet<Integer> positiveMarker, TreeSet<Integer> negativeMarker) {
        return (this.positiveMarker.equals(positiveMarker) && this.negativeMarker.equals(negativeMarker));
    }
}
