package core.graphVisualization;

import core.graphBuilding.EdgeTransitionGraph;
import core.graphBuilding.GraphReaction;
import core.graphBuilding.TransitionVertex;
import core.graphBuilding.Vertex;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizException;
import guru.nidi.graphviz.engine.GraphvizV8Engine;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.apache.commons.lang3.SystemUtils;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static guru.nidi.graphviz.model.Factory.*;

public class CreateGraphvizModel {

    private static CreateGraphvizModel instance;

    private String modelName;

    private static Logger logger = Logger.getLogger("Report");

    private CreateGraphvizModel() {}

    public static CreateGraphvizModel getInstance() {
        if(instance == null)
            return (instance = new CreateGraphvizModel());
        return instance;
    }

    public void createModel(Multigraph<Vertex, DefaultEdge> currentGraph) {
        logger.log(Level.INFO,"Model drawing started");
        Graphviz.useEngine(new GraphvizV8Engine());

        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {

            // Title node
            MutableNode title = mutNode(modelName + " model").attrs().add(Shape.RECTANGLE).add("fontsize",16)
                    .add("margin",adjustMargins(modelName.length()));

            // Required only for better spacing
            mutNode("a").add(Shape.RECTANGLE).add("style","invis");

            //  Adjusting shapes
            nodeAttrs().add("shape","box");
            nodeAttrs().add("style", "rounded");
            linkAttrs().add("arrowsize", 0.8);

            // Adding Vertices
            for (Vertex x : currentGraph.vertexSet()) {
                if (x.isControl()) {
                    String nodeLabel = x.getLabel();
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
                                    .attrs().add("label", edgeTarget.getLabel()));
                }
            }
        });
        try {
            // TODO : I could let users to choose
            //Graphviz.fromGraph(g).width((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File(modelName + "/" + modelName +".svg"));
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(modelName + "/" + modelName +".png"));
            logger.log(Level.INFO,"Graphviz model successfully drawn");
        } catch (IOException e) {
            System.out.println("[GRAPHVIZ ERROR] Can't print out model");
            logger.log(Level.SEVERE,"Impossible to draw the model\nStack trace: " + e.getMessage());
        } catch(guru.nidi.graphviz.engine.GraphvizException e) {
            System.out.println("Couldn't find dot: unable to print model and reactions!");
            if(SystemUtils.IS_OS_WINDOWS)
                System.out.println("Be sure to add graphviz folder to your path before trying again");
            else
                System.out.println("Install graphviz before trying again");
            logger.log(Level.SEVERE,"Couldn't find graphviz, so no models can be output. Check you've installed it and, if you're using windows, that you've added to your path\nStack trace: " + e.getMessage());
        }
        logger.log(Level.INFO,"Model drawing completed");
    }


   public void createReactions(GraphReaction gr) {
        logger.log(Level.INFO,"Graphviz reactions drawing started");
        Graphviz.useEngine(new GraphvizV8Engine());
        HashMap<String,Color> colorHashMap = new HashMap<>();

        Multigraph<Vertex, DefaultEdge> redexGraph = gr.getRedex();
        Multigraph<Vertex, DefaultEdge>  reactumGraph = gr.getReactum();
        String ruleName = gr.getRulename();


        MutableGraph g1 = buildReactionGraph(redexGraph,colorHashMap,ruleName + " : Redex");
        MutableGraph g2 = buildReactionGraph(reactumGraph,colorHashMap,ruleName + " : Reactum");

        try {
            mergeGraphs(g1,g2,ruleName);
            } catch (IOException e) {
                System.out.println("[GRAPHVIZ ERROR] Can't print out reactions");
                logger.log(Level.SEVERE,"Impossible to draw reaction graphs " + ruleName + "\nStack trace: " + e.getMessage());
            } catch (GraphvizException e) {
                System.out.println("[GRAPHVIZ ERROR] Can't print out reactions, probably run out of memory: model is too big!");
                logger.log(Level.SEVERE,"Impossible to draw reaction graphs " + ruleName + "\nStack trace: " + e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    private MutableGraph buildReactionGraph(Multigraph<Vertex, DefaultEdge>  currentGraph, HashMap<String,Color> colorHashMap, String ruleName) {
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
                String nodeLabel = v.getLabel();
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
                String targetLabel = currentGraph.getEdgeTarget(edge).getLabel();
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

    public void createTransition(DirectedMultigraph<TransitionVertex, EdgeTransitionGraph> currentGraph) {
        logger.log(Level.INFO,"Graphviz transition drawing started");
        MutableGraph transitionGraph =
                mutGraph("Transitions").setDirected(true).use((gr, ctx) -> {
                    Graphviz.useEngine(new GraphvizV8Engine());
                    graphAttrs().add("rank","same");

                    //  Adjusting shapes
                    nodeAttrs().add("penwidth", 2.0);
                    nodeAttrs().add(Color.LIGHTBLUE1);
                    nodeAttrs().add("fontcolor", "black");
                    linkAttrs().add("arrowsize", 0.7);
                    linkAttrs().add("arrowhead", "normal");
                    linkAttrs().add(Color.GREY3);
                    linkAttrs().add("style", "normal");

                     // Adding edges
                    for (EdgeTransitionGraph edge : currentGraph.edgeSet()) {
                        TransitionVertex edgeSource = currentGraph.getEdgeSource(edge);
                        TransitionVertex edgeTarget = currentGraph.getEdgeTarget(edge);
                        linkAttrs().add("label",edge.getLabel());
                        if (!Graphs.vertexHasSuccessors(currentGraph, edgeTarget))
                            mutNode(Integer.toString(edgeSource.getVertexID())).addLink(
                                    mutNode(Integer.toString(edgeTarget.getVertexID()))
                                            .attrs().add(Shape.CIRCLE)
                                            .attrs().add(Color.DEEPSKYBLUE)
                                            .attrs().add("size", 1.2));
                        else
                            mutNode(Integer.toString(edgeSource.getVertexID())).addLink(
                                    mutNode(Integer.toString(edgeTarget.getVertexID()))
                                            .attrs().add(Shape.CIRCLE)
                                            .attrs().add(Color.LIGHTGREY)
                                            .attrs().add("size", 0.9));
                    }
                 });
        // Labels are kept in a different graph for better graph results
        MutableGraph labelsGraph =
                mutGraph("labels").setDirected(true).use((gr, ctx) -> {
                    linkAttrs().add("constraint","false");
                    mutNode(modelName + " Transition").attrs().add(Shape.RECTANGLE);
                    graphAttrs().add("rank","same");

                    // Displaying id->labels association
                    StringBuilder labels = new StringBuilder();
                    for (TransitionVertex v : currentGraph.vertexSet()) {
                        labels.append("#").append(v.getVertexID()).append(": ").append(v.getLabel()).append("\n");
                    }
                    graphAttrs().add("labelloc","b");
                    graphAttrs().add("label", labels.toString());
                });
        try {
            mergeGraphs(transitionGraph,labelsGraph,null);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Impossible to draw the transition graph");
        } catch (GraphvizException e) {
            System.out.println("[GRAPHVIZ ERROR] Can't print out transition, probably run out of memory: model is too big!");
            logger.log(Level.SEVERE, "Impossible to draw transition graph\nStack trace: " + e.getMessage());
        }
    }

    // This function merges two graphs into a single png image
    // Rule name arg is set to null in case we're merging a transition graph and its labels
    private void mergeGraphs(MutableGraph g1, MutableGraph g2, String ruleName) throws IOException {
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
        if(ruleName != null) {
            new File(modelName + "/" + "rules").mkdir();
            ImageIO.write(mergedImage,"png", new File(modelName + "/rules/" + ruleName + ".png"));
        } else {
            ImageIO.write(mergedImage, "png", new File(modelName + "/" + "transition.png"));
            logger.log(Level.INFO,"Transition graph successfully drawn");
        }
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
