package core;

import antlr.bigraph.BigraphLexer;
import antlr.bigraph.BigraphParser;
import core.graphBuilding.GraphBuildingVisitor;
import core.graphBuilding.GraphsCollection;
import core.syntaxAnalysis.ErrorListener;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.Map;


public class Main {

    private static String filename;
    private static String modelName;
    private static ParseTree modelTree;
    private static boolean acceptableModel;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws ImportException {
        if (args.length > 0) {
            filename = args[0];
            setupSynk(filename);
            acceptableModel = syntaxAnalysis(FilenameUtils.removeExtension(filename));   // String is required to check model name against filename
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
    private static void setupSynk(String filename) {
        try {
            File inputFile = new File(filename);
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new BigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            BigraphParser parser = new BigraphParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ErrorListener.INSTANCE);
            modelTree = parser.bigraph();

        } catch(FileNotFoundException e) {
            // TODO : Execution halts goes on and throws unexpected errors
            System.out.println("error");
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
            if (!syntaxVisitor.checkModelName(inputFileName))
                System.out.println("[ERROR] File name and model names do not match");
            System.out.println(syntaxVisitor.getParseResult());
            return syntaxVisitor.getAcceptableModel();
            // TODO : Throws errors because of uncomplited exception management
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
        String workingDirectory = System.getProperty("user.dir");
        ProcessBuilder pb = new ProcessBuilder("lib/bigmc", "-G hospital", filename);
        // Fixare qua!!!
        pb.directory(new File(workingDirectory));
        pb.redirectErrorStream(true);

        try {
            Process p = pb.start();
            IOUtils.copy(p.getInputStream(), System.out);
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            logger.error("Problems interfacing with bigmc input stream");
        }
    }

    private static void processTransition() {
        try {
            DirectedMultigraph<String, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);
            VertexProvider<String> vertexProvider = new VertexProvider<String>() {
                @Override
                public String buildVertex(String s, Map<String, Attribute> map) {
                    return s;
                }
            };
            EdgeProvider<String, DefaultEdge> edgeEdgeProvider = new EdgeProvider<String, DefaultEdge>() {
                @Override
                public DefaultEdge buildEdge(String s, String v1, String s2, Map<String, Attribute> map) {
                    DefaultEdge de = new DefaultEdge();
                    graph.addEdge(s, v1);
                    return de;
                }
            };
            DOTImporter<String, DefaultEdge> importer = new DOTImporter<>(vertexProvider, edgeEdgeProvider);
            FileReader miao = new FileReader(modelName + ".dot");
            importer.importGraph(graph, miao);
            //TODO
            // Specify a more complex behavior for exception management
        } catch (FileNotFoundException fe) {
            System.err.println(fe.getMessage());
        } catch (ImportException ie) {
            System.err.println(ie.getMessage());
        }
    }

}

