package core.modelcheckers;

public interface ModelChecker {

    // Note that a model Checker can have whatever arguments for input
    boolean needsTranslation();
    void translate();
    void execute();


}
