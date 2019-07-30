package core;

public class ExecutionSettings {

    private String filePath;
    private int steps = 0;
    private int statisticsFrequency = 0;
    private boolean printStepsEnabled = false;
    private boolean printNewStatesEnabled = false;
    private boolean prismExportingEnabled = false;
    private boolean spotExportingEnabled = false;
    private boolean printModelEnabled = false;
    private boolean printTransitionEnabled = false;
    private boolean processTransitionOnly = false;
    private String  transitionFile;
    private boolean bigmcReady = false;
    private String  bigmcFile;

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

    boolean isSpotExportingEnabled() {
        return spotExportingEnabled;
    }

    boolean isPrintModelEnabled() {
        return printModelEnabled;
    }

    boolean isPrintTransitionEnabled() {
        return printTransitionEnabled;
    }

    public boolean isProcessTransitionOnly() {
        return this.processTransitionOnly;
    }

    public String getTransitionFile() {
        return this.transitionFile;
    }

    public boolean isBigmcReady() {
        return bigmcReady;
    }

    public String getBigmcFile() {
        return bigmcFile;
    }

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
        this.prismExportingEnabled = true;
    }

    void enableSpotExporting() {
        this.spotExportingEnabled = true;
    }

    void enablePrintModel() {
        this.printModelEnabled = true;
    }

    void enablePrintTransition() {
        this.printTransitionEnabled = true;
    }

    void setProcessTransitionOnly() {
        this.processTransitionOnly = true;
    }

    void setTransitionFile(String transitionFile) {
        this.transitionFile = transitionFile;
    }

    void setBigmcReady() {
        this.bigmcReady = true;
    }

    void setBigmcFile(String bigmcFile) {
        this.bigmcFile = bigmcFile;
    }
}

