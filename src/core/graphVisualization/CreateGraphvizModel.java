package core.graphVisualization;

import core.graphBuilding.GraphReaction;
import core.graphBuilding.Vertex;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizCmdLineEngine;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
                    int labelLength = nodeLabel.length();
                    mutNode(Integer.toString(x.getVertexId())).attrs().add("label", nodeLabel).add("margin",adjustMargins(labelLength));
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
            // TODO : I could let users to choose
            //Graphviz.fromGraph(g).width((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File(modelName + "/" + modelName +".svg"));
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(modelName + "/" + modelName +".png"));
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


        MutableGraph g1 = buildReactionGraph(redexGraph,colorHashMap,ruleName + " : Redex");
        MutableGraph g2 = buildReactionGraph(reactumGraph,colorHashMap,ruleName + " : Reactum");

        try {
            /* OLD SVG PRINTING

             Graphviz.fromGraph(g1).width((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())
             .height((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight())
             .render(Format.SVG).toFile(new File(modelName +"/" + ruleName + " - Redex" + ".svg"));
             Graphviz.fromGraph(g2).width((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())
             .height((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight())
             .render(Format.SVG).toFile(new File(modelName + "/" + ruleName + " - Reactum" + ".svg"));

             */

        BufferedImage graphvizGraph1 = Graphviz.fromGraph(g1).render(Format.PNG).toImage();
        BufferedImage graphvizGraph2 = Graphviz.fromGraph(g2).render(Format.PNG).toImage();
        int maxHeight = Math.max(graphvizGraph1.getHeight(),graphvizGraph2.getHeight());
        BufferedImage mergedImage = new BufferedImage( graphvizGraph1.getWidth()+graphvizGraph2.getWidth(),  maxHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D finalPicture = mergedImage.createGraphics();
        finalPicture.setPaint(java.awt.Color.WHITE);
        finalPicture.fillRect(0, 0, graphvizGraph1.getWidth()+graphvizGraph2.getWidth(), maxHeight);
        finalPicture.drawImage(graphvizGraph1,null,0,0);
        finalPicture.drawImage(graphvizGraph2,null,graphvizGraph1.getWidth(),0);
        finalPicture.dispose();
        ImageIO.write(mergedImage,"png", new File(modelName + "/" + ruleName + ".png"));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    private MutableGraph buildReactionGraph(Multigraph<Vertex,DefaultEdge> currentGraph, HashMap<String,Color> colorHashMap, String ruleName) {
       MutableGraph g = mutGraph("Reactions").setDirected(true).use((gr, ctx) -> {

           // Title node
           MutableNode title = mutNode(ruleName).attrs().add(Shape.RECTANGLE).add("fontsize",16)
                   .add("margin",adjustMargins(ruleName.length()));

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
                        mutNode(nodeId).attrs().add("label", nodeLabel).attrs().add("margin",adjustMargins(nodeLabel.length()));
                    } else {
                        nodeAttrs().add(Shape.RECTANGLE);
                        mutNode(nodeId).attrs().add("label", nodeLabel).add("margin",adjustMargins(nodeLabel.length()));
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

    // Automatically adjusts the size of margins according to text length
    private String adjustMargins(double labelLength) {
        if(labelLength<10)
            return("0.2,0.1");
        else if(labelLength<18)
            return("0.3,0.1");
        else {
            double differential = (labelLength / 6) * 0.1;
            return(differential+",0.1");
        }
    }
}
