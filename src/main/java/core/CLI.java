package core;

import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class CLI {

    private String[] args;

    private Options options = new Options();
    private static Logger logger = Logger.getLogger("Report");
    private static FileHandler fh;

    private ExecutionSettings settings = new ExecutionSettings();



    CLI(String[] args) {


        this.args = args;

        options.addOption(Option.builder("l").longOpt("load").hasArg(true).desc("load a .bigraph model for processing").required(true).build());
        options.addOption("o","output-translation",true,"feed transitions to PRISM and SPOT for transition analysis");
        options.addOption("g","graph-print",false,"print model and reactions graphs");
        options.addOption("m","steps",true,"maximum number of steps");
        options.addOption("p","print",false,"print newly discovered states during execution");
        options.addOption("r","statistics",true,"set frequency (steps) with which statistics about graph and transitions are output");
        options.addOption("G","print-everything",false,"print all graphs produced");
        options.addOption("h", "help", false, "show help.");

    }

    // Values are set inside the ExecutionSettings class. Logger is started when the filename is parsed
    void parse() {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if(cmd.hasOption("l")) {
                String fileName = cmd.getOptionValue("load");
                setupLogger(fileName);
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
                settings.enablePrint();
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
                    System.out.println("Invalid frequency of transitions print: it's been set to 0");
            }
            if (cmd.hasOption("G")) {
                settings.enablePrint();
                settings.enablePrintTransition();
                settings.enablePrintIntermediateTransitions();
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
        System.exit(0);
    }

    private static void setupLogger(String filename) {
        try {
            logger.setUseParentHandlers(false);

            // This block configure the logger with handler and formatter
            filename = FilenameUtils.getName(filename);
            filename = FilenameUtils.removeExtension(filename);
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

    ExecutionSettings loadSettings() {
        return settings;
    }

}