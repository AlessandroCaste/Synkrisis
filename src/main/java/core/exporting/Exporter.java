package core.exporting;

import core.exporting.prismExporting.PrismExporter;
import core.exporting.spotExporting.SpotExporter;
import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import core.graphs.storing.GraphsCollection;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exporter {

    private GraphsCollection graphsCollection = GraphsCollection.getInstance();

    private Properties properties;
    private PrismExporter prismExporter;
    private SpotExporter spotExporter;
    private String modelName;
    // Here you may add new exporting packages. Common behavior is just printing the specified properties

    private ArrayList<String> enabledLanguages;

    private static Logger logger = Logger.getLogger("Report");


    private static Exporter instance;

    public void initialize(ArrayList<String> checkersList) {
        this.enabledLanguages = checkersList;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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
            //else if (s.equalsIgnoreCase("spot"))
              //  spotExporting();
            else
                printAllProperties(s);
    }

    public boolean isEmpty() {
        return properties.isEmpty();
    }

    private void prismExporting() {
        boolean result = true;
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph = graphsCollection.getTransitionGraph();
        HashMap<String,Integer> markerMap = graphsCollection.getMarkerMap();
        String propertiesString = properties.get("PRISM","prop");
        prismExporter = new PrismExporter(transitionGraph,modelName,markerMap,propertiesString);
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

    private void printAllProperties(String checker) {
        ArrayList<OutputFile> propertiesString = properties.get(checker);
        boolean result = true;
        for(OutputFile o : propertiesString) {
            result = result && printToFile(checker,o.getExtension(),o.getText());
        }
        if(result)
            System.out.println("Printing of " + checker + " properties is completed");
    }


    private boolean printToFile(String modelChecker, String extension, String text) {
        boolean result = true;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(modelName + "/" + modelName + "/" + modelChecker + "." + extension), false));
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
                graphsCollection.exportToSpot(modelBuilder.getAcceptanceInfo());
            if (executionSettings.isPrismExportingEnabled())
                if (propertiesString != null)
                    graphsCollection.exportToPrism(propertiesString);
                else
                    graphsCollection.exportToPrism("");
        }
    } */
}
