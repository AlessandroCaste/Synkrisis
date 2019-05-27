package core.graphBuilding;

import java.util.ArrayList;

public class VertexTransitionGraph {

    private String vertexID;
    private String label;
    private ArrayList<String> properties;

    public VertexTransitionGraph(String vertexID,String label,ArrayList<String> properties) {
        this.vertexID = vertexID;
        this.label = label;
        this.properties = properties;
    }

    public String getVertexID() {
        return vertexID;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setVertexID(String vertexID) {
        this.vertexID = vertexID;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }
}
