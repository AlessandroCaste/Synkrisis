package core.graphModels;

import java.util.TreeSet;

class SpotAcceptanceState {

    private int acceptanceID;
    private TreeSet<String> markers;

    SpotAcceptanceState(int acceptanceID, TreeSet<String> markers) {
        this.acceptanceID = acceptanceID;
        this.markers = markers;
    }

    boolean compare(TreeSet<String> markers) {
        return this.markers.equals(markers);
    }
}
