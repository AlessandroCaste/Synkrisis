package core;

public class ExecutionSettings {

    private String filePath;
    private int threads = 2;
    private int steps = 0;
    private int statisticsFrequency = 0;
    private boolean printSteps;
    private boolean printNewStates = false;
    private String outputModelChecker;
    private boolean exporting = false;
    private boolean printEnabled = false;
    private boolean printTransition = false;
    private boolean printIntermediate = false;
    private boolean bigmcReady = false;
    private String bigmcFile;

    ExecutionSettings() { }


    public String getFilePath() {
        return filePath;
    }

    public int getThreads() {
        return threads;
    }

    public int getSteps() {
        return steps;
    }

    public int getStatisticsFrequency() {
        return statisticsFrequency;
    }

    public boolean canPrintSteps() {
        return printSteps;
    }

    public boolean canPrintNewStates() { return printNewStates; }

    String getOutputModelChecker() {
        return outputModelChecker;
    }

    boolean isPrintEnabled() { return printEnabled; }

    boolean isExportingEnabled (){
        return exporting;
    }

    public String getBigmcFile() { return bigmcFile; }

    public boolean isBigmcReady() { return bigmcReady; }

    void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    void setThreads(int threads) {
        this.threads = threads;
    }

    void setSteps(int steps) {
        this.steps = steps;
    }

    void setStatisticsFrequency(int statisticsFrequency) {
        this.statisticsFrequency = statisticsFrequency;
    }

    void setPrintSteps(boolean printSteps) {
        this.printSteps = printSteps;
    }

    void setOutputModelChecker(String outputModelChecker) {
        this.outputModelChecker = outputModelChecker;
        exporting = true;
    }

    void enablePrintNewState() {
        printNewStates = true;
    }

    void enablePrint() {
        printEnabled = true;
    }

    void enablePrintTransition() {
        printTransition = true;
    }

    void enablePrintIntermediate() {
        printIntermediate = true;
    }

    void setBigmcFile(String fileName) { this.bigmcFile = fileName; }

    void setBigmcReady() { this.bigmcReady = true; }


}

