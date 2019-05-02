package core;

import antlr.BigraphLexer;
import antlr.BigraphParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.*;

public class Main {


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
                BigraphBaseVisitor visitor = new BigraphBaseVisitor();
                visitor.visit(tree);

                if (!visitor.checkModelName(inputFile.getName()))
                    System.out.println("[ERROR] File name and model names do not match");
                String variableUsage = visitor.checkUnusedVariables();
                if(!variableUsage.isEmpty())
                    System.out.println(visitor.checkUnusedVariables());
                System.out.println(visitor.getParseResult());

                // Graph Building
                GraphBuildingVisitor graphvizVisitor = new GraphBuildingVisitor();
                graphvizVisitor.storeFileName(visitor.getModelName());
                graphvizVisitor.visit(tree);

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