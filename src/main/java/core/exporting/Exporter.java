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

    // Whether it was input only a transition file by itself
    private boolean transitionOnly;

    private static Logger logger = Logger.getLogger("Report");


    private static Exporter instance;

    public void initialize(ArrayList<String> checkersList,boolean transitionOnly) {
        this.enabledLanguages = checkersList;
        this.transitionOnly = transitionOnly;
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
            else if (s.equalsIgnoreCase("spot") && !transitionOnly)
                spotExporting();
            else
                // All other model checkers have properties print to file
                printAllProperties(s.toLowerCase());
    }

    public boolean isEmpty() {
        return properties.isEmpty();
    }

    private void prismExporting() {
        boolean resultTraLab;
        boolean resultProp;
        String propertiesString = properties.get("PRISM","prop");
        prismExporter = new PrismExporter(transitionGraph);
        // Printing .tra and .lab files
        resultTraLab = prismExporter.translate();
        // Printing .prop file
        if(propertiesString.replaceAll("\\s+","").equals("")) {
            System.out.println("No PRISM properties have been specified");
            resultProp = false;
        } else
             resultProp = printToFile("prism", "prop", propertiesString);
        if(resultTraLab)
            System.out.println("Printing of PRISM .tra and .lab files completed");
        else
            System.out.println("Problems recorded during printing of PRISM .tra and .lab files");
        if(resultProp)
            System.out.println("Printing of .prop file is successful");
        else
            System.out.println("Can't print out PRISM .prop file");
    }

    private void spotExporting() {
        boolean result = false;
        if(spotInfo==null)
            System.out.println("SPOT properties have not correctly been specified");
        else {
            SpotExporter spotExporter = new SpotExporter(transitionGraph,spotInfo);
            result = spotExporter.translate();
        }
        if(result)
            System.out.println("Spot exporting concluded");
        else
            System.out.println("Problems recorded during spot exporting");
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
        boolean result;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(modelName + "/" + "/" + modelChecker + "." + extension), false));
            writer.write(text);
            writer.close();
            result = true;
        } catch (IOException e) {
            System.out.println("Can't output the " + extension + " properties");
            logger.log(Level.SEVERE, "Error with properties buffer writer. Something's off with the file, possibly authorization. Extension was : " + extension + "\nStack trace " + e.getMessage());
            result = false;
        }
        return result;
    }

}
