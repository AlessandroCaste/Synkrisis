package core.graphs.visualization;

import core.graphs.specificationgraph.Vertex;
import core.graphs.storing.RedexReactumPair;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizCmdLineEngine;
import guru.nidi.graphviz.engine.GraphvizException;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static guru.nidi.graphviz.model.Factory.*;

public class PrintReaction extends AbstractPrinter implements Runnable {


    private static Logger logger = Logger.getLogger("Report");
    private String modelName;
    private RedexReactumPair gr;

    public PrintReaction(RedexReactumPair gr,String modelName){
        this.gr = gr;
        this.modelName = modelName;
    }

    public void run() {
        logger.log(Level.INFO,"Graphviz reactions drawing started");
        Graphviz.useEngine(new GraphvizCmdLineEngine());
        HashMap<String, Color> colorHashMap = new HashMap<>();

        DirectedMultigraph<Vertex, DefaultEdge> redexGraph = gr.getRedex();
        DirectedMultigraph<Vertex, DefaultEdge>  reactumGraph = gr.getReactum();
        String ruleName = gr.getRulename();


        MutableGraph g1 = buildReactionGraph(redexGraph,colorHashMap,ruleName + " : Redex");
        MutableGraph g2 = buildReactionGraph(reactumGraph,colorHashMap,ruleName + " : Reactum");

        try {
            mergeGraphs(modelName,g1,g2,true,ruleName,1);
        } catch (IOException e) {
            System.out.println("[GRAPHVIZ ERROR] Can't print out reactions");
            logger.log(Level.SEVERE,"Impossible to draw reaction graphs " + ruleName + "\nStack trace: " + e.getMessage());
        } catch (GraphvizException e) {
            System.out.println("[GRAPHVIZ ERROR] Can't print reaction " + ruleName +"; check the log for further info");
            logger.log(Level.SEVERE,"Impossible to draw reaction graphs " + ruleName + "\n" +
                    "This was probably due to problems configuring graphviz or because printing run out of memory;" +
                    "Check if the same kind of error happened for model and transition graph\nStack trace: " + e.getMessage());
        }
    }

    private MutableGraph buildReactionGraph(DirectedMultigraph<Vertex, DefaultEdge>  currentGraph, HashMap<String,Color> colorHashMap, String ruleName) {
        MutableGraph g = mutGraph("Reactions").setDirected(true).use((gr, ctx) -> {

            // Counter to keep adding new nodes ids
            ArrayList<Vertex> vertices = new ArrayList<>(currentGraph.vertexSet());
            int counter = 0;
            for(Vertex v : vertices) {
                if(v.getVertexId() > counter)
                    counter = v.getVertexId();
            }

            // Title node
            MutableNode title = mutNode(ruleName).attrs().add(Shape.RECTANGLE).add("fontsize",16)
                    .add("margin",adjustMargins(ruleName.length()));

            //  Adjusting shapes
            nodeAttrs().add(Shape.RECTANGLE);
            nodeAttrs().add("style", "rounded");
            linkAttrs().add("arrowsize", 0.8);

            // Adding Nodes (first standard modelgraph, then name modelgraph)
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
                }
            }

            // Adding edges
            for (DefaultEdge edge : currentGraph.edgeSet()) {
                String sourceId = Integer.toString(currentGraph.getEdgeSource(edge).getVertexId());
                String targetId = Integer.toString(currentGraph.getEdgeTarget(edge).getVertexId());
                String nodeLabel = currentGraph.getEdgeTarget(edge).getLabel();
                if (currentGraph.getEdgeTarget(edge).isControl()) {
                    linkAttrs().add("dir", "forward");
                    linkAttrs().add("arrowhead", "normal");
                    linkAttrs().add("color", "black");
                    linkAttrs().add("style", "solid");
                    mutNode(sourceId).addLink(mutNode(targetId));
                    // Names share colors across redex and reactum
                } else {
                    Color customColor;
                    counter++;
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
                    linkAttrs().add("arrowhead", "dot");
                    linkAttrs().add(customColor);
                    if (nodeLabel.contains("@")) {
                        mutNode(Integer.toString(counter)).attrs().add(Shape.DOUBLE_CIRCLE)
                                .attrs().add(customColor)
                                .attrs().add("label", nodeLabel.replace("@",""))
                                .attrs().add("size", 0.8);
                        mutNode(sourceId).addLink(mutNode(Integer.toString(counter)));
                    } else {
                        mutNode(Integer.toString(counter)).attrs().add(Shape.CIRCLE)
                                .attrs().add(customColor)
                                .attrs().add("label", nodeLabel)
                                .attrs().add("size", 0.8);
                        mutNode(sourceId).addLink(mutNode(Integer.toString(counter)));
                    }
                }
            }
        });
        return g;
    }



}
