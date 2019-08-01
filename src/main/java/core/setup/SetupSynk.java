package core.setup;


import antlr.bigraph.bigraphLexer;
import antlr.bigraph.bigraphParser;
import core.syntaxAnalysis.ErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SetupSynk {

    private static Logger logger = Logger.getLogger("Report");
    private static FileHandler fh;
    private ParseTree modelTree;
    private boolean successfulSetup;


    public SetupSynk(File inputFile) {
        setupLogger(inputFile);
        this.successfulSetup = setupSynk(inputFile);
    }

    private static void setupLogger(File inputFile) {
        String filename = "";
        try {
            logger.setUseParentHandlers(false);
            filename = inputFile.getName();
            filename = FilenameUtils.removeExtension(filename);
            // This block configure the logger with handler and formatter
            // I create a local folder
            File newDirectory = new File(filename);
            if(!newDirectory.exists())
                new File(filename).mkdirs();
            fh = new FileHandler(filename + "/" + filename +".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // Here starts the logging
            logger.info("Log started");

        } catch (SecurityException | IOException e) {
            System.out.println("[FATAL ERROR] Can't setup the logger");
            logger.log(Level.SEVERE, "Error raised while initializing " + filename + " directory and the logging procedures" + "\nStack trace: " + e.getMessage());
        }
    }

    // File setup for analysis
    @SuppressWarnings("Duplicates")
    private boolean setupSynk(File inputFile) {
        boolean successfulSetup = false;
        try {
            logger.log(Level.INFO, "Parsing tree creation started");
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new bigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            bigraphParser parser = new bigraphParser(tokenStream);
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
