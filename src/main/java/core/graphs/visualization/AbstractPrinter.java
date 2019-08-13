package core.graphs.visualization;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AbstractPrinter {

    private static Logger logger = Logger.getLogger("Report");

    abstract void run();

    // This function merges two graphs into a single png image
    // Rule name arg is set to null in case we're merging a transition graph and its labels
    void mergeGraphs(String modelName, MutableGraph g1, MutableGraph g2, String ruleName, double factor) throws IOException {
        BufferedImage graphvizGraph1 = Graphviz.fromGraph(g1).render(Format.PNG).toImage();
        BufferedImage graphvizGraph2;
        if(factor!=1)
            graphvizGraph2 = Graphviz.fromGraph(g2).fontAdjust(factor).render(Format.PNG).toImage();
        else
            graphvizGraph2 = Graphviz.fromGraph(g2).render(Format.PNG).toImage();
        int maxHeight = Math.max(graphvizGraph1.getHeight(),graphvizGraph2.getHeight());
        BufferedImage mergedImage = new BufferedImage( graphvizGraph1.getWidth()+graphvizGraph2.getWidth(),  maxHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D finalPicture = mergedImage.createGraphics();
        finalPicture.setPaint(java.awt.Color.WHITE);
        finalPicture.fillRect(0, 0, graphvizGraph1.getWidth()+graphvizGraph2.getWidth()*2, maxHeight);
        finalPicture.drawImage(graphvizGraph1,null,0,0);
        finalPicture.drawImage(graphvizGraph2,null,graphvizGraph1.getWidth(),0);
        finalPicture.dispose();
        if(ruleName != null) {
            String rulePath = modelName + "/rules";
            File ruleFolder = new File(rulePath);
            if(!ruleFolder.exists()) {
                if(!ruleFolder.mkdir()) {
                    System.out.println("Can't create \"rules\" folder for graphviz pictures!");
                    logger.log(Level.WARNING, "Can't create rules folder. A hideous bug");
                }
            }
            ImageIO.write(mergedImage,"png", new File(rulePath + "/" + ruleName + ".png"));
        } else {
            ImageIO.write(mergedImage, "png", new File(modelName + "/" + "transition.png"));
            logger.log(Level.INFO,"Transition graph successfully drawn");
        }
    }


    // Automatically adjusts the size of margins according to text length
    String adjustMargins(double labelLength) {
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
