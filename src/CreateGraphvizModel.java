import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
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
import static guru.nidi.graphviz.model.Factory.mutNode;

public class CreateGraphvizModel {

    private static CreateGraphvizModel instance;

    // Attributes for rules drawing
    private HashMap<Integer, String> nodeMapping;
    private HashMap<Integer,String> namesMapping;
    private String ruleName;

    private CreateGraphvizModel() {}

    static CreateGraphvizModel getInstance() {
        if(instance == null)
            return (instance = new CreateGraphvizModel());
        return instance;
    }

    void createModel(Multigraph<Integer, DefaultEdge> currentGraph, HashMap<Integer, String> nodeMapping, HashMap<Integer,String> namesMapping, String name) {
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
            // Adding links
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
             Graphviz.fromGraph(g).width((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File("example/"+name+".svg"));
            Image i = Graphviz.fromGraph(g).width((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toImage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void createReactions(Multigraph<Integer,String> redexGraph,Multigraph<Integer,String> reactumGraph, HashMap<Integer, String> nodeMapping, HashMap<Integer,String> namesMapping, String ruleName) {
        HashMap<String,Color> colorHashMap = new HashMap<>();
        this.nodeMapping = nodeMapping;
        this.namesMapping = namesMapping;
        this.ruleName = ruleName;
        BufferedImage redex = null;
        BufferedImage redexAdjusted = null;
        BufferedImage reactum = null;
        BufferedImage reactumAdjusted = null;
        int width1 = 0;
        int height1 = 0;
        int width2 = 0;
        int height2 = 0;

        MutableGraph g1 = buildReactionGraph(redexGraph,nodeMapping,namesMapping,colorHashMap);
        MutableGraph g2 = buildReactionGraph(reactumGraph,nodeMapping,namesMapping,colorHashMap);

        //Graphviz.fromGraph(g).width((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File("example/" + reactionsNames.get(i) + "- Match" + ".svg"));
        redex = Graphviz.fromGraph(g1).render(Format.PNG).toImage();
        width1 = redex.getWidth();
        height1 = redex.getHeight();

        //Graphviz.fromGraph(g).width((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()).render(Format.SVG).toFile(new File("example/" + reactionsNames.get(i - 1) + "- Reactum" + ".svg"));
        reactum = Graphviz.fromGraph(g2).render(Format.PNG).toImage();
        width2 = reactum.getWidth();
        height2 = reactum.getHeight();

        int maxWidth = Math.max(width1,width2);
        int maxHeight = Math.max(height1,height2);

        redexAdjusted = Graphviz.fromGraph(g1).width(maxWidth).height(maxHeight).render(Format.PNG).toImage();
        reactumAdjusted = Graphviz.fromGraph(g2).width(maxWidth).height(maxHeight).render(Format.PNG).toImage();

        BufferedImage reaction = new BufferedImage(maxWidth + maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);

        boolean image1Drawn = reaction.createGraphics().drawImage(redexAdjusted, 0, 0, null); // 0, 0 are the x and y positions
        if(!image1Drawn) System.out.println("Problems drawing first image"); //where we are placing image1 in final image

        boolean image2Drawn = reaction.createGraphics().drawImage(reactumAdjusted, width1, 0, null); // here width is mentioned as width of
        if(!image2Drawn)
            System.out.println("Problems drawing second image"); // image1 so both images will come in same level

        File final_image = new File("example/test.png");

        boolean final_Image_drawing = false;

        try {
            final_Image_drawing = ImageIO.write(reaction, "png", final_image);
            if(!final_Image_drawing) System.out.println("Problems drawing final image");
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private MutableGraph buildReactionGraph(Multigraph<Integer,String> currentGraph,HashMap<Integer, String> nodeMapping, HashMap<Integer,String> namesMapping,HashMap<String,Color> colorHashMap) {
        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {

            //  Adjusting shapes
            nodeAttrs().add(Shape.RECTANGLE);
            nodeAttrs().add("style", "rounded");
            linkAttrs().add("arrowsize", 0.8);

            // Adding Nodes and links
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
                    Color customColor = Color.rgb(rc, gc, bc);
                    colorHashMap.put(nameLabel, customColor);    // Color is saved to be the same of arrows
                    mutNode(nameLabel).attrs().add(Shape.CIRCLE)
                            .attrs().add(customColor)
                            .attrs().add("size", 0.8);
                    //      .attrs().add("label", nameLabel);
                }
            }
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
                } else {
                    linkAttrs().add("arrowhead", "normal");
                    Color color = colorHashMap.get(targetLabel);
                    linkAttrs().add(color);
                    mutNode(edgeSource).addLink(mutNode(namesMapping.get(currentGraph.getEdgeTarget(edge))));
                }
            }
        });
        return g;
    }

}
