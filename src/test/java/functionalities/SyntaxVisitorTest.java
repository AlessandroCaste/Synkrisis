package functionalities;

import antlr.bigraph.bigraphParser;
import antlr.g4models.bigraphLexer;
import core.syntax.ErrorListener;
import core.syntax.SyntaxVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This test checks black-box style that the execution of our models go as expected
class SyntaxVisitorTest {

    private SyntaxVisitor airport;
    private SyntaxVisitor example1;
    private SyntaxVisitor example2;
    private SyntaxVisitor example3;

    SyntaxVisitorTest() {

        String airportPath = "src/test/java/models/airport2.bigraph";
        String example1Path = "src/test/java/models/example1.bigraph";
        String example2Path = "src/test/java/models/example2.bigraph";
        String example3Path = "src/test/java/models/example3.bigraph";

        airport = createVisitor(airportPath);
        example1 = createVisitor(example1Path);
        example2 = createVisitor(example2Path);
        example3 = createVisitor(example3Path);
        checkAcceptance();
    }


    @SuppressWarnings("Duplicates")
    private SyntaxVisitor createVisitor(String path) {
        try {
            File inputFile = new File(path);
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new bigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            bigraphParser parser = new bigraphParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ErrorListener.INSTANCE);
            ParseTree tree = parser.bigraph();
            SyntaxVisitor visitor = new SyntaxVisitor();
            visitor.visit(tree);
            return visitor;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseCancellationException e) {
            // Example1 misses the model completely and throws a parsing error
            if(path.equals("src/test/java/models/example1.bigraph")) {
                assertEquals("[FATAL ERROR - Line 14:41] mismatched input '<EOF>' expecting {'rule', 'model'}",e.getMessage());
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    @Test
    void checkAcceptance() {
        // Airport model is a successful model
        String airportResult = airport.getParseResult();
        assertEquals("[RESULT : PASSED] Model is ready",airportResult);

        // Example2 shows different kind of errors instead
        String example2Result = example2.getParseResult();
        assertEquals("[ERROR - Line 7:5] Repeated control declaration\n" +
                    "[ERROR - Line 19:15] Control User has arity 1 not 2\n" +
                    "[ERROR - Line 19:45] Attempt to use an undeclared control: Random\n" +
                    "[ERROR - Line 21:18] Control Test has arity 0 not 1\n" +
                    "[WARNING] The following controls are declared and never used: Link \n" +
                    "[WARNING] The following names are declared and never used: Bob \n" +
                    "[RESULT : FAILED]",example2Result);

        // Example3 shows identifier repetition error
        String example3Result = example3.getParseResult();
        assertEquals("[ERROR - Line 6:5] Repeated control declaration\n" +
                    "[WARNING - Line 12:5] Names shouldn't share identifiers with controls!\n" +
                    "[WARNING - Line 13:5] Names shouldn't share identifiers with controls!\n" +
                    "[ERROR - Line 13:5] Repeated name declaration\n" +
                    "[ERROR - Line 16:32] Ports in reaction rules are either scoped variables or declared names\n" +
                    "[WARNING - Line 18:1] Reaction rules shouldn't be named after controls\n" +
                    "[WARNING - Line 18:1] Reaction rules shouldn't be named after an outer/inner name\n" +
                    "[ERROR - Line 18:18] Control Test shouldn't have arity 0!\n" +
                    "[ERROR - Line 18:42] Control Test shouldn't have arity 0!\n" +
                    "[ERROR - Line 18:47] Ports in reaction rules are either scoped variables or declared names\n" +
                    "[ERROR - Line 18:65] Control Test shouldn't have arity 0!\n" +
                    "[ERROR - Line 18:84] Control Test shouldn't have arity 0!\n" +
                    "[ERROR - Line 21:34] Variable used in model definition\n" +
                    "[WARNING] The following names are declared and never used: Bob Test \n" +
                    "[RESULT : FAILED]",example3Result);
    }
}