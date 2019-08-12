package functionalities;

import org.junit.jupiter.api.Test;

class RunFunctionalityTest {

    @Test
    void executeTests(){
        new SyntaxVisitorTest();
        new GraphBuildingTest();
        new TransitionImportingTest();
    }

}
