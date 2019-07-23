package core.graphModels.verticesAndEdges;


import java.util.TreeSet;

public class TransitionVertex {

    private int vertexID;
    private String label;
    private TreeSet<Integer> properties;
    private TreeSet<Integer> acceptanceSets;

    public TransitionVertex(int vertexID, String label, TreeSet<Integer> properties) {
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

    public String getPropertiesString() {
        StringBuilder builder = new StringBuilder();
        for(int value : properties)
            builder.append(value).append(" ");
        return builder.toString();
    }

    public TreeSet<Integer> getProperties() {
        return properties;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProperties(TreeSet<Integer> properties) {
        this.properties = properties;
    }

    public void addAcceptanceSets(int acceptanceSet) {
        if(acceptanceSets == null)
            acceptanceSets = new TreeSet<>();
        acceptanceSets.add(acceptanceSet);
    }
}
