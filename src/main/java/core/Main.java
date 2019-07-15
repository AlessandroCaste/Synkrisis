package core;

import core.graphBuilding.GraphBuildingVisitor;
import core.graphBuilding.GraphsCollection;
import core.setup.Bigmc;
import core.setup.ProcessTransition;
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

    private static Logger logger = Logger.getLogger("Report");

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {

        System.out.println("****************************\nSynkrisis Toolchain (2019)\n****************************");
        System.out.println("Input your model with the --load command.\nUse -h for further help");
        boolean modelLoaded = false;

        if(args.length != 0) {
            // Passing args to the program via command line
            CLI cli = new CLI(args);
            cli.parse();
            loadedSettings = cli.loadSettings();
        } else {
            // Interactive shell
            while (!modelLoaded) {
                System.out.print("> ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] inputString = null;
                try {
                    inputString = reader.readLine().split(" ");
                } catch (IOException e) {
                    System.out.println("Problems reading user input from System.in!");
                }
                CLI cli = new CLI(inputString);
                cli.parse();
                if (cli.isModelLoaded()) {
                    loadedSettings = cli.loadSettings();
                    modelLoaded = true;
                } else
                    System.out.println("No model was loaded!");
            }
        }

        filePath = loadedSettings.getFilePath();
        File inputFile = new File(filePath);
        SetupSynk initialization = new SetupSynk(inputFile); // Initializing lexer, tokens etc
        if(initialization.isSuccessful()) {
            modelTree = initialization.getModelTree();
            acceptableModel = syntaxAnalysis();   // String is required to check model name against filePath
            if (acceptableModel) {
                // I translate the graph into a JgraphT model: this way I may use different kinds of model checkers
                logger.log(Level.INFO,"Jgraph translation from parsetree started");
                GraphBuildingVisitor modelBuilder = new GraphBuildingVisitor();
                modelBuilder.visit(modelTree);
                logger.log(Level.INFO, "Jgraph translation from parsetree completed.");

                logger.log(Level.INFO, "Printing .prop file");
                //TODO if exporting is enabled
                modelBuilder.printProperties();
                logger.log(Level.INFO, ".prop file print");

                // Graph printing
                if(loadedSettings.isPrintEnabled()) {
                    logger.log(Level.INFO,"Launching graphviz printing...");
                    System.out.println("PRINTING MODEL AND REACTIONS");
                    GraphsCollection.getInstance().printModel();
                    System.out.println("****************************");
                }

                // TODO Add here other-than-bigmc support
                // If the model can't be submitted as-it-is then we must strip it of elements non compatible with bigmc
                if(!loadedSettings.isBigmcReady()) {
                    bigmcTranslator(modelName);
                    loadedSettings.setBigmcFile(modelName + "/" + "temp_transl_bigmc.bigraph");
                }
                // Running bigmc and parsing the results
                new Bigmc(loadedSettings,modelName);

                System.out.println("****************************");
                System.out.println("MODEL EXPORTING AND ANALYSIS");
                System.out.println("****************************");
                // Model exporting
               // if (loadedSettings.isExportingEnabled()) {
                    new ProcessTransition(modelName); // Translating the transition graph to a jgrapht graph
                    if(loadedSettings.isPrintTransitionEnabled())
                        GraphsCollection.getInstance().printTransition();
//                    if(loadedSettings.getOutputModelChecker().equals("PRISM"))
//                       System.out.println("ciao");
                        //new PrismExporter(modelName);
               // }
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
