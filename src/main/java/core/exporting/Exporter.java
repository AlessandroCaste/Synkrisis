package core.exporting;

import core.exporting.prismExporting.PrismExporter;
import core.exporting.spotExporting.SpotExporter;
import core.exporting.spotExporting.SpotInfo;
import core.graphs.transitiongraph.TransitionGraph;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exporter {

    private TransitionGraph transitionGraph;
    private Properties properties;
    private String modelName;

    // SPOT exporting
    private SpotExporter spotExporter;
    private SpotInfo spotInfo;

    // PRISM exporting
    private PrismExporter prismExporter;

    // Languages that have translation enabled
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
        properties.add(language,new ExtensionText(format,text));
    }

    /* When operating on new formats remember that:
    It's better to operate on new formats from this class.
    You can either printToFile properties, get only single Strings, or get the set of <extension,text> pairs.
    PRISM is a good reference for new custom formats and generally you could pass to a new format constructor the graph and the path for outputting.
    Check in the documentation for more info on this part, following there are some usage examples */
    public void execute() {
        for (String s : enabledLanguages)
            if (s.equalsIgnoreCase("prism"))
                prismExporting();
            else if (s.equalsIgnoreCase("spot") && !transitionOnly)
                spotExporting();
            else {
                printToFile("format","specification");
                ArrayList<ExtensionText> specificationList = properties.get("format");
                String specification = properties.get("format","extension");
            }
    }

    public boolean isEmpty() {
        return properties.isEmpty();
    }

    private void prismExporting() {
        boolean resultTraLab;
        boolean resultProp;
        // Creating a folder; (eventual) old one gets eliminated
        String path = createFolder("prism");
        prismExporter = new PrismExporter(transitionGraph,path);
        // Printing .tra and .lab files
        resultTraLab = prismExporter.translate();
        if(resultTraLab)
            System.out.println("Printing of PRISM .tra and .lab files completed");
        else
            System.out.println("Problems recorded during printing of PRISM .tra and .lab files");
        // Printing .prop file
        resultProp = printToFile("prism", "prop");
        if(resultProp)
            System.out.println("Printing of .prop file is successful");
        else
            System.out.println("Can't print out PRISM .prop file");
    }

    private void spotExporting() {
        boolean result = false;
        if(spotInfo==null)
            System.out.println("SPOT properties can't be read");
        else {
            spotExporter = new SpotExporter(transitionGraph,spotInfo);
            result = spotExporter.translate();
        }
        if(result)
            System.out.println("Spot exporting concluded");
        else
            System.out.println("[SPOT-EXPORTING] Exporting failed");
    }

    private boolean printToFile(String format, String extension) {
        boolean result = false;
        String text = properties.get(format, extension);
        if(!text.isEmpty()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(modelName + "/"  + format + "/" + format + "." + extension), false));
                writer.write(text);
                writer.close();
                result = true;
            } catch (IOException e) {
                System.out.println("Can't output the " + extension + " properties");
                logger.log(Level.SEVERE, "Error with properties buffer writer. Something's off with the file, possibly authorization. Extension was : " + extension + "\nStack trace " + e.getMessage());
                result = false;
            }
        }
        else
            System.out.println("[WARNING] Empty specification for " + format + " (" + extension + ")");
        return result;
    }

    private String createFolder(String format){
        String modelName = transitionGraph.getModelName();
        String path = modelName + "/" + format;
        File prismPath = new File(path);
        if(!prismPath.exists()) {
            if(!prismPath.mkdirs()) {
                System.out.println("Can't create a new '" + format + "' directory");
                logger.log(Level.WARNING,"Couldn't create a '" + format + "' folder for model outputting");
            }
        } else {
            try {
                FileUtils.deleteDirectory(prismPath);
                logger.log(Level.INFO, "Deleted old " + format + " output folder");
                if (!prismPath.mkdirs()) {
                    System.out.println("Can't create a new '" + format + "' directory");
                    logger.log(Level.WARNING, "Couldn't create a '" + format + "' folder for model outputting");
                }
            } catch (IOException e) {
                System.out.println("Can't delete old '" + format + "' directory");
                logger.log(Level.SEVERE, "Can't remove the old '" + format + "' directory. File not found?\nStack trace: " + e.getMessage());
            }
        }
        return path;
    }

}
