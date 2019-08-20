package core.graphs.customized.vertices;


public class Vertex {

    int vertexId;
    String vertexLabel;
    boolean control;
    boolean activeControl;
    boolean outerName;

    Vertex(){}

    public Vertex(int vertexId,String vertexLabel,boolean control) {
        this.vertexId = vertexId;
        this.vertexLabel = vertexLabel;
        this.control = control;
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
