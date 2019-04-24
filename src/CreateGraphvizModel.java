import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.awt.*;
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

    void createModel(Multigraph<Integer, DefaultEdge> currentGraph, HashMap<Integer, String> nodeMapping, HashMap<Integer,String> namesMapping, String name, boolean model) {

        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {

        //  Adjusting shapes
        nodeAttrs().add(Shape.RECTANGLE);
        nodeAttrs().add("style","rounded");
        linkAttrs().add("arrowsize",0.8);


            // Adding Nodes and links
        for(int x : currentGraph.vertexSet()) {
            if(nodeMapping.containsKey(x)) {
                String nodeLabel = nodeMapping.get(x);
                mutNode(Integer.toString(x)).attrs().add("label", nodeLabel);
            }
            else if(namesMapping.containsKey(x)) {
                String nameLabel = namesMapping.get(x);
                mutNode(Integer.toString(x)).attrs().add(Shape.CIRCLE)
                                            .attrs().add(Color.RED2)
                                            .attrs().add("size",0.8)
                                            .attrs().add("label",nameLabel);
            }
        }
        for(DefaultEdge edge: currentGraph.edgeSet()) {
            String edgeSource = currentGraph.getEdgeSource(edge).toString();
            String edgeTarget = currentGraph.getEdgeTarget(edge).toString();
            if(!namesMapping.containsKey(Integer.parseInt(edgeTarget))) {
                linkAttrs().add("arrowhead","normal");
                linkAttrs().add("color","black");
                linkAttrs().add("style","solid");
                mutNode(edgeSource).addLink(mutNode(edgeTarget));
            }
            else {
                linkAttrs().add("arrowhead","none");
                linkAttrs().add("color","red");
                linkAttrs().add("style","dotted");
                mutNode(edgeSource).addLink(mutNode(edgeTarget));
            }
        }
        });
        try {
            Graphviz.fromGraph(g).width((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File("example/"+name+".svg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
