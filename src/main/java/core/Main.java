package core;

import core.graphModels.GraphBuildingVisitor;
import core.graphModels.TransitionDotImporter;
import core.graphModels.storing.GraphsCollection;
import core.setup.Bigmc;
import core.setup.SetupSynk;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
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

    private static String filePath;
    private static String modelName;
    private static ParseTree modelTree;
    private static boolean acceptableModel;
    private static ExecutionSettings loadedSettings;
    private static SyntaxVisitor syntaxVisitor;
    private static GraphsCollection  graphsCollection = GraphsCollection.getInstance();

    private static Logger logger = Logger.getLogger("Report");

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {

        System.out.println("****************************\nSynkrisis Toolchain (2019)\n****************************");
        File inputFile;

        // Interactive Shell
        if(args.length == 1) {
            System.out.print("Type ?list command-name to get more info");
            InteractiveShell interactiveShell = new InteractiveShell();
            interactiveShell.setExecutionSettings(loadedSettings);
            interactiveShell.loop();
        }
        // Passing args from command line
        else {
            System.out.println("Use -h command for further help");
            CLI cli = new CLI(args);
            cli.parse();
            loadedSettings = cli.loadSettings();
        }
        filePath = loadedSettings.getFilePath();
        inputFile = new File(filePath);

        SetupSynk initialization = new SetupSynk(inputFile); // Initializing lexer, tokens etc
        if(initialization.isSuccessful()) {
            modelTree = initialization.getModelTree();
            acceptableModel = syntaxAnalysis();   // String is required to check model name against filePath
            if (acceptableModel) {
                // I translate the graph into a JgraphT model: this way I may use different kinds of model checkers
                logger.log(Level.INFO,"Jgraph translation from parsetree started");
                GraphBuildingVisitor modelBuilder = new GraphBuildingVisitor();
                modelBuilder.visit(modelTree);
                String propertiesString = modelBuilder.getPropertiesString();
                logger.log(Level.INFO, "Jgraph translation from parsetree completed.");

                // Graph printing
                if(loadedSettings.isPrintModelEnabled()) {
                    logger.log(Level.INFO,"Launching graphviz printing...");
                    System.out.println("***************");
                    System.out.println("MODEL ANALYSIS");
                    System.out.println("***************");
                    System.out.println("Printing model and reactions");
                    graphsCollection.printModel();
                }

                // TODO Add here other-than-bigmc support
                // If the model can't be submitted as-it-is then we must strip it of elements non compatible with bigmc
                if(!loadedSettings.isBigmcReady()) {
                    bigmcTranslator(modelName);
                    //TODO hashed?
                    loadedSettings.setBigmcFile(modelName + "/" + "temp_transl_bigmc.bigraph");
                }
                // Running bigmc and parsing the results
                new Bigmc(loadedSettings,modelName);
                System.out.println("Generating the transition graph");
                if(loadedSettings.isProcessTransitionOnly())
                    new TransitionDotImporter(modelName); // Translating the transition graph to a jgrapht graph
                else
                    new TransitionDotImporter(loadedSettings.getTransitionFile());
                if (loadedSettings.isPrintTransitionEnabled()) {
                    System.out.println("Printing the transition graph");
                    graphsCollection.printTransition();
                }
                if(loadedSettings.isPrismExportingEnabled() || loadedSettings.isSpotExportingEnabled()) {
                    System.out.println("****************");
                    System.out.println("MODEL EXPORTING");
                    System.out.println("****************");
                    // Model exporting
                    if (loadedSettings.isSpotExportingEnabled() && modelBuilder.isSpotReady())
                        graphsCollection.exportToSpot(modelBuilder.getAcceptanceInfo());
                    if (loadedSettings.isPrismExportingEnabled())
                        if(propertiesString!=null)
                            graphsCollection.exportToPrism(propertiesString);
                        else
                            graphsCollection.exportToPrism("");


                    else if (loadedSettings.isSpotExportingEnabled() && !modelBuilder.isSpotReady())
                        System.out.println("Can't proceed with SPOT exporting: duplicate acceptance states!\n" + modelBuilder.getDuplicateSpotStates());

                }
            } else
                System.out.println("Error in syntax analysis: processing can't go any further");
        }
    }


    // Syntax Visitor execution
    private static boolean syntaxAnalysis() {
        logger.log(Level.INFO,"Syntax visitor started");
        String filename = FilenameUtils.getName(loadedSettings.getFilePath());
        syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.visit(modelTree);
        modelName = syntaxVisitor.getModelName();
        if (!filename.equals(modelName + ".bigraph")) {
            System.out.println("[FATAL ERROR] File name and model names do not match : " + filename + " vs " + modelName + ".bigraph");
            logger.log(Level.SEVERE,"Execution suspended since model name and file name do not match: " + filename + " vs " + modelName +".bigraph.\nCan't run visitor until it's fixed");
            return false;
        }
        // .prop file is extracted and print here
        // TODO add options in CLI
        // if(loadedSettings.isExportingEnabled())
        if(syntaxVisitor.isBigmcReady())
            loadedSettings.setBigmcReady();

        System.out.println(syntaxVisitor.getParseResult());
        logger.log(Level.INFO,"Syntax visitor completed");
        return syntaxVisitor.getAcceptableModel();
    }

    // Translation requires only weights to be removed: properties are automatically filtered by bigmc
    private static void bigmcTranslator(String modelName){
        System.out.println("TRANSITION GRAPH GENERATION");
        System.out.println("Model translation underway");
        logger.log(Level.INFO,"Starting translation of bigraph into bigmc-readable file");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(loadedSettings.getFilePath()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(modelName + "/" + "temp_transl_bigmc.bigraph")));
            String line;

            while ((line = reader.readLine()) != null) {
                if(line.contains("rule")) {
                    line = line.replaceAll("->\\s*\\(0.\\d+\\)","->"); }
                writer.write(line + "\n");
            }
            reader.close();
            writer.close();
         } catch (Exception e) {
            System.out.println("Problems during bigmc translation!");
            logger.log(Level.SEVERE,"Can't translate input to bigmc-readable file.\nStack: " + e.getMessage());
        }
        System.out.println("Translation complete");
    }

}
