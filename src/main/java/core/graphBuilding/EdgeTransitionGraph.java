package core.graphBuilding;

public class EdgeTransitionGraph {

    private String label;
    private float  weight;

    public EdgeTransitionGraph(String label, float weight)  {
        this.label = label;
        this.weight = weight;
    }

    public String getLabel(){
        return label;
    }

    public float getWeight() {
        return weight;
    }

}
