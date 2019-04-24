import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.*;

import static guru.nidi.graphviz.model.Factory.*;

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
                BigraphBaseVisitor2 graphvizVisitor = new BigraphBaseVisitor2();
                graphvizVisitor.visit(tree);
                graphvizVisitor.createGraph();

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