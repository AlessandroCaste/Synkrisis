package core.graphModels.verticesAndEdges;

import java.util.ArrayList;

public class TransitionVertex {

    private int vertexID;
    private String label;
    private ArrayList<Integer> properties;

    public TransitionVertex(int vertexID, String label, ArrayList<Integer> properties) {
        this.vertexID = vertexID;
        this.label = label;
        this.properties = properties;
    }

    public int getVertexID() {
        return vertexID;
    }

    public String getLabel() {
        return label;
    }

    public String getProperties() {
        StringBuilder builder = new StringBuilder();
        for(int value : properties)
            builder.append(value).append(" ");
        return builder.toString();
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProperties(ArrayList<Integer> properties) {
        this.properties = properties;
    }

}
