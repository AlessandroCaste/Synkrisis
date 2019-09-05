package core.graphs.specificationgraph;


import java.util.HashMap;

public class Vertex {

    private int vertexId;
    private String vertexLabel;
    private boolean control;
    private boolean activeControl;
    private boolean outerName;
    // An arbitrary number of additional features of a vertex can be defined
    private HashMap<String,String> featuresMap;

    public Vertex(int vertexId,String vertexLabel,boolean control) {
        this.vertexId = vertexId;
        this.vertexLabel = vertexLabel;
        this.control = control;
        this.featuresMap = new HashMap<>();
    }

    public void setActiveControl() {
        if(control)
            this.activeControl = true;
    }

    public void setOuterName() {
        if(!control)
            this.outerName = true;
    }

    public int getVertexId() {
        return vertexId;
    }

    public String getLabel() {
        return vertexLabel;
    }

    public boolean isControl() {
        return control;
    }

    public void addFeature(String key,String value){
        this.featuresMap.put(key,value);
    }

    public String getFeature(String key){
        return featuresMap.get(key);
    }

    public boolean isActiveControl() { return activeControl; }

    public boolean isOuterName() { return outerName; }

    public void setVertexId(int vertexId) {
        this.vertexId = vertexId;
    }

    public void setVertexLabel(String vertexLabel) {
        this.vertexLabel = vertexLabel;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public void setActiveControl(boolean activeControl) {
        this.activeControl = activeControl;
    }

    public void setOuterName(boolean outerName) {
        this.outerName = outerName;
    }

}
