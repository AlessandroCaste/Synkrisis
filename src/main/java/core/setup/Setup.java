package core.setup;


import antlr.bigraph.bigraphLexer;
import antlr.bigraph.bigraphParser;
import core.syntax.ErrorListener;
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

class Setup {

    private File inputFile;
    private static Logger logger = Logger.getLogger("Report");
    private ParseTree modelTree;
    private boolean successfulSetup = true;
    private FileHandler fh;


    Setup(File inputFile) {
         this.inputFile = inputFile;
    }


    void setupLogger() {
        String filename = "filename";
        try {
            logger.setUseParentHandlers(false);
            filename = inputFile.getName();
            filename = FilenameUtils.removeExtension(filename);
            // This block configure the logger with handler and formatter
            File oldFile = new File(filename+".log");
            boolean deletionResult = true;
            if(oldFile.exists())
                deletionResult = oldFile.delete();
            if(!deletionResult)
                throw new IOException();
            fh = new FileHandler(filename + ".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // Here starts the logging
            logger.info("Log started");
        } catch (SecurityException | IOException e) {
            System.out.println("[FATAL ERROR] Can't setup the logger");
            successfulSetup = false;
            logger.log(Level.SEVERE, "Error raised while initializing " + filename + " directory and the logging procedures" + "\nStack trace: " + e.getMessage());
        }
    }

    // File setup for analysis
    @SuppressWarnings("Duplicates")
    void setupParser() {
        try {
            logger.log(Level.INFO, "Parsing tree creation started");
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new bigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            bigraphParser parser = new bigraphParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ErrorListener.INSTANCE);
            modelTree = parser.bigraph();
        } catch(FileNotFoundException e) {
            // TODO : Execution halts goes on and throws unexpected errors
            successfulSetup = false;
            System.out.println("[FATAL ERROR] File \"" + inputFile.getPath() + "\" not found");
            logger.log(Level.SEVERE,"Couldn't find the requested file " + inputFile.getAbsolutePath() + "\nStack trace:" + e.getMessage());
        } catch (IOException e) {
            successfulSetup = false;
            System.out.println("[FATAL ERROR] Can't create the Lexer to start analysis" + inputFile.getName());
            logger.log(Level.SEVERE, "Can't create CharStreams from the InputStream of " + inputFile.getName() + "\nStack trace:" + e.getMessage());
        } catch (ParseCancellationException | NumberFormatException e) {
            successfulSetup = false;
            System.out.println(e.getMessage());
            System.out.println("Parsing can't proceed");
            logger.log(Level.SEVERE,e.getMessage());
        }
        logger.log(Level.INFO,"Parsing tree creation concluded");
    }

    void closeLogger() {
        logger.log(Level.INFO,"Logging complete");
        //TODO translation only
        if(fh != null)
            fh.close();
    }

    boolean isSuccessful() {
        return successfulSetup;
    }

    ParseTree getModelTree() {
        return modelTree;
    }
}
