package core.graphVisualization;

import core.graphBuilding.GraphReaction;
import core.graphBuilding.Vertex;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizCmdLineEngine;
import guru.nidi.graphviz.engine.GraphvizJdkEngine;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static guru.nidi.graphviz.model.Factory.*;

public class CreateGraphvizModel {

    private static CreateGraphvizModel instance;

    private String modelName;

    private CreateGraphvizModel() {}

    public static CreateGraphvizModel getInstance() {
        if(instance == null)
            return (instance = new CreateGraphvizModel());
        return instance;
    }

    public void createModel(Multigraph<Vertex,DefaultEdge> currentGraph) {
        Graphviz.useEngine(new GraphvizCmdLineEngine());
        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {

            //  Adjusting shapes
            nodeAttrs().add("shape","box");
            nodeAttrs().add("style", "rounded");
            linkAttrs().add("arrowsize", 0.8);

            // Adding Vertices
            for (Vertex x : currentGraph.vertexSet()) {
                if (x.isControl()) {
                    String nodeLabel = x.getVertexLabel();

                    // Font size adjusting for more readable results
                    double labelLength = nodeLabel.length();
                    if(labelLength<10)
                        nodeAttrs().add("margin","0.2,0.1");
                    else if(labelLength<18)
                        nodeAttrs().add("margin","0.3,0.1");
                    else if(labelLength>18) {
                        double differential = (labelLength / 6) * 0.1;
                        nodeAttrs().add("margin",differential+",0.1");
                    }
                    mutNode(Integer.toString(x.getVertexId())).attrs().add("label", nodeLabel);
                }
            }
            // Adding edges
            for (DefaultEdge edge : currentGraph.edgeSet()) {
                Vertex edgeSource = currentGraph.getEdgeSource(edge);
                Vertex edgeTarget = currentGraph.getEdgeTarget(edge);
                if (edgeTarget.isControl()) {
                    linkAttrs().add("arrowhead", "normal");
                    linkAttrs().add("color", "black");
                    linkAttrs().add("style", "solid");
                    mutNode(Integer.toString(edgeSource.getVertexId())).addLink(mutNode(Integer.toString(edgeTarget.getVertexId())));
                } else {
                    linkAttrs().add("arrowhead", "none");
                    linkAttrs().add("color", "red");
                    linkAttrs().add("style", "dotted");
                    mutNode(Integer.toString(edgeSource.getVertexId())).addLink(
                            mutNode(Integer.toString(edgeTarget.getVertexId()))
                            .attrs().add(Shape.CIRCLE)
                                    .attrs().add(Color.RED2)
                                    .attrs().add("size", 0.8)
                                    .attrs().add("label", edgeTarget.getVertexLabel()));
                }
            }
        });
        try {
             Graphviz.fromGraph(g).width((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File(modelName + "/" + modelName +".svg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public void createReactions(GraphReaction gr) {
        Graphviz.useEngine(new GraphvizCmdLineEngine());
        HashMap<String,Color> colorHashMap = new HashMap<>();

        Multigraph<Vertex,DefaultEdge> redexGraph = gr.getRedex();
        Multigraph<Vertex,DefaultEdge> reactumGraph = gr.getReactum();
        String ruleName = gr.getRulename();


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

    private MutableGraph buildReactionGraph(Multigraph<Vertex,DefaultEdge> currentGraph, HashMap<String,Color> colorHashMap) {
       MutableGraph g = mutGraph("Reactions").setDirected(true).use((gr, ctx) -> {

            //  Adjusting shapes
            nodeAttrs().add(Shape.RECTANGLE);
            nodeAttrs().add("style", "rounded");
            linkAttrs().add("arrowsize", 0.8);

            // Adding Nodes (first standard vertices, then name vertices)
            for (Vertex v : currentGraph.vertexSet()) {
                String nodeLabel = v.getVertexLabel();
                String nodeId = Integer.toString(v.getVertexId());
                if (v.isControl()) {
                    if (nodeLabel.contains("$")) {
                        nodeAttrs().add(Shape.DOUBLE_OCTAGON);
                        mutNode(nodeId).attrs().add("label", nodeLabel).attrs();
                    } else {
                        nodeAttrs().add(Shape.RECTANGLE);
                        mutNode(nodeId).attrs().add("label", nodeLabel);
                    }
                } else {
                    Color customColor;
                    if(colorHashMap.containsKey(nodeLabel))
                        customColor = colorHashMap.get(nodeLabel);
                    else {
                        // New random colors are assigned to names
                        Random rand = new Random();
                        int rc = rand.nextInt();
                        int gc = rand.nextInt();
                        int bc = rand.nextInt();
                        customColor = Color.rgb(rc, gc, bc);
                        colorHashMap.put(nodeLabel, customColor);
                    }
                    mutNode(nodeLabel).attrs().add(Shape.CIRCLE)
                            .attrs().add(customColor)
                            .attrs().add("size", 0.8);
                }
            }

            // Adding edges
            for (DefaultEdge edge : currentGraph.edgeSet()) {
                String sourceId = Integer.toString(currentGraph.getEdgeSource(edge).getVertexId());
                String targetId = Integer.toString(currentGraph.getEdgeTarget(edge).getVertexId());
                String targetLabel = currentGraph.getEdgeTarget(edge).getVertexLabel();
                if (currentGraph.getEdgeTarget(edge).isControl()) {
                    linkAttrs().add("dir", "forward");
                    linkAttrs().add("arrowhead", "normal");
                    linkAttrs().add("color", "black");
                    linkAttrs().add("style", "solid");
                    mutNode(sourceId).addLink(mutNode(targetId));
                // Names share colors across redex and reactum
                } else {
                    linkAttrs().add("arrowhead", "dot");
                    Color color = colorHashMap.get(targetLabel);
                    linkAttrs().add(color);
                    mutNode(sourceId).addLink(mutNode(targetLabel));
                }
            }
        });
        return g;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
