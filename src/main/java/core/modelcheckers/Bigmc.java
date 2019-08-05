package core.modelcheckers;

import core.clishell.ExecutionSettings;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bigmc implements ModelChecker{

    private static Logger logger = Logger.getLogger("Report");

    private String bigmcCompatibleFilepath;
    private ParseTree modelTree;
    private ExecutionSettings loadedSettings;
    private boolean isBigmcReady;


    public Bigmc(ExecutionSettings settings,ParseTree modelTree) {
        System.out.println("\nINTERFACING BIGMC");
        System.out.println("********************");
        this.loadedSettings = settings;
        this.bigmcCompatibleFilepath = settings.getFilePath();
        this.modelTree = modelTree;
    }

    // This specific implementation for the model checker parses with ANTLR, but the approach may be even simpler (or just different)
    @Override
    public boolean needsTranslation() {
        BigmcVisitor bigmcVisitor = new BigmcVisitor();
        bigmcVisitor.visit(modelTree);
        isBigmcReady = bigmcVisitor.isBigmcReady();
        return !isBigmcReady;
    }

    // I build up a command line string for bigmc
    private String createInputString() {
        StringBuilder input = new StringBuilder();

        // Bigmc location is set
        input.append("lib/bigmc -s ");

        // Setting a maximum number of steps. 0 means user didn't specify any
        if(loadedSettings.getSteps() != 0)
            input.append(" -m ").append(loadedSettings.getSteps());

        // Setting frequency statistics
        if(loadedSettings.getStatisticsFrequency() > 0)
            input.append(" -r ").append(loadedSettings.getStatisticsFrequency());

        // Print new states
        if(loadedSettings.isPrintNewStatesEnabled())
            input.append(" -p");

        // Basic model checking functionality
        // I distinguish the case the file was altered on purpose for bigmc
        if(isBigmcReady)
            input.append(" ").append(loadedSettings.getFilePath());
        else
            input.append(" ").append(bigmcCompatibleFilepath);

        return input.toString();

    }

    // Sending input to bigmc
    @Override
    public void execute() {
        String input = createInputString();
        String modelName = loadedSettings.getModelName();
        try{
            logger.log(Level.INFO, "Executing bigmc commands");
            String workingDirectory = System.getProperty("user.dir");

            // Linux case is standard case

            ProcessBuilder pb;
            //TODO wsl setup is temporary
            if(SystemUtils.IS_OS_WINDOWS)
                pb = new ProcessBuilder("pengwin", "-c", input);
            //TODO Don't know what to do with osx and has of now as the same linux commands
            else if (SystemUtils.IS_OS_MAC_OSX)
                pb = new ProcessBuilder("echo",input);
            // Linux case!
            else
                pb = new java.lang.ProcessBuilder("/bin/bash","-c",input);
            pb.directory(new File(workingDirectory));
            pb.redirectErrorStream(true);
            // I redirect bigmc output to a temp file in order to scan it
            pb.redirectOutput(new File(modelName+"/"+modelName+".temp"));
            Process p = pb.start();
            logger.log(Level.INFO, "Waiting for bigmc process..");
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Can't work with bigmc: is it correctly installed?");
            logger.log(Level.SEVERE, "Can't interface with bigmc input stream, probably has to do with the terminal commands not being properly recognized");
            File bigmcCompatibleFile = new File(bigmcCompatibleFilepath);
            if(bigmcCompatibleFile.exists())
                bigmcCompatibleFile.delete();
        }

        // The scanner will separate the transition system from all the other messages (that get output to CLI)
        // Multiple transition systems can be output by the user; because of that we keep count of them through transition_counter
        Scanner sc;
        try {
            File tempFile = new File(modelName+"/"+modelName+".temp");
            sc = new Scanner(tempFile);
            // When transition is true we're scanning a transition graph (a dot file)
            boolean isTransition = false;
            // We may have multiple transition file, so we keep a counter
            int transition_counter = 0;

            BufferedWriter transition = new BufferedWriter(new FileWriter(modelName + "/" + modelName + "-" + transition_counter + ".dot",true));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.equals("digraph reaction_graph {")) {
                    isTransition = true;
                    if(transition_counter > 0)
                        transition = new BufferedWriter(new FileWriter(modelName + "/" + modelName + "-" + transition_counter + ".dot",true));
                    transition.write(line + "\n");
                }else if(line.equals("}")) {
                    isTransition = false;
                    transition.write(line);
                    transition.close();
                    transition_counter++;
                }else if(isTransition)
                    transition.write(line + "\n");
                else {
                    System.out.println(line);           // I print out non-transition lines
                }
            }
            //Closing everything
            sc.close();
            transition.close();

            //Rename lost output transition
            transition_counter = transition_counter - 1;
            File lastTransitionFile = new File(modelName + "/" + modelName + "-" + (transition_counter) + ".dot");

            //TODO WHY
            // I create a new transition file, deleting an eventual old one
            File oldFile = new File(modelName + "/" + modelName + ".dot");
            boolean deleteResult = true;
            if(oldFile.exists())
                deleteResult = oldFile.delete();
            boolean renameResult = lastTransitionFile.renameTo(new File(modelName + "/" + "transition.dot"));
            if(!renameResult || !deleteResult)
                logger.log(Level.WARNING,"Couldn't correctly rename the last .dot file!");
            else
                logger.log(Level.INFO,"Renaming of the last .dot file successful");

            // In case I generated multiple transition systems I move them to a separate folder
            if(transition_counter > 0)
                for(int counter = 0; counter < transition_counter - 1; counter++)
                    FileUtils.moveFile(new File(modelName + "/" + modelName + "-" + counter + ".dot" ),
                            new File(modelName + "/" + "intermediate edges/" + modelName + "-" + counter + ".dot" ));

            // Deleting the temp file
            boolean deletionResult;
            deletionResult = tempFile.delete();
            if(!deletionResult)
                logger.log(Level.WARNING,"Couldn't correctly delete .temp file!");
            if(!isBigmcReady) {
                boolean result = new File(bigmcCompatibleFilepath).delete();
                if(!result) {
                    System.out.println("Can't delete bigmc-filtered file");
                    throw new IOException();
                }
                logger.log(Level.INFO,"Temporary bigmc translation has been eliminated successfully");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inner error while scanning Bigmc output. Check the log file for further info");
            logger.log(Level.SEVERE, "File not found when using the scanner\nStack trace: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Inner error while scanning Bigmc output. Check the log file for further info");
            logger.log(Level.SEVERE, "Unexpected crash due to BufferWriter object\nStack trace: " + e.getMessage());
        }
    }

    // Translation requires only weights to be removed: properties are automatically filtered by bigmc
    public void translate(){
        String path = loadedSettings.getFilePath();
        System.out.println("Model translation underway");
        logger.log(Level.INFO,"Starting translation of bigraph into bigmc-readable file");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            // I create a new file with random name for temporary storing of bigmc-epurated model
            bigmcCompatibleFilepath =  RandomStringUtils.random(10, true, true);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(bigmcCompatibleFilepath)));
            String line;
            boolean endingCondition = false;

            while ((line = reader.readLine()) != null && !endingCondition) {
                if(line.contains("rule")) {
                    line = line.replaceAll("\\(0.\\d+\\)\\s*->","->"); }
                if(line.contains("properties"))
                    endingCondition = true;
                else
                    writer.write(line+"\n");
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.out.println("Problems during bigmc translation!");
            logger.log(Level.SEVERE,"Can't translate input to bigmc-readable file.\nStack: " + e.getMessage());
        }
        System.out.println("Translation complete");
    }

}
