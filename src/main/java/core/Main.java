package core;

import core.clishell.CLI;
import core.clishell.ExecutionSettings;
import core.clishell.InteractiveShell;
import core.graphModels.GraphBuildingVisitor;
import core.graphModels.TransitionDotImporter;
import core.graphModels.storing.GraphsCollection;
import core.setup.Bigmc;
import core.setup.ModelChecker;
import core.setup.SetupVisitor;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/* This is the application flow:
1) Read arguments from CLI
2) Syntax Analysis
3) If model can be submitted to bigmc it is, otherwise I use a visitor to strip elements
4) Model and reaction printing
5) Translation from transition graph into Jgraph
6) Translation from transition graph into PRISM model
 */

public class Main {

    private static String modelName;
    private static ParseTree modelTree;
    private static SyntaxVisitor syntaxVisitor;
    private static GraphsCollection  graphsCollection = GraphsCollection.getInstance();
    private static FileHandler fh;

    private static Logger logger = Logger.getLogger("Report");

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        System.out.println("****************************\n" +
                            "Synkrisis Toolchain (2019)\n" +
                           "****************************");
        if (args.length == 0)
            interactiveShell();
        else
            cli(args);
    }

    public static void execution(ExecutionSettings executionSettings) {
        File inputFile = new File(executionSettings.getFilePath());
        SetupVisitor initialization = new SetupVisitor(inputFile); // Initializing lexer, tokens etc
        if (initialization.isSuccessful()) {
            modelTree = initialization.getModelTree();
            boolean acceptableModel = syntaxAnalysis(executionSettings);
            setupLogger();
            if (acceptableModel) {
                // I translate the graph into a JgraphT model
                logger.log(Level.INFO, "Jgraph translation from parsetree started");
                GraphBuildingVisitor modelBuilder = new GraphBuildingVisitor();
                modelBuilder.visit(modelTree);
                logger.log(Level.INFO, "Jgraph translation from parsetree completed.");

                // Saving execution info from model analysis
                String propertiesString = modelBuilder.getPropertiesString();
                executionSettings.setSpotExportingReady(modelBuilder.isSpotReady());
                String spotErrorsString = modelBuilder.getSpotErrorsString().trim();

                if(executionSettings.isPrintModelEnabled())
                    graphPrinting();

                // If the model can't be submitted as-it-is then we must strip it of elements non compatible with bigmc
                ModelChecker modelChecker = new Bigmc(executionSettings, modelTree);
                if (modelChecker.needsTranslation())
                    modelChecker.translate();
                modelChecker.execute();

                // Running the model checker must give us back a transition graph, so that we parse results
                System.out.println("Generating the transition graph");
                if (executionSettings.isProcessTransitionOnly())
                    new TransitionDotImporter(modelName, true); // Translating the transition graph to a jgrapht graph
                else
                    new TransitionDotImporter(modelName, false);
                if (executionSettings.isPrintTransitionEnabled()) {
                    System.out.println("Printing the transition graph");
                    graphsCollection.printTransition();
                }
                if (executionSettings.isExportingEnabled()) {
                    System.out.println("\nMODEL EXPORTING");
                    System.out.println("****************");
                    // Model exporting
                    if (executionSettings.isSpotExportingEnabled() && executionSettings.isSpotExportingReady())
                        graphsCollection.exportToSpot(modelBuilder.getAcceptanceInfo());
                    if (executionSettings.isPrismExportingEnabled())
                        if (propertiesString != null)
                            graphsCollection.exportToPrism(propertiesString);
                        else
                            graphsCollection.exportToPrism("");

                    else if (executionSettings.isSpotExportingEnabled() && !executionSettings.isSpotExportingReady())
                        System.out.println("Can't proceed with SPOT exporting\n" + spotErrorsString);
                }
            } else
                System.out.println("Error in syntax analysis: processing can't go any further");
        }
    }

    private static void interactiveShell() {
        System.out.print("Type ?list command-name to get more info");
        InteractiveShell interactiveShell = new InteractiveShell();
        interactiveShell.loop();
    }

    private static void cli(String[] args) {
        System.out.println("Use -h command for further help");
        CLI cli = new CLI(args);
        cli.parse();
        execution(cli.loadSettings());
    }

    // Syntax Visitor execution
    private static boolean syntaxAnalysis(ExecutionSettings executionSettings) {
        logger.log(Level.INFO,"Syntax visitor started");
        syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.visit(modelTree);
        System.out.println(syntaxVisitor.getParseResult());
        modelName = syntaxVisitor.getModelName();
        executionSettings.setModelName(modelName);
        logger.log(Level.INFO,"Syntax visitor completed");
        return syntaxVisitor.getAcceptableModel();
    }

    private static void setupLogger() {
        String filename = "";
        try {
            logger.setUseParentHandlers(false);
            // This block configure the logger with handler and formatter
            // I create a local folder
            File newDirectory = new File(modelName);
            if(!newDirectory.exists())
                new File(modelName).mkdirs();
            fh = new FileHandler(modelName + "/" + modelName +".log");
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

    private static void graphPrinting() {
            logger.log(Level.INFO, "Launching graphviz printing...");
            System.out.println("***************");
            System.out.println("MODEL ANALYSIS");
            System.out.println("***************");
            System.out.println("Printing model and reactions");
            graphsCollection.printModel();
    }

}
