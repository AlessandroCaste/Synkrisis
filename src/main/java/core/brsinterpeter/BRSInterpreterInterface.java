package core.brsinterpeter;

public interface BRSInterpreterInterface {

    // Note that a model Checker can have whatever arguments for input
    boolean needsTranslation();
    boolean isSuccessful();
    void translate();
    void execute();


}
