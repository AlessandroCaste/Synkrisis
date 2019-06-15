package core.setup;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bigmc {

    private static Logger logger = Logger.getLogger("Report");

    private String filename;
    private String modelName;
    //TODO Args parameters to pass to bigmc binaries

    public Bigmc(String filename,String modelName) {
        this.filename = filename;
        this.modelName = modelName;
        executeBigmc();
    }

    // Sending input to bigmc
    private void executeBigmc() {

        try{
            logger.log(Level.INFO, "Executing bigmc commands");
            String workingDirectory = System.getProperty("user.dir");
            // TODO What about implementing bigmc inside .jar?
            ProcessBuilder pb = new ProcessBuilder("src/main/resources/bigmc", "-s", filename);
            pb.directory(new File(workingDirectory));
            pb.redirectErrorStream(true);
            // pb.redirectOutput(new File(modelName+"/"+modelName+".transition"));

            // I redirect bigmc output to a temp file in order to scan it
            pb.redirectOutput(new File(modelName+"/"+modelName+".temp"));
            Process p = pb.start();
            logger.log(Level.INFO, "Waiting for bigmc process..");
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Can't work with bigmc: is it correctly installed?");
            logger.log(Level.SEVERE,"Can't interface with bigmc input stream, probably has to do with the terminal commands not being properly recognized");
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

            // We'll have a BufferedWriter to store the output print on the terminal and a BufferedWriter for transition files
            BufferedWriter outputTxt = new BufferedWriter(new FileWriter(modelName + "/bigmc output.txt"));
            BufferedWriter transition = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".transition(" + transition_counter +")",true));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if(line.equals("Transition system:")) {
                    isTransition = true;
                    line = sc.nextLine();
                    if(transition_counter > 0)
                        transition = new BufferedWriter(new FileWriter(modelName + "/" + modelName + ".transition(" + transition_counter +")",true));
                    transition.write(line + "\n");
                }else if(line.equals("End of the transition system")) {
                    isTransition = false;
                    transition.close();
                    transition_counter++;
                }else if(isTransition)
                    transition.write(line + "\n");
                else {
                    System.out.println(line);           // I print out non-transition lines
                    outputTxt.write(line + "\n");
                }
            }
            //Closing everything
            sc.close();
            transition.close();
            outputTxt.close();

            //Rename lost output transition
            File lastTransitionFile = new File(modelName + "/" + modelName + ".transition(" + (transition_counter - 1) + ")");
            boolean renameResult = lastTransitionFile.renameTo(new File(modelName + "/" + modelName + ".transition"));
            if(!renameResult)
                logger.log(Level.WARNING,"Couldn't correctly rename the last.transition file!");

            // In case I generated multiple transition systems I move them to a separate folder
            if(transition_counter > 0)
                for(int counter = 0; counter < transition_counter - 1; counter++)
                    FileUtils.moveFile(new File(modelName + "/" + modelName + ".transition(" + counter + ")"),
                            new File(modelName + "/" + "intermediate transitions/" + modelName + ".transition(" + counter + ")"));

            // Deleting the temp file
            boolean deletionResult;
            deletionResult = tempFile.delete();
            if(!deletionResult)
                logger.log(Level.WARNING,"Couldn't correctly delete .temp file!");

        } catch (FileNotFoundException e) {
            System.out.println("Inner error while scanning Bigmc output. Check the log file for further info");
            logger.log(Level.SEVERE, "File not found when using the scanner\n Stack trace: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Inner error while scanning Bigmc output. Check the log file for further info");
            logger.log(Level.SEVERE, "Unexpected crash due to BufferWriter object\n Stack trace: " + e.getMessage());
        }
    }

}
