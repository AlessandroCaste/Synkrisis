package core;

import antlr.bigraph.BigraphLexer;
import antlr.bigraph.BigraphParser;
import core.graphBuilding.GraphBuildingVisitor;
import core.graphBuilding.GraphsCollection;
import core.graphBuilding.VertexTransitionGraph;
import core.syntaxAnalysis.ErrorListener;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.io.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                }
            }
            System.out.println("Model printing and transition generation can't proceed");
        }

    }

    // File setup for analysis
    @SuppressWarnings("Duplicates")
    private static boolean setupSynk(File inputFile) {
        boolean successfullSetup = false;
        try {
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
            System.out.println("[ERROR] File \"" + inputFile.getPath() + "\" not found");
            logger.log(Level.SEVERE,"Couldn't find the requested file " + inputFile.getAbsolutePath() );

        } catch (IOException e) {
            // TODO : LexerStream errors are not managed
            System.out.println("Error");
        }
        return successfullSetup;
    }

    // Syntax Visitor execution
    private static boolean syntaxAnalysis(String inputFileName) {
        SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.visit(modelTree);
        modelName = syntaxVisitor.getModelName();
        if (!inputFileName.equals(modelName+".bigraph")) {
            System.out.println("[ERROR] File name and model names do not match");
            return false;
        }
        System.out.println(syntaxVisitor.getParseResult());
        return syntaxVisitor.getAcceptableModel();
        // TODO : Throws errors because of uncompleted exception management
    }

    /*  } catch (ParseCancellationException | NumberFormatException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Parsing can't proceed"); */

    // Outputting pictures!
    private static void graphvizModel() {
        GraphBuildingVisitor graphvizVisitor = new GraphBuildingVisitor();
        graphvizVisitor.visit(modelTree);
        GraphsCollection.getInstance().printModel();
    }

    // Sending input to bigmc
    private static void executeBigmc() {
        try{
            String workingDirectory = System.getProperty("user.dir");
            // TODO What about implementing bigmc inside .jar?
            ProcessBuilder pb = new ProcessBuilder("lib/bigmc", "-s", filename);
            pb.directory(new File(workingDirectory));
            pb.redirectErrorStream(true);
            pb.redirectOutput(new File(modelName+"/"+modelName+".transition"));
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE,"Problems interfacing with bigmc input stream");
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
            // Testing reactions
            // for(DefaultEdge de : graph.edgeSet())
            // System.out.println(graph.getEdgeSource(de).getVertexID() + " -> " + graph.getEdgeTarget(de).getVertexID());
            // TODO
            // Specify a more complex behavior for exception management
        } catch (FileNotFoundException fe) {
            System.err.println(fe.getMessage());
        } catch (ImportException ie) {
            System.err.println(ie.getMessage());
        }
    }

    private static void setupLogger(String filename) {
        try {
            // This block configure the logger with handler and formatter
            logger.setUseParentHandlers(false);
            fh = new FileHandler(FilenameUtils.removeExtension(filename) + ".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // the following statement is used to log any messages
            logger.info("Log started");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

}
