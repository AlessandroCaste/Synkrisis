package core.clishell;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.ShellFactory;
import core.setup.Main;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.CloseShieldInputStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//TODO Interactive shell resets execution folders each time
public class InteractiveShell {

    private ExecutionSettings loadedSettings = new ExecutionSettings();
    private StringBuilder sb = new StringBuilder();

    @Command(name="load",abbrev = "l",description = "Load model from file") // one,
    public void load(@Param(name="path") String filePath) {
        if(new File(filePath).exists()) {
            loadedSettings.setFilePath(filePath);
            loadedSettings.setRunFullAnalysis();
        }
        else
            System.out.println("Couldn't find " + filePath);
    }

    @Command(name="set",abbrev = "s") // two
     public void set(@Param(name="type") String type,@Param(name="value") String value) {
        if(type.equalsIgnoreCase("print")) {
            if (value.equalsIgnoreCase("model"))
                loadedSettings.enablePrintModel();
            else if (value.equalsIgnoreCase("transition"))
                loadedSettings.enablePrintTransition();
            else if (value.equalsIgnoreCase("all")) {
                loadedSettings.enablePrintModel();
                loadedSettings.enablePrintTransition();
            } else if (value.equalsIgnoreCase("nothing"))
                loadedSettings.disablePrinting();
        } else if(type.equalsIgnoreCase("steps")) {
            try {
                int steps = Integer.parseInt(value);
                loadedSettings.setSteps(steps);
            } catch(NumberFormatException e) {
                System.out.println("You must submit an integer value for steps");
            }
        } else if(type.equalsIgnoreCase("intermediate")) {
            try {
                int steps = Integer.parseInt(value);
                loadedSettings.setStatisticsFrequency(steps);
            } catch (NumberFormatException e) {
                System.out.println("You must submit an integer value for intermediate frequency printing");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    @Command(name="output",abbrev = "o")
     public void output(@Param(name="mchecker") String modelChecker) {
        loadedSettings.addExportingLanguage(modelChecker);
    }

    @Command(name="tran",abbrev = "t")
        public void transition(@Param(name="filename") String filename){
            if(new File(filename).exists()) {
                loadedSettings.setProcessTransitionOnly();
                loadedSettings.setFilePath(filename);
            } else
                System.out.println("Couldn't find the specified file: " + filename);
        }


    @Command(name = "write", abbrev = "w")
     public void writeModel(@Param(name="filename") String filename) {
        Scanner modelScanner = new Scanner(new CloseShieldInputStream(System.in));
        // Normalize filename
        filename = FilenameUtils.removeExtension(filename.replaceAll("\\W+", ""));
        System.out.println("Input your model, type '%end' when you've done or %run to directly run the new model");
        String line = "";
        while(!line.equals("%end") && !line.equals("%run")) {
            line = modelScanner.nextLine();
            if(line.equals("%end") || line.equals("%run")) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".bigraph"));
                    writer.write(sb.toString());
                    writer.close();
                } catch (IOException e) {
                    System.out.println("[FATAL ERROR] Can't write model to file");
                }
            } else
                sb.append(line).append("\n");
        } if(line.equals("%run")) {
            loadedSettings.setFilePath(filename+".bigraph");
            Main.execution(loadedSettings);
            loadedSettings.reset();
        }
        modelScanner.close();
    }

    @Command(name="run", abbrev = "r")
     public void run(){
        if(loadedSettings.getFilePath()!=null && !loadedSettings.isProcessTransitionOnly()) {
            Main.execution(loadedSettings);
            loadedSettings.reset();
        } else if (loadedSettings.getFilePath() != null && loadedSettings.isProcessTransitionOnly()){
            Main.transitionOnlyExecution(loadedSettings);
            loadedSettings.reset();
        } else
            System.out.println("You must first specify a model or a transition file!");
    }

     public void loop() {
        try {
            ShellFactory.createConsoleShell("Synkrisis", "", new InteractiveShell()).commandLoop();
        } catch (IOException e) {
            System.out.println("No such file exists");
        }
    }

}

