package core.graphBuilding;

import java.util.ArrayList;

public class VertexTransitionGraph {

    private int vertexID;
    private String label;
    private ArrayList<String> properties;

    public VertexTransitionGraph(int vertexID,String label,ArrayList<String> properties) {
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

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setVertexID(int vertexID) {
        this.vertexID = vertexID;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }

}
