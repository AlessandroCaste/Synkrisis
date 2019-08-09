package core.clishell;

import java.util.ArrayList;
import java.util.HashSet;

public class ExecutionSettings {

    private String filePath;
    private int steps = 0;
    private int statisticsFrequency = 0;
    private boolean printNewStatesEnabled = false;
    private boolean exportingEnabled = false;
    private HashSet <String> exporting = new HashSet<>();
    private boolean printModelEnabled = false;
    private boolean printTransitionEnabled = false;
    private boolean processTransitionOnly = false;
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

    public boolean isPrintNewStatesEnabled() {
        return printNewStatesEnabled;
    }

    public boolean isExportingEnabled() {
        return !exporting.isEmpty();
    }

    void addExportingLanguage(String language) {
        exporting.add(language);
    }

    public ArrayList<String> checkersList() {
        return new ArrayList<>(exporting);
    }

    public boolean isPrintModelEnabled() {
        return printModelEnabled;
    }

    public boolean isPrintTransitionEnabled() {
        return printTransitionEnabled;
    }

    public boolean isProcessTransitionOnly() {
        return this.processTransitionOnly;
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

    void enablePrintNewStates() {
        this.printNewStatesEnabled = true;
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

    void setRunFullAnalysis(){
        this.processTransitionOnly = false;
    }

    public void setModelName(String modelName) { this.modelName = modelName; }

}

