package core.graphs.customized.vertices;


import java.util.ArrayList;
import java.util.TreeSet;

public class TransitionVertex {

    private String vertexID;
    private String label;
    private TreeSet<Integer> properties;
    //TODO Check this out
    private TreeSet<Integer> acceptanceStates;

    public TransitionVertex(String vertexID, String label, TreeSet<Integer> properties) {
        this.vertexID = vertexID;
        this.label = label;
        this.properties = properties;
        acceptanceStates = new TreeSet<>();
    }

    public String getVertexID() {
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

    public void prismNormalization(){
        // TODO a more efficient technique?
        ArrayList<Integer> listConversion = new ArrayList<>(properties);
        for (int i = 0; i < listConversion.size(); i += 1)
            listConversion.set(i, listConversion.get(i) + 2);
        properties = new TreeSet<>(listConversion);
    }

}
