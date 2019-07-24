package core.graphModels.verticesAndEdges;

public class TransitionEdge {

    private String label;
    private float  weight;

    public TransitionEdge(String label, float weight)  {
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
