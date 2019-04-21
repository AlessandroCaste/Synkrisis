import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class CreateGraphvizModel {

    private static CreateGraphvizModel instance;

    private CreateGraphvizModel() {}


    static CreateGraphvizModel getInstance() {
        if(instance == null)
            return (instance = new CreateGraphvizModel());
        return instance;
    }

    void createModel(Multigraph<Integer, DefaultEdge> currentGraph, HashMap<Integer, String> nodeMapping) {

        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {

        //  Adjusting shapes
        nodeAttrs().add(Shape.RECTANGLE);
        nodeAttrs().add("style","rounded");

        // Adding Nodes and links
        for(int x : currentGraph.vertexSet()) {
            mutNode(Integer.toString(x));
            String nodeLabel = nodeMapping.get(x);
            mutNode(Integer.toString(x)).attrs().add("label",nodeLabel);
        }
        for(DefaultEdge edge: currentGraph.edgeSet()) {
            String edgeSource = currentGraph.getEdgeSource(edge).toString();
            String edgeTarget = currentGraph.getEdgeTarget(edge).toString();
            mutNode(edgeSource).addLink(mutNode(edgeTarget));
        }
        });
        try {
            Graphviz.fromGraph(g).width(400).render(Format.PNG).toFile(new File("example/ex1i.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
