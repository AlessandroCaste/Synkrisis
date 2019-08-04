package core.clishell;

public class ExecutionSettings {

    private String filePath;
    private int steps = 0;
    private int statisticsFrequency = 0;
    private boolean printStepsEnabled = false;
    private boolean printNewStatesEnabled = false;
    private boolean prismExportingEnabled = false;
    private boolean spotExportingEnabled = false;
    private boolean spotExportingReady = false;
    private boolean exportingEnabled = false;
    private boolean printModelEnabled = false;
    private boolean printTransitionEnabled = false;
    private boolean processTransitionOnly = false;
    private String  transitionFile;
    private String  modelName = "";

    ExecutionSettings() { }

    public String getFilePath() {
        return filePath;
    }

    public int getSteps() {
        return steps;
    }

    public int getStatisticsFrequency() {
        return statisticsFrequency;
    }

    public boolean isPrintStepsEnabled() {
        return printStepsEnabled;
    }

    public boolean isPrintNewStatesEnabled() {
        return printNewStatesEnabled;
    }

    public boolean isPrismExportingEnabled() {
        return prismExportingEnabled;
    }

    public boolean isSpotExportingEnabled() {
        return spotExportingEnabled;
    }

    public boolean isSpotExportingReady() { return spotExportingReady; }

    public boolean isExportingEnabled() { return exportingEnabled; }

    public boolean isPrintModelEnabled() {
        return printModelEnabled;
    }

    public boolean isPrintTransitionEnabled() {
        return printTransitionEnabled;
    }

    public boolean isProcessTransitionOnly() {
        return this.processTransitionOnly;
    }

    public String getTransitionFile() {
        return this.transitionFile;
    }

    public String getModelName() { return this.modelName; }

    void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    void setSteps(int steps) {
        this.steps = steps;
    }

    void setStatisticsFrequency(int statisticsFrequency) {
        this.statisticsFrequency = statisticsFrequency;
    }

    public void setPrintSteps(boolean printSteps) {
        this.printStepsEnabled = printSteps;
    }

    void enablePrintNewStates() {
        this.printNewStatesEnabled = true;
    }

    void enablePrismExporting() {
        exportingEnabled = true;
        this.prismExportingEnabled = true;
    }

    void enableSpotExporting() {
        exportingEnabled = true;
        this.spotExportingEnabled = true;
    }

    public void setSpotExportingReady(boolean spotExportingReady) {
        this.spotExportingReady = spotExportingReady;
    }

    void enablePrintModel() {
        this.printModelEnabled = true;
    }

    void enablePrintTransition() {
        this.printTransitionEnabled = true;
    }

    void disablePrinting() {
        this.printModelEnabled = false;
        this.printTransitionEnabled = false;
    }

    void setProcessTransitionOnly() {
        this.processTransitionOnly = true;
    }

    void setTransitionFile(String transitionFile) {
        this.transitionFile = transitionFile;
    }

    public void setModelName(String modelName) { this.modelName = modelName; }

}

