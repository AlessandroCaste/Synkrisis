import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

public class Main {

    private static BigraphBaseVisitor visitor;

    public static void main(String[] args) {
        if (args.length > 0) {
            String filename = args[0];
            System.out.println(System.getProperty("user.dir"));
            try {

                File inputFile = new File(filename);
                InputStream inputStream = new FileInputStream(inputFile);
                Lexer lexer = new BigraphLexer(CharStreams.fromStream(inputStream));
                TokenStream tokenStream = new CommonTokenStream(lexer);
                BigraphParser parser = new BigraphParser(tokenStream);
                ParseTree tree = parser.bigraph();
                visitor = new BigraphBaseVisitor();
                visitor.visit(tree);

                if (!visitor.checkModelName(inputFile.getName()))
                    System.out.println("[ERROR] File name and model names do not match");
                System.out.println(visitor.checkUnusedVariables());
                System.out.println(visitor.getParseResult());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}