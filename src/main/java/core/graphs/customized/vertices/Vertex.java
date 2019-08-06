package core.graphs.customized.vertices;


public class Vertex {

    private int vertexId;
    private String vertexLabel;
    private boolean control;
    private boolean activeControl;
    private boolean outerName;

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

}
