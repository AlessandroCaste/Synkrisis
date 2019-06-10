package core;

import antlr.bigraph.BigraphLexer;
import antlr.bigraph.BigraphParser;
import core.graphBuilding.GraphBuildingVisitor;
import core.graphBuilding.GraphsCollection;
import core.graphBuilding.VertexTransitionGraph;
import core.syntaxAnalysis.ErrorListener;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.io.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
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
            if(setupSynk(inputFile)) {
                acceptableModel = syntaxAnalysis(inputFile.getName());   // String is required to check model name against filename
                if (acceptableModel) {
                    graphvizModel();
                    executeBigmc();
                    processTransition();
                } else
                System.out.println("Error in syntax analysis: processing can't go any further");
            }
            else {
                System.out.println("Model printing and transition generation can't proceed");
            }
        }

    }

    // File setup for analysis
    @SuppressWarnings("Duplicates")
    private static boolean setupSynk(File inputFile) {
        boolean successfullSetup = false;
        try {
            logger.log(Level.INFO, "Parsing tree creation started");
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new BigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            BigraphParser parser = new BigraphParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ErrorListener.INSTANCE);
            modelTree = parser.bigraph();
            successfullSetup = true;
        } catch(FileNotFoundException e) {
            // TODO : Execution halts goes on and throws unexpected errors
            System.out.println("[FATAL ERROR] File \"" + inputFile.getPath() + "\" not found");
            logger.log(Level.SEVERE,"Couldn't find the requested file " + inputFile.getAbsolutePath() + "\nStack trace:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("[FATAL ERROR] Can't create the Lexer to start analysis" + inputFile.getName());
            logger.log(Level.SEVERE, "Can't create CharStreams from the InputStream of " + inputFile.getName() + "\nStack trace:" + e.getMessage());
        } catch (ParseCancellationException | NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Parsing can't proceed");
            logger.log(Level.SEVERE,e.getMessage());
        }
        logger.log(Level.INFO,"Parsing tree creation concluded");
        return successfullSetup;
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

    // Sending input to bigmc
    private static void executeBigmc() {

        try{
            logger.log(Level.INFO, "Executing bigmc commands");
            String workingDirectory = System.getProperty("user.dir");
            // TODO What about implementing bigmc inside .jar?
            ProcessBuilder pb = new ProcessBuilder("lib/bigmc", "-m 100", "-s", "-r 2", filename);
            pb.directory(new File(workingDirectory));
            pb.redirectErrorStream(true);
           // pb.redirectOutput(new File(modelName+"/"+modelName+".transition"));

            // I redirect bigmc output to a temp file in order to scan it
            pb.redirectOutput(new File(modelName+"/"+modelName+".temp"));
            Process p = pb.start();
            logger.log(Level.INFO, "Waiting for bigmc process..");
            p.waitFor();
            } catch (IOException | InterruptedException e) {
            System.out.println("Can't work with bigmc: is it correctly installed?");
            logger.log(Level.SEVERE,"Can't interface with bigmc input stream, probably has to do with the terminal commands not being properly recognized");
        }

        Scanner sc = null;
        try {
            sc = new Scanner(new File(modelName+"/"+modelName+".temp"));
            boolean endFlag = false;

            int transition_systems = 0;
            BufferedWriter transition = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".transition(" + transition_systems +")",true));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.equals("Transition system:")) {
                    endFlag = true;
                    line = sc.nextLine();
                    if(transition_systems > 0) {
                        transition = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".transition(" + transition_systems + ")",true));
                    }
                    transition.write(line + "\n");
                }
                else if(line.equals("End of the transition system")) {
                    endFlag = false;
                    transition.close();
                    transition_systems++;
                } else if(line.matches("bigmc::report] ")) {

                }else if(!endFlag)
                    System.out.println(line);
                else if(endFlag)
                    transition.write(line +"\n");
            }
            sc.close();
            transition.close();
            File lastTransition = new File(modelName + "/" + modelName + ".transition(" + (transition_systems - 1) + ")");
            lastTransition.renameTo(new File(modelName + "/" + modelName + ".transition"));

            // In case I generated multiple transition systems I move them to a separate folder
            if(transition_systems > 0)
                for(int counter = 0; counter < transition_systems - 1; counter++)
                    FileUtils.moveFile(new File(modelName + "/" + modelName + ".transition(" + counter + ")"),
                                       new File(modelName + "/" + "intermediate transitions/" + modelName + ".transition(" + counter + ")"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) { // Per il writer
            e.printStackTrace();
        }


    }

    // Translating the transition graph to a jgrapht graph
    private static void processTransition() {
        try {
            DirectedMultigraph<VertexTransitionGraph, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);

            // The following methods specify how to create vertices, edges, and update them during parsing
            // Dot translation uses a special kind of vertex that stores an ID, a label and a list of properties
            VertexProvider<VertexTransitionGraph> vertexProvider = (s, map) -> {
                // Extracting attributes from dot representation
                Attribute label = map.get("label");
                Attribute properties = map.get("properties");
                String labelString;
                ArrayList<String> propertiesList;
                if(label != null)
                    labelString = label.toString();
                else
                    labelString = "";
                if(properties != null)
                    propertiesList = new ArrayList<>(Arrays.asList(properties.toString().split("\\s*,\\s*")));
                else
                    propertiesList = null;
                return new VertexTransitionGraph(s,labelString,propertiesList);
            };
            EdgeProvider<VertexTransitionGraph, DefaultEdge> edgeProvider = (v1, v2, s2, map) -> new DefaultEdge();
            ComponentUpdater<VertexTransitionGraph> vertexUpdater = (vertex, map) -> {
                Attribute label = map.get("label");
                Attribute properties = map.get("properties");
                ArrayList<String> propertiesList;
                if(label != null)
                    vertex.setLabel(label.toString());
                if(properties != null) {
                    propertiesList = new ArrayList<>(Arrays.asList(properties.toString().split("\\s*,\\s*")));
                    vertex.setProperties(propertiesList);
                }
            };

            DOTImporter<VertexTransitionGraph, DefaultEdge> importer = new DOTImporter<>(vertexProvider, edgeProvider, vertexUpdater);
            FileReader transitionFile = new FileReader(modelName+"/"+modelName+".transition");
            importer.importGraph(graph, transitionFile);
            logger.log(Level.INFO,".dot transition file correctly translated to jgraph model");

        } catch (FileNotFoundException fe) {
            System.out.println("[FATAL ERROR] Transition system hasn't been successfully created; problems with the model checker?");
            logger.log(Level.SEVERE, "Missing transition file; something went wrong when reading the output of the model checker (bigmc?) and printing it to file\nStack trace: " + fe.getMessage());
        } catch (ImportException ie) {
            System.out.println("[FATAL ERROR] Error while importing dot transition file: check for syntax problems");
            logger.log(Level.SEVERE, "JgraphT parsing of dot file failed; this may have to do with vertex/edge providers, but it probably boils down to wrong dot specifications\n Stack trace: " + ie.getMessage());
        }
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
