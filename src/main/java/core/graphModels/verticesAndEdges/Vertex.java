package core.graphModels.verticesAndEdges;


public class Vertex {

    private int vertexId;
    private String vertexLabel;
    private boolean control;

    public Vertex(int vertexId,String vertexLabel,boolean control) {
        this.vertexId = vertexId;
        this.vertexLabel = vertexLabel;
        this.control = control;

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

}
