package core.exporting.prismExporting;

class MDPReactum {

    private int targetID;
    private double weight;

    MDPReactum(int targetID, double weight) {
        this.targetID = targetID;
        this.weight = weight;
    }

    String reactionToString() {
        return targetID + " " + weight;
    }

}
