package core;

import core.graphBuilding.GraphBuildingVisitor;
import core.graphBuilding.GraphsCollection;
import core.setup.Bigmc;
import core.setup.ProcessTransition;
import core.setup.SetupSynk;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/* This is the application flow:
1) Read arguments from CLI
2) Syntax Analysis
3) Model and reaction printing
4) Translation from transition graph into Jgraph
5) Translation from transition graph into PRISM model
 */

public class Main {

    private static String filename;
    private static String modelName;
    private static ParseTree modelTree;
    private static boolean acceptableModel;

    private static Logger logger = Logger.getLogger("Report");

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {

        // I read input args from CLI and store them into an ExecutionSettings Object
        CLI analysis = new CLI(args);
        analysis.parse();
        ExecutionSettings loadedSettings = analysis.loadSettings();

        filename = loadedSettings.getFileName();
        File inputFile = new File(filename);
        SetupSynk initialization = new SetupSynk(inputFile); // Initializing lexer, tokens etc
        if(initialization.isSuccessful()) {
            modelTree = initialization.getModelTree();
            acceptableModel = syntaxAnalysis(inputFile.getName());   // String is required to check model name against filename
            if (acceptableModel) {
                if(loadedSettings.isPrintEnabled())
                    graphvizModel();
                new Bigmc(loadedSettings,modelName); // Running bigmc and parsing the results
                if (loadedSettings.isExportingEnabled()) {
                    new ProcessTransition(modelName); // Translating the transition graph to a jgrapht graph
                    if(loadedSettings.getOutputModelChecker().equals("PRISM"))
                        System.out.println("ciao");
                        //new PrismExporter(modelName);
                }
            } else
            System.out.println("Error in syntax analysis: processing can't go any further");
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

}
