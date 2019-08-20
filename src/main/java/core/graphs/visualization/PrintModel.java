package core.graphs.visualization;

import core.graphs.customized.vertices.Vertex;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizCmdLineEngine;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.apache.commons.lang3.SystemUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static guru.nidi.graphviz.model.Factory.*;

public class PrintModel extends AbstractPrinter implements Runnable {

    private DirectedMultigraph<Vertex, DefaultEdge> currentGraph;
    private String modelName;
    private static Logger logger = Logger.getLogger("Report");


    public PrintModel(DirectedMultigraph<Vertex, DefaultEdge> currentGraph,String modelName){
        this.currentGraph = currentGraph;
        this.modelName = modelName;
    }

    public void run() {
        logger.log(Level.INFO,"Model drawing started");
        Graphviz.useEngine(new GraphvizCmdLineEngine());

        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {


            // Counter to keep adding new nodes original node ids
            ArrayList<Vertex> vertices = new ArrayList<>(currentGraph.vertexSet());
            int counter = 0;
            for(Vertex v : vertices) {
                if(v.getVertexId() > counter)
                    counter = v.getVertexId();
            }


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
                    counter++;
                    linkAttrs().add("arrowhead", "none");
                    linkAttrs().add("color", "red");
                    linkAttrs().add("style", "dotted");
                    mutNode(Integer.toString(edgeSource.getVertexId())).addLink(
                            mutNode(Integer.toString(counter)).attrs().add(Shape.CIRCLE)
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
            System.out.println("[GRAPHVIZ ERROR] Can't print model; check the log for further info");
            if(SystemUtils.IS_OS_WINDOWS)
                System.out.println("Be sure to add graphviz folder to your path before trying again");
            else
                System.out.println("Install graphviz before trying again");
            logger.log(Level.SEVERE,"Couldn't find graphviz, so no models can be output. Check you've installed it and, if you're using windows, that you've added to your path\n" +
                    "Note that this may also have to do with graphs so big that run out of memory before printing out the model!\nStack trace: " + e.getMessage());
        }
        logger.log(Level.INFO,"Model drawing completed");
    }

}
