import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.model.Factory.mutNode;

class CreateGraphvizModel {

    private static CreateGraphvizModel instance;

    // Attributes for rules drawing
    private HashMap<Integer, String> nodeMapping;
    private HashMap<Integer,String> namesMapping;
    private String modelName;

    private CreateGraphvizModel() {}

    static CreateGraphvizModel getInstance() {
        if(instance == null)
            return (instance = new CreateGraphvizModel());
        return instance;
    }

    void createModel(Multigraph<Integer, DefaultEdge> currentGraph, HashMap<Integer, String> nodeMapping, HashMap<Integer,String> namesMapping) {
        Graphviz.useEngine(new GraphvizCmdLineEngine());
        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {

            //  Adjusting shapes
            nodeAttrs().add(Shape.RECTANGLE);
            nodeAttrs().add("style", "rounded");
            linkAttrs().add("arrowsize", 0.8);

            // Adding Vertices
            for (int x : currentGraph.vertexSet()) {
                if (nodeMapping.containsKey(x)) {
                    String nodeLabel = nodeMapping.get(x);
                    mutNode(Integer.toString(x)).attrs().add("label", nodeLabel);
                } else if (namesMapping.containsKey(x)) {
                    String nameLabel = namesMapping.get(x);
                    mutNode(Integer.toString(x)).attrs().add(Shape.CIRCLE)
                            .attrs().add(Color.RED2)
                            .attrs().add("size", 0.8)
                            .attrs().add("label", nameLabel);
                }
            }
            // Adding edges
            for (DefaultEdge edge : currentGraph.edgeSet()) {
                String edgeSource = currentGraph.getEdgeSource(edge).toString();
                String edgeTarget = currentGraph.getEdgeTarget(edge).toString();
                if (!namesMapping.containsKey(Integer.parseInt(edgeTarget))) {
                    linkAttrs().add("arrowhead", "normal");
                    linkAttrs().add("color", "black");
                    linkAttrs().add("style", "solid");
                    mutNode(edgeSource).addLink(mutNode(edgeTarget));
                } else {
                    linkAttrs().add("arrowhead", "none");
                    linkAttrs().add("color", "red");
                    linkAttrs().add("style", "dotted");
                    mutNode(edgeSource).addLink(mutNode(edgeTarget));
                }
            }
        });
        try {
             Graphviz.fromGraph(g).width((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File(modelName + "/" + modelName +".svg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void createReactions(Multigraph<Integer,DefaultEdge> redexGraph,Multigraph<Integer,DefaultEdge> reactumGraph, HashMap<Integer, String> nodeMapping, HashMap<Integer,String> namesMapping, String ruleName) {
        Graphviz.useEngine(new GraphvizCmdLineEngine());
        HashMap<String,Color> colorHashMap = new HashMap<>();
        this.nodeMapping = nodeMapping;
        this.namesMapping = namesMapping;

        MutableGraph g1 = buildReactionGraph(redexGraph,colorHashMap);
        MutableGraph g2 = buildReactionGraph(reactumGraph,colorHashMap);

        try {
        Graphviz.fromGraph(g1).width((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())
                              .height((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight())
                               .render(Format.SVG).toFile(new File(modelName +"/" + ruleName + " - Redex" + ".svg"));
        Graphviz.fromGraph(g2).width((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())
                                                                      .height((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight())
                                                                      .render(Format.SVG).toFile(new File(modelName + "/" + ruleName + " - Reactum" + ".svg"));
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private MutableGraph buildReactionGraph(Multigraph<Integer,DefaultEdge> currentGraph,HashMap<String,Color> colorHashMap) {
        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {

            //  Adjusting shapes
            nodeAttrs().add(Shape.RECTANGLE);
            nodeAttrs().add("style", "rounded");
            linkAttrs().add("arrowsize", 0.8);

            // Adding Nodes (first standard vertices, then name vertices)
            for (int x : currentGraph.vertexSet()) {
                if (nodeMapping.containsKey(x)) {
                    String nodeLabel = nodeMapping.get(x);
                    if (nodeLabel.contains("$")) {
                        nodeAttrs().add(Shape.DOUBLE_OCTAGON);
                        mutNode(Integer.toString(x)).attrs().add("label", nodeLabel).attrs();
                    } else {
                        nodeAttrs().add(Shape.RECTANGLE);
                        mutNode(Integer.toString(x)).attrs().add("label", nodeLabel);
                    }
                } else if (namesMapping.containsKey(x)) {
                    Random rand = new Random();
                    int rc = rand.nextInt();
                    int gc = rand.nextInt();
                    int bc = rand.nextInt();
                    String nameLabel = namesMapping.get(x);
                    Color customColor;
                    if(colorHashMap.containsKey(nameLabel))
                        customColor = colorHashMap.get(nameLabel);
                    else {
                        customColor = Color.rgb(rc, gc, bc);
                        colorHashMap.put(nameLabel, customColor);
                    }
                    mutNode(nameLabel).attrs().add(Shape.CIRCLE)
                            .attrs().add(customColor)
                            .attrs().add("size", 0.8);
                }
            }
            // Adding edges
            for (DefaultEdge edge : currentGraph.edgeSet()) {
                String edgeSource = currentGraph.getEdgeSource(edge).toString();
                String edgeTarget = currentGraph.getEdgeTarget(edge).toString();
                String targetLabel = namesMapping.get(currentGraph.getEdgeTarget(edge));
                if (!namesMapping.containsKey(Integer.parseInt(edgeTarget))) {
                    linkAttrs().add("dir", "forward");
                    linkAttrs().add("arrowhead", "normal");
                    linkAttrs().add("color", "black");
                    linkAttrs().add("style", "solid");
                    mutNode(edgeSource).addLink(mutNode(edgeTarget));
                // Names share colors across redex and reactum
                } else {
                    linkAttrs().add("arrowhead", "dot");
                    Color color = colorHashMap.get(targetLabel);
                    linkAttrs().add(color);
                    mutNode(edgeSource).addLink(mutNode(namesMapping.get(currentGraph.getEdgeTarget(edge))));
                }
            }
        });
        return g;
    }

    void setFileName(String modelName) {
        this.modelName = modelName;
    }
}
