package core.setup;

import antlr.bigraph.BigraphLexer;
import antlr.bigraph.BigraphParser;
import core.syntaxAnalysis.ErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetupSynk {

    private static Logger logger = Logger.getLogger("Report");
    private ParseTree modelTree;
    private File inputFile;
    private boolean successfulSetup;


    public SetupSynk(File inputFile) {
        this.inputFile = inputFile;
        this.successfulSetup = setupSynk();
    }

    // File setup for analysis
    @SuppressWarnings("Duplicates")
    private boolean setupSynk() {
        boolean successfulSetup = false;
        try {
            logger.log(Level.INFO, "Parsing tree creation started");
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new BigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            BigraphParser parser = new BigraphParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ErrorListener.INSTANCE);
            modelTree = parser.bigraph();
            successfulSetup = true;
        } catch(FileNotFoundException e) {
            // TODO : Execution halts goes on and throws unexpected errors
            System.out.println("[FATAL ERROR] File \"" + inputFile.getPath() + "\" not found");
            logger.log(Level.SEVERE,"Couldn't find the requested file " + inputFile.getAbsolutePath() + "\nStack trace:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("[FATAL ERROR] Can't create the Lexer to start analysis" + inputFile.getName());
            logger.log(Level.SEVERE, "Can't create CharStreams from the InputStream of " + inputFile.getName() + "\nStack trace:" + e.getMessage());
        } catch (ParseCancellationException | NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Parsing can't proceed");
            logger.log(Level.SEVERE,e.getMessage());
        }
        logger.log(Level.INFO,"Parsing tree creation concluded");
        return successfulSetup;
    }

    public boolean isSuccessful() {
        return successfulSetup;
    }

    public ParseTree getModelTree() {
        return modelTree;
    }
}
