package core;

import core.graphBuilding.GraphBuildingVisitor;
import core.graphBuilding.GraphsCollection;
import core.setup.Bigmc;
import core.setup.ProcessTransition;
import core.setup.SetupSynk;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Main {

    private static String filename;
    private static String modelName;
    private static ParseTree modelTree;
    private static boolean acceptableModel;

    private static Logger logger = Logger.getLogger("Report");
    private static FileHandler fh;

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {

        if (args.length > 0) {
            filename = args[0];
            File inputFile = new File(filename);
            setupLogger(inputFile.getName());
            SetupSynk initialization = new SetupSynk(inputFile); // Initializing lexer, tokens etc
            if(initialization.isSuccessfulSetup()) {
                modelTree = initialization.getModelTree();
                acceptableModel = syntaxAnalysis(inputFile.getName());   // String is required to check model name against filename
                if (acceptableModel) {
                    graphvizModel();
                    new Bigmc(filename,modelName); // Running bigmc and parsing the results
                    new ProcessTransition(modelName); // Translating the transition graph to a jgrapht graph
                } else
                System.out.println("Error in syntax analysis: processing can't go any further");
            }
            else {
                System.out.println("Model printing and transition generation can't proceed");
            }
        }
    }

    // Syntax Visitor execution
    private static boolean syntaxAnalysis(String inputFileName) {
        logger.log(Level.INFO,"Syntax visitor started");
        SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.visit(modelTree);
        modelName = syntaxVisitor.getModelName();
        if (!inputFileName.equals(modelName+".bigraph")) {
            System.out.println("[FATAL ERROR] File name and model names do not match : " + inputFileName + " vs " + modelName +".bigraph");
            logger.log(Level.SEVERE,"Execution suspended since model name and file name do not match: " + inputFileName + " vs " + modelName +".bigraph.\nCan't run visitor until it's fixed");
            return false;
        }
        System.out.println(syntaxVisitor.getParseResult());
        logger.log(Level.INFO,"Syntax visitor completed");
        return syntaxVisitor.getAcceptableModel();
    }

    // Outputting pictures!
    private static void graphvizModel() {
        logger.log(Level.INFO,"Jgraph translation from parsetree started");
        GraphBuildingVisitor graphvizVisitor = new GraphBuildingVisitor();
        graphvizVisitor.visit(modelTree);
        logger.log(Level.INFO, "Jgraph translation from parsetree completed. Launching graphviz printing...");
        GraphsCollection.getInstance().printModel();
    }

    private static void setupLogger(String filename) {
        try {
            // This block configure the logger with handler and formatter
            logger.setUseParentHandlers(false);
            filename = FilenameUtils.removeExtension(filename);
            deleteDirectory(filename+"/");
            //noinspection ResultOfMethodCallIgnored
            new File(filename).mkdirs();
            fh = new FileHandler(filename + "/" + filename +".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // Here starts the logging
            logger.info("Log started");

        } catch (SecurityException | IOException e) {
            System.out.println("[FATAL ERROR] Can't setup the logger");
            logger.log(Level.SEVERE, "Error raised while initializing " + filename + " directory and the logging procedures" + "\nStack trace: " + e.getMessage());
        }
    }

    private static void deleteDirectory(String filename) throws IOException {
        File tempFile = new File(filename);
        boolean exists = tempFile.exists();
        if(exists)
            FileUtils.deleteDirectory(new File(filename));
    }

}
