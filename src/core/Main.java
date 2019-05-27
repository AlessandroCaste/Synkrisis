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
import org.apache.commons.io.FilenameUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class Main {

    private static String filename;
    private static String modelName;
    private static ParseTree modelTree;
    private static boolean acceptableModel;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        if (args.length > 0) {
            filename = args[0];
            File inputFile = new File(filename);
            setupSynk(inputFile);
            acceptableModel = syntaxAnalysis(FilenameUtils.removeExtension(inputFile.getName()));   // String is required to check model name against filename
            if (acceptableModel)
                graphvizModel();
            else
                System.out.println("Can't produce graphic model with wrong specifications!");
            executeBigmc();
            processTransition();
        }

    }

    // Setupping the file for analysis
    @SuppressWarnings("Duplicates")
    private static void setupSynk(File inputFile) {
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new BigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            BigraphParser parser = new BigraphParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ErrorListener.INSTANCE);
            modelTree = parser.bigraph();

        } catch(FileNotFoundException e) {
            // TODO : Execution halts goes on and throws unexpected errors
            logger.error("There's no file like that");
        } catch (IOException e) {
            // TODO : LexerStream errors are not managed
            System.out.println("Error");
        }
    }

    // Syntax Visitor execution
    private static boolean syntaxAnalysis(String inputFileName) {
        SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.visit(modelTree);
        modelName = syntaxVisitor.getModelName();
        if (!inputFileName.equals(modelName)) {
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
            ProcessBuilder pb = new ProcessBuilder("bigmc", "-s", filename);
            pb.directory(new File(workingDirectory));
            pb.redirectErrorStream(true);
            pb.redirectOutput(new File(modelName+"/"+modelName+".transition"));
            Process p = pb.start();
            p.waitFor();

        } catch (IOException | InterruptedException e) {
            logger.error("Problems interfacing with bigmc input stream");
        }
    }

    private static void processTransition() {
        try {
            // Dot translation uses a special kind of vertex that stores both a label and a list of properties
            DirectedMultigraph<VertexTransitionGraph, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);
            VertexProvider<VertexTransitionGraph> vertexProvider = new VertexProvider<VertexTransitionGraph>() {
                @Override
                public VertexTransitionGraph buildVertex(String s, Map<String, Attribute> map) {
                    // Extracting attributes from dot representation
                    Attribute label = map.get("label");
                    Attribute properties = map.get("properties");
                    // Creating strings and arraylist for Vertex representation
                    String labelString;
                    String propertiesString;
                    ArrayList<String> propertiesList;
                    if(label != null)
                        labelString = label.toString();
                    else
                        labelString = "";
                    if(properties != null) {
                        propertiesString = properties.toString();
                        propertiesList = new ArrayList<>(Arrays.asList(propertiesString.split("\\s*,\\s*")));
                    }
                    else
                        propertiesList = null;
                    return new VertexTransitionGraph(s,labelString,propertiesList);
                }
            };
            EdgeProvider<VertexTransitionGraph, DefaultEdge> edgeProvider = new EdgeProvider<VertexTransitionGraph, DefaultEdge>() {
                @Override
                public DefaultEdge buildEdge(VertexTransitionGraph v1, VertexTransitionGraph v2, String s2, Map<String, Attribute> map) {
                    return new DefaultEdge();
                }
            };
            ComponentUpdater<VertexTransitionGraph> vertexUpdater = new ComponentUpdater<VertexTransitionGraph>() {
                @Override
                public void update(VertexTransitionGraph vertexTransitionGraph, Map<String, Attribute> map) {
                    Attribute label = map.get("label");
                    Attribute properties = map.get("properties");
                    ArrayList<String> propertiesList;
                    if(label != null)
                        vertexTransitionGraph.setLabel(label.toString());
                    if(properties != null) {
                        propertiesList = new ArrayList<>(Arrays.asList(properties.toString().split("\\s*,\\s*")));
                        vertexTransitionGraph.setProperties(propertiesList);
                    }
                }
            };
            DOTImporter<VertexTransitionGraph, DefaultEdge> importer = new DOTImporter<VertexTransitionGraph,DefaultEdge>(vertexProvider, edgeProvider,vertexUpdater);
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

}

