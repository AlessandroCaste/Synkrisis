import org.junit.jupiter.api.Test;

class RunAllTests {

    @Test
    void executeTests(){
        new SyntaxVisitorTest();
        new GraphBuildingTest();
    }

}
