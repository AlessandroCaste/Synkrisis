package core.exporting;

import core.exporting.prismExporting.PrismExporter;
import core.exporting.spotExporting.SpotExporter;
import core.exporting.spotExporting.SpotInfo;
import core.graphs.customized.TransitionGraph;
import core.graphs.storing.GraphsCollection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exporter {

    private GraphsCollection graphsCollection = GraphsCollection.getInstance();

    private TransitionGraph transitionGraph;
    private Properties properties;
    private String modelName;

    // SPOT exporting
    private SpotExporter spotExporter;
    private SpotInfo spotInfo;

    // PRISM exporting
    private PrismExporter prismExporter;

    // Here you may add new exporting packages. Common behavior is just printing the specified properties
    // By default specified properties get print, but you can change that behavior in 'execute' methods

    private ArrayList<String> enabledLanguages;

    private static Logger logger = Logger.getLogger("Report");


    private static Exporter instance;

    public void initialize(ArrayList<String> checkersList) {
        this.enabledLanguages = checkersList;
    }

    public void setTransitionGraph(TransitionGraph transitionGraph) {
        this.transitionGraph = transitionGraph;
        this.modelName = transitionGraph.getModelName();
    }

    public void addSpotInfo(SpotInfo spotInfo){
        this.spotInfo = spotInfo;
    }

    public static Exporter getInstance() {
        if(instance == null)
            return (instance = new Exporter());
        return instance;
    }

    private Exporter() {
        properties = new Properties();
    }

    public void addPropertyFile(String language, String format, String text) {
        properties.add(language,new OutputFile(format,text));
    }

    // From here you can add customized behavior for your exporter
    public void execute() {
        for (String s : enabledLanguages)
            if (s.equalsIgnoreCase("prism"))
                prismExporting();
            else if (s.equalsIgnoreCase("spot"))
                spotExporting();
            else
                // All other model checkers have properties print to file
                printAllProperties(s.toLowerCase());
    }

    public boolean isEmpty() {
        return properties.isEmpty();
    }

    private void prismExporting() {
        boolean result = true;
        String propertiesString = properties.get("PRISM","prop");
        prismExporter = new PrismExporter(transitionGraph);
        // Printing .tra and .lab files
        prismExporter.translate();
        // Printing .prop file
        if(propertiesString.replaceAll("\\s+","").equals(""))
            System.out.println("No PRISM properties have been specified");
        else
            result = printToFile("prism","prop",propertiesString);
        if(result)
            System.out.println("Printing of .prop file completed");
    }

    private void spotExporting() {
        if(spotInfo==null)
            System.out.println("SPOT properties have not correctly been specified");
        else {
            SpotExporter spotExporter = new SpotExporter(transitionGraph,spotInfo);
            spotExporter.translate();
        }
    }

    private void printAllProperties(String checker) {
        ArrayList<OutputFile> propertiesString = properties.get(checker);
        boolean result = false;
        boolean first = true;
        for(OutputFile o : propertiesString) {
            if(first) {
                result = printToFile(checker, o.getExtension(), o.getText());
                first = false;
            }
            else
                result = result && printToFile(checker,o.getExtension(),o.getText());
        }
        if(result)
            System.out.println("Printing of " + checker + " properties is completed");
    }


    private boolean printToFile(String modelChecker, String extension, String text) {
        boolean result = true;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(modelName + "/" + "/" + modelChecker + "." + extension), false));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("Can't output the " + extension + " properties");
            logger.log(Level.SEVERE, "Error with properties buffer writer. Something's off with the file, possibly authorization. Extension was : " + extension + "\nStack trace " + e.getMessage());
            result = false;
        }
        return result;
    }


/*
    //TODO add boolean control
    private static void dotExporting(ExecutionSettings executionSettings,GraphBuildingVisitor modelBuilder) {
        String propertiesString = modelBuilder.getPropertiesString();
        executionSettings.setSpotExportingReady(modelBuilder.isSpotReady());
        if (executionSettings.isExportingEnabled()) {
            System.out.println("\nMODEL EXPORTING");
            System.out.println("****************");
            // Model exporting
            if (executionSettings.isSpotExportingEnabled())
                graphsCollection.exportToSpot(modelBuilder.getSpotInfo());
            if (executionSettings.isPrismExportingEnabled())
                if (propertiesString != null)
                    graphsCollection.exportToPrism(propertiesString);
                else
                    graphsCollection.exportToPrism("");
        }
    } */
/*     public void exportToSpot(SpotInfo spotInfo) {
        if(spotInfo == null)
            System.out.println("[SPOT-TRANSLATION] No spot Acceptance has been specified");
        else
            new SpotExporter(transitionGraph,modelName,reactionNames,spotInfo);
    }*/
}
