package core.setup;

import core.clishell.CLI;
import core.clishell.ExecutionSettings;
import core.clishell.InteractiveShell;
import core.graphs.GraphBuildingVisitor;
import core.graphs.TransitionDotImporter;
import core.graphs.storing.GraphsCollection;
import core.modelcheckers.Bigmc;
import core.modelcheckers.ModelChecker;
import core.syntax.SyntaxVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static Logger logger = Logger.getLogger("Report");

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
        Setup setup = new Setup(inputFile);
        setup.setupLogger();
        setup.setupParser(); // Initializing lexer, tokens etc
        if (setup.isSuccessful()) {
            modelTree = setup.getModelTree();
            boolean acceptableModel = syntaxAnalysis(executionSettings);
            if (acceptableModel) {
                // I translate the graph into a JgraphT model
                logger.log(Level.INFO, "Jgraph translation from parsetree started");
                GraphBuildingVisitor modelBuilder = new GraphBuildingVisitor();
                modelBuilder.visit(modelTree);
                System.out.println("[ANALYSIS COMPLETE] Specification has been analyzed");
                logger.log(Level.INFO, "Jgraph translation from parsetree completed.");

                // Looking for/creating new folder
                File newDirectory = new File(modelName);
                if(!newDirectory.exists())
                    new File(modelName).mkdirs();

                if(executionSettings.isPrintModelEnabled())
                    graphPrinting();

                // If the model can't be submitted as-it-is then we must strip it of elements non compatible with bigmc
                ModelChecker modelChecker = new Bigmc(executionSettings, modelTree);
                if (modelChecker.needsTranslation())
                    modelChecker.translate();
                modelChecker.execute();

                // Running the model checker must give us back a transition graph, so that we parse results
                boolean dotImportingSuccessful = false;
                //boolean dotExportingSuccessful = false;
                if( modelChecker.isSuccessful()) {
                    System.out.println("[GENERATION : COMPLETE] Transition graph has been generated by the model checker");
                    dotImportingSuccessful = dotImporting(executionSettings);
                } else
                    System.out.println("[GENERATION : ERROR] Couldn't find bigmc in this working machine");
                if(dotImportingSuccessful) {
                    System.out.println("Transition file correctly imported");
                    dotExporting(executionSettings,modelBuilder);
                }
                else
                    System.out.println("[FATAL ERROR] Can't import the transition file correctly");
            } else
                System.out.println("Error in syntax analysis: processing can't go any further");
        }
        setup.closeLogger();
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
        if(!cli.mustLeave())
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


    private static void graphPrinting() {
            logger.log(Level.INFO, "Launching graphviz printing...");
            System.out.println("***************");
            System.out.println("MODEL ANALYSIS");
            System.out.println("***************");
            System.out.println("Printing model and reactions");
            graphsCollection.printModel();
    }

    private static boolean dotImporting(ExecutionSettings executionSettings) {
        TransitionDotImporter dotImporter;
        if (executionSettings.isProcessTransitionOnly())
            dotImporter = new TransitionDotImporter(modelName, true);
        else
            dotImporter = new TransitionDotImporter(modelName, false);
        dotImporter.processTransition();

        if (executionSettings.isPrintTransitionEnabled()) {
            System.out.println("Printing the transition graph");
            graphsCollection.printTransition();
        }
        return dotImporter.isSuccessful();
    }

    //TODO add boolean control
    private static void dotExporting(ExecutionSettings executionSettings,GraphBuildingVisitor modelBuilder) {
        String propertiesString = modelBuilder.getPropertiesString();
        executionSettings.setSpotExportingReady(modelBuilder.isSpotReady());
        if (executionSettings.isExportingEnabled()) {
            System.out.println("\nMODEL EXPORTING");
            System.out.println("****************");
            // Model exporting
            if (executionSettings.isSpotExportingEnabled())
                graphsCollection.exportToSpot(modelBuilder.getAcceptanceInfo());
            if (executionSettings.isPrismExportingEnabled())
                if (propertiesString != null)
                    graphsCollection.exportToPrism(propertiesString);
                else
                    graphsCollection.exportToPrism("");
        }
    }

}
