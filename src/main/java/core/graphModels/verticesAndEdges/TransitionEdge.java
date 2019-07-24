package core.graphModels.verticesAndEdges;

// Customized edge for further expansions
public class TransitionEdge {

    private String label;
    private double  weight;

    public TransitionEdge(String label, double weight)  {
        this.label = label;
        this.weight = weight;
    }

    public String getLabel(){
        return label;
    }

    public double getWeight() {
        return weight;
    }

}
