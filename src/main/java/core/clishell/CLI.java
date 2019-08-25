package core.clishell;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CLI {

    private String[] args;

    private Options options = new Options();

    private ExecutionSettings settings = new ExecutionSettings();

    // Boolean to leave the program in case user just wanted to display -h
    private boolean leave = false;
    private boolean acceptable = false;



    public CLI(String[] args) {


        this.args = args;

        options.addOption("l","load", true,"load a .bigraph model for processing");
        options.addOption("t","transition-load",true,"load a .dot transition file for processing");
        options.addOption("o","output-translation",true,"format to external model checkers syntax");
        options.addOption("g","graph-print",false,"print model and reactions graphs");
        options.addOption("G","print-everything",false,"print all graphs produced, including edges");
        options.addOption("m","steps",true,"maximum number of execution steps");
        options.addOption("p","print",false,"print newly discovered states during execution");
        options.addOption("r","statistics",true,"set frequency (steps) with which statistics about graph and edges are output");
        options.addOption("h", "help", false, "show help.");

    }

    // Values are set inside the ExecutionSettings class. Logger is started when the filename is parsed
    public void parse() {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if(cmd.hasOption("l")) {
                if(cmd.hasOption("t"))
                    System.out.println("Can't specify both .dot and .bigraph files");
                else {
                    String fileName = cmd.getOptionValue("load");
                    settings.setFilePath(fileName);
                    acceptable = true;
                }
            }
            if(cmd.hasOption("t")) {
                if(cmd.hasOption("l"))
                    System.out.println("Can't specify both .dot and bigraph files");
                else {
                    String path = cmd.getOptionValue("transition-load");
                    settings.setFilePath(path);
                    settings.setProcessTransitionOnly();
                    acceptable = true;
                }
            }
            if (cmd.hasOption("o")) {
                ArrayList<String> list = new ArrayList<>(Arrays.asList(cmd.getOptionValues("o")));
                for(String s : list)
                    settings.addExportingLanguage(s);
            }
            if(cmd.hasOption("g"))
                settings.enablePrintModel();
            if(cmd.hasOption("m")) {
                try {
                    int steps = Integer.parseInt(cmd.getOptionValue("m"));
                    if (steps > 0)
                        settings.setSteps(steps);
                    else
                        System.out.println("Invalid number of steps specified: no step limit has been set\n");
                } catch(NumberFormatException ne){
                    System.out.println("You shall input a valid integer for the number of steps.");
                    acceptable = false;
                }
            }
            if(cmd.hasOption("p"))
                settings.enablePrintNewStates();
            if(cmd.hasOption("r")) {
                try {
                    int frequency = Integer.parseInt(cmd.getOptionValue("r"));
                    if (frequency > 0)
                        settings.setStatisticsFrequency(frequency);
                    else
                        System.out.println("Invalid frequency of edges print: it's been set to 0");
                } catch(NumberFormatException ne){
                        System.out.println("You must input a valid integer for the frequency.");
                        acceptable = false;
                    }
            }
            if (cmd.hasOption("G")) {
                settings.enablePrintModel();
                settings.enablePrintTransition();
            }
            if (cmd.hasOption("h"))
                help();

        } catch (MissingArgumentException e) {
            System.out.println("Missing argument! Synkrisis can't proceed");
            help();
        } catch (MissingOptionException me) {
            System.out.println("You must load a model with -l (--load) command!");
            help();
        } catch (ParseException e) {
            System.out.println("Problems with input args!");
            help();
        }
    }

    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("Main", options);
        leave = true;
    }

    public boolean mustLeave() {
        return leave;
    }

    public boolean transitionOnly(){
        return settings.isProcessTransitionOnly();
    }

    public ExecutionSettings loadSettings() {
        return settings;
    }

    public boolean isAcceptable() {
        return acceptable;
    }
}