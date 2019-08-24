package core.exporting.spotExporting;

import java.util.TreeSet;

public class SpotAcceptanceState {

    private int acceptanceStateID;
    private String acceptanceStateString;
    private TreeSet<Integer> positiveMarker;
    private TreeSet<Integer> negativeMarker;

    public SpotAcceptanceState(int acceptanceStateID, String acceptanceStateString, TreeSet<Integer> positiveMarker,TreeSet<Integer> negativeMarker) {
        this.acceptanceStateID = acceptanceStateID;
        this.acceptanceStateString = acceptanceStateString;
        this.positiveMarker = positiveMarker;
        this.negativeMarker = negativeMarker;
    }

    // In case we submit t or f every state is an acceptance state
    public SpotAcceptanceState(String acceptanceStateString){
        this.acceptanceStateString = acceptanceStateString;
    }

    int getAcceptanceStateID() {
        return acceptanceStateID;
    }

    public String getAcceptanceStateString() {
        return acceptanceStateString;
    }

    public boolean verify(TreeSet<Integer> positiveMarker, TreeSet<Integer> negativeMarker) {
        return (this.positiveMarker.equals(positiveMarker) && this.negativeMarker.equals(negativeMarker));
    }

    // Method to compare if a vertex in transition graph matches the acceptance state
    // The vertex must contain all markers in 'positive' and no markers in negative
    // In case we used "t" or "f" then result will always be positive
    boolean verify(TreeSet<Integer> vertexMarkers) {
        if(positiveMarker==null && negativeMarker==null)
            return true;
        else {
            boolean containsPositive;
            boolean containsNegative = false;
            containsPositive = vertexMarkers.containsAll(positiveMarker);
            for (int marker : negativeMarker)
                if (vertexMarkers.contains(marker))
                    containsNegative = true;
            return (containsPositive & !containsNegative);
        }
    }

}
