import org.junit.jupiter.api.Test;

class RunAllTests {

    @Test
    void executeTests(){
        new SyntaxVisitorTest();
        new GraphBuildingTest();
        new TransitionImportingTest();
    }

    // Since this test run a complete run of Synkrisis it's kept by itself
    @Test
    void executeFullProgram() {
        new ExportingTest();
    }

}
