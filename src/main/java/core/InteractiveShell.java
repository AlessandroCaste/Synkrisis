package core;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.ShellFactory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class InteractiveShell {

    private ExecutionSettings loadedSettings = new ExecutionSettings();
    private StringBuilder sb = new StringBuilder();

    @Command(name="load",abbrev = "l",description = "Load model from file") // one,
    public void load(@Param(name="path") String filePath) {
        if(new File(filePath).exists())
            loadedSettings.setFilePath(filePath);
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
            }
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
        if(modelChecker.equalsIgnoreCase("prism"))
            loadedSettings.enablePrismExporting();
        else if(modelChecker.equalsIgnoreCase("spot"))
            loadedSettings.enableSpotExporting();
    }

    @Command(name="Output",abbrev = "O")
     public void output() {
        loadedSettings.enablePrismExporting();
        loadedSettings.enableSpotExporting();
    }

    @Command(name = "write", abbrev = "w")
     public void writeModel() {
        Scanner sc = new Scanner(System.in);
        boolean endCondition = false;
        while(sc.hasNextLine() && !endCondition) {
            String miao = sc.nextLine();
            sb.append(miao);
            System.out.println(miao);
            if(miao.equals("end"))
                endCondition = true;
        }
    }

    @Command(name="run", abbrev = "r")
     public void run(){
        if(loadedSettings.getFilePath()!=null)
            Main.execution(loadedSettings);
        else
            System.out.println("You must first specify a model!");
    }

     void loop() {
        try {
            ShellFactory.createConsoleShell("Synkrisis", "", new InteractiveShell()).commandLoop();
        } catch (IOException e) {
            System.out.println("No such file exists");
        }
    }
}

