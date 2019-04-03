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
                System.out.println(visitor.checkUnusedVariables());
                System.out.println(visitor.getParseResult());

            } catch (IOException e) {
                //e.printStackTrace();
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