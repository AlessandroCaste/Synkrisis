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



    public CLI(String[] args) {


        this.args = args;

        options.addOption(Option.builder("l").longOpt("load").hasArg(true).desc("load a .bigraph model for processing").required(true).build());
        options.addOption("o","output-translation",true,"feed edges to PRISM and SPOT for transition analysis");
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
                String fileName = cmd.getOptionValue("load");
                settings.setFilePath(fileName);
            }
            if (cmd.hasOption("o")) {
                ArrayList<String> list = new ArrayList<>(Arrays.asList(cmd.getOptionValues("o")));
                if(list.contains("prism"))
                    settings.enablePrismExporting();
                if (list.contains("spot"))
                    settings.enableSpotExporting();
            }
            if(cmd.hasOption("g"))
                settings.enablePrintModel();
            if(cmd.hasOption("m")) {
                int steps = Integer.parseInt(cmd.getOptionValue("m"));
                if(steps > 0)
                    settings.setSteps(steps);
                else
                    System.out.println("Invalid number of steps specified: no step limit has been set\n");
            }
            if(cmd.hasOption("p"))
                settings.enablePrintNewStates();
            if(cmd.hasOption("r")) {
                int frequency = Integer.parseInt(cmd.getOptionValue("r"));
                if(frequency > 0)
                    settings.setStatisticsFrequency(frequency);
                else
                    System.out.println("Invalid frequency of edges print: it's been set to 0");
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

    public ExecutionSettings loadSettings() {
        return settings;
    }

}