package core.graphs.customized.vertices;

public class TypedVertex extends Vertex {

    private String vertexType;

    public TypedVertex(int vertexId, String vertexType,String vertexLabel, boolean control){
        this.vertexId = vertexId;
        this.vertexType = vertexType;
        this.vertexLabel = vertexLabel;
        this.control = control;
    }

    public String getVertexType() {
        return vertexType;
    }

}
