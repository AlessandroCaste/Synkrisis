package core.setup;

import core.clishell.CLI;
import core.clishell.ExecutionSettings;
import core.clishell.InteractiveShell;
import core.exporting.Exporter;
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
    private static Exporter exporter = Exporter.getInstance();

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
        // SETUP
        File inputFile = new File(executionSettings.getFilePath());
        Setup setup = new Setup(inputFile);
        setup.setupLogger();
        setup.setupParser(); // Initializing lexer, tokens etc
        exporter.initialize(executionSettings.checkersList(),executionSettings.isProcessTransitionOnly());
        if (setup.isSuccessful()) {
            // .bigraph Model Validation
            boolean acceptableModel = syntaxAnalysis(executionSettings);
            // Model Abstraction
            if (acceptableModel) {
                GraphBuildingVisitor modelBuilder = setup.setupGraphBuilder(modelName);
                if(executionSettings.isPrintModelEnabled())
                    graphPrinting();
                //Transition Graph generation
                ModelChecker modelChecker = new Bigmc(executionSettings, modelTree);
                if (modelChecker.needsTranslation())
                    modelChecker.translate();
                modelChecker.execute();
                // Running the model checker must give us back a transition graph, so that we parse results
                boolean dotImportingSuccessful = false;
                if(modelChecker.isSuccessful()) {
                    System.out.println("[GENERATION : COMPLETE] Transition graph has been generated by the model checker");
                    dotImportingSuccessful = dotImporting(executionSettings);
                }
                else
                    System.out.println("[GENERATION : ERROR] Couldn't find bigmc in this working machine");
                exporting(dotImportingSuccessful,executionSettings,modelBuilder);

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
        if(cli.transitionOnly()) {
            boolean dotImportingResult = dotImporting(cli.loadSettings());
            exportingNoAnalysis(dotImportingResult, cli.loadSettings());
        }
        else
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

    // Transition-only case execution
    public static void transitionOnlyExecution(ExecutionSettings executionSettings){
        if(executionSettings.isProcessTransitionOnly()) {
            boolean dotImportingResult = dotImporting(executionSettings);
            exportingNoAnalysis(dotImportingResult, executionSettings);
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

    private static boolean dotImporting(ExecutionSettings executionSettings) {
        if(executionSettings.isProcessTransitionOnly()) {
            File inputFile = new File(executionSettings.getFilePath());
            Setup setup = new Setup(inputFile);
            setup.setupLogger();
        }
        System.out.println("\nTRANSITION GRAPH IMPORTING\n**************************");
        System.out.println("Transition graph importing started");
        TransitionDotImporter dotImporter;
        dotImporter = new TransitionDotImporter(executionSettings.getFilePath(), executionSettings.isProcessTransitionOnly());
        dotImporter.processTransition();
        if (executionSettings.isPrintTransitionEnabled()) {
            System.out.println("Printing the transition graph");
            graphsCollection.printTransition();
        }
        if(dotImporter.isSuccessful())
            System.out.println("Transition file correctly imported");
        else
            System.out.println("[FATAL ERROR] Can't import the transition file correctly");
        return dotImporter.isSuccessful();
    }

    private static void exporting(boolean dotImportingSuccessful,ExecutionSettings executionSettings,GraphBuildingVisitor modelBuilder) {
        if (dotImportingSuccessful && executionSettings.isExportingEnabled() && !exporter.isEmpty()) {
            System.out.println("\nMODEL EXPORTING\n***************");
            exporter.setTransitionGraph(graphsCollection.getTransitionGraph());
            if (modelBuilder.isSpotReady())
                exporter.addSpotInfo(modelBuilder.getSpotInfo());
            exporter.execute();
        } else if(!executionSettings.isExportingEnabled())
            System.out.println("No exporting format has been specified");
        else
            System.out.println("[EXPORTING FAILURE] Impossible to export to external formats");
    }

    private static void exportingNoAnalysis(boolean dotImportingSuccessful,ExecutionSettings executionSettings) {
        if (dotImportingSuccessful && executionSettings.isExportingEnabled()) {
            System.out.println("\nMODEL EXPORTING\n***************");
            exporter.initialize(executionSettings.checkersList(),executionSettings.isProcessTransitionOnly());
            exporter.setTransitionGraph(graphsCollection.getTransitionGraph());
            exporter.execute();
        } else if(!executionSettings.isExportingEnabled())
            System.out.println("No exporting format has been specified");
        else
            System.out.println("[EXPORTING FAILURE] Impossible to export to external formats");
    }


}
