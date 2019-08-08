package core.graphs.customized.vertices;


import java.util.TreeSet;

public class TransitionVertex {

    private int vertexID;
    private String label;
    private TreeSet<Integer> properties;
    private TreeSet<Integer> acceptanceStates;

    public TransitionVertex(int vertexID, String label, TreeSet<Integer> properties) {
        this.vertexID = vertexID;
        this.label = label;
        this.properties = properties;
        acceptanceStates = new TreeSet<>();
    }

    public int getVertexID() {
        return vertexID;
    }

    public String getLabel() {
        return label;
    }

    public String getPropertiesString() {
        StringBuilder builder = new StringBuilder();
        for(int value : properties)
            builder.append(value).append(" ");
        return builder.toString();
    }

    public TreeSet<Integer> getProperties() {
        return properties;
    }

    public void addAcceptanceState(int state) {
        this.acceptanceStates.add(state);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProperties(TreeSet<Integer> properties) {
        this.properties = properties;
    }


}