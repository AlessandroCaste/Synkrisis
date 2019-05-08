package core;

import antlr.BigraphLexer;
import antlr.BigraphParser;
import core.graphBuilding.GraphBuildingVisitor;
import core.graphBuilding.GraphsCollection;
import core.syntaxAnalysis.ErrorListener;
import core.syntaxAnalysis.SyntaxVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {


    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        if (args.length > 0) {
            String filename = args[0];
            try {
                File inputFile = new File(filename);
                InputStream inputStream = new FileInputStream(inputFile);
                Lexer lexer = new BigraphLexer(CharStreams.fromStream(inputStream));
                TokenStream tokenStream = new CommonTokenStream(lexer);
                BigraphParser parser = new BigraphParser(tokenStream);
                parser.removeErrorListeners();
                parser.addErrorListener(ErrorListener.INSTANCE);
                ParseTree tree = parser.bigraph();
                SyntaxVisitor visitor = new SyntaxVisitor();
                visitor.visit(tree);

                if (!visitor.checkModelName(inputFile.getName()))
                    System.out.println("[ERROR] File name and model names do not match");
                System.out.println(visitor.getParseResult());

                // Graph Building
                if(visitor.getAcceptableModel()) {
                    GraphBuildingVisitor graphvizVisitor = new GraphBuildingVisitor();
                    graphvizVisitor.visit(tree);
                    graphvizVisitor.storeModelName();
                    GraphsCollection.getInstance().printModel();
                } else {
                    System.out.println("Can't produce graphic models");
                }

            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("File " + filename +" not found");
            } catch (ParseCancellationException | NumberFormatException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Parsing can't proceed");
            }
        } else {
            System.out.println("Missing input!");
        }
    }


}