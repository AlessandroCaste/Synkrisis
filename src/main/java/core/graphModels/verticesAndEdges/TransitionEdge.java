package core.graphModels.verticesAndEdges;

import org.jgrapht.graph.DefaultWeightedEdge;

public class TransitionEdge extends DefaultWeightedEdge {

    private String label;

    public TransitionEdge(String label)  {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }



}
