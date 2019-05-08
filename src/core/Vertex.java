package core;

class Vertex {

    private int vertexId;
    private String vertexLabel;
    private boolean control;

    Vertex(int vertexId,String vertexLabel,boolean control) {
        this.vertexId = vertexId;
        this.vertexLabel = vertexLabel;
        this.control = control;
    }

    int getVertexId() {
        return vertexId;
    }

    String getVertexLabel() {
        return vertexLabel;
    }

    boolean isControl() {
        return control;
    }

}
