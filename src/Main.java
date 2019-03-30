import jdk.internal.util.xml.impl.Input;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {

        System.out.println("miao");

        InputStream inputStream = Main.class.getResourceAsStream("/example.txt");
        try {
            Lexer lexer = new BigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            BigraphParser parser = new BigraphParser(tokenStream);
            ParseTree tree = parser.bigraph();
            BigraphVisitor visitor = new BigraphBaseVisitor();
            visitor.visit(tree);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}