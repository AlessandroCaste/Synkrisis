package core.graphs.visualization;

import core.graphs.customized.TransitionGraph;
import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizCmdLineEngine;
import guru.nidi.graphviz.engine.GraphvizException;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static guru.nidi.graphviz.model.Factory.*;

public class PrintTransition extends AbstractPrinter implements Runnable {

    private TransitionGraph transitionGraph;
    private String modelName;
    private static Logger logger = Logger.getLogger("Report");

    public PrintTransition(TransitionGraph transitionGraph){
        this.transitionGraph = transitionGraph;
        this.modelName = transitionGraph.getModelName();
    }

    public void run() {
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> jgraphGraph = transitionGraph.getGraph();
        logger.log(Level.INFO,"Graphviz transition drawing started");
        MutableGraph transitionOutputGraph =
                mutGraph("Transitions").setDirected(true).use((gr, ctx) -> {
                    Graphviz.useEngine(new GraphvizCmdLineEngine());
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
                    for (TransitionEdge edge : jgraphGraph.edgeSet()) {
                        TransitionVertex edgeSource = jgraphGraph.getEdgeSource(edge);
                        TransitionVertex edgeTarget = jgraphGraph.getEdgeTarget(edge);
                        //mutNode()
                        linkAttrs().add("label",edge.getLabel()
                                + " ("
                                + jgraphGraph.getEdgeWeight(edge) + ")");
                        if (!Graphs.vertexHasSuccessors(jgraphGraph, edgeTarget))
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
                    mutNode(modelName + " Transition").attrs().add(Shape.RECTANGLE).add("margin",adjustMargins((modelName+" Transition").length()));
                    graphAttrs().add("rank","same");

                    // Displaying id->labels association
                    StringBuilder labels = new StringBuilder();
                    for (TransitionVertex v : jgraphGraph.vertexSet()) {
                        labels.append("#").append(v.getVertexID()).append(": ").append(v.getLabel()).append("\n");
                        //TODO properties
                        //labels.append(v.getPropertiesString());
                    }

                    labels.append("\n\nList of markers by state:\n");
                    for(TransitionVertex tv : jgraphGraph.vertexSet()){
                        String markers = transitionGraph.markersInVertices(tv);
                        if(!markers.isEmpty())
                            labels.append("#").append(tv.getVertexID()).append(" : ").append(markers).append("\n");
                    }

                    graphAttrs().add("labelloc","b");
                    graphAttrs().add("label", labels.toString());

                });
        try {
            // I calculate the shrinking factor for long labels
            int maxSize = 1;
            for(TransitionVertex v : jgraphGraph.vertexSet()) {
                int length = v.getLabel().length();
                if(length > maxSize)
                    maxSize = length;
            }
            // Temporary solution
            double factor = 1;
            if(75 <= maxSize && maxSize <= 105)
                factor = 0.85;
            if(maxSize > 105)
                factor = 0.75;
            mergeGraphs(modelName,transitionOutputGraph,labelsGraph,null,factor);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Impossible to draw the transition graph");
        } catch (GraphvizException e) {
            System.out.println("[GRAPHVIZ ERROR] Can't print transition graph; check the log for further info\"");
            logger.log(Level.SEVERE, "Impossible to draw transition graph." +
                    "Graphviz was not properly configured or, more probably, transition printing run out of memory\nStack trace: " + e.getMessage());
        }
    }

}
