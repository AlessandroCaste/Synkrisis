package core.exporting.prismExporting;

class MDPReactum {

    private String targetID;
    private double weight;

    MDPReactum(String targetID, double weight) {
        this.targetID = targetID;
        this.weight = weight;
    }

    String reactionToString() {
        return targetID + " " + weight;
    }

}
