import core.graphs.TransitionDotImporter;
import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import core.graphs.storing.GraphsCollection;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

// I verify importing is in check with bigmc results
class TransitionImportingTest {

    private String transition1;


    TransitionImportingTest() {
        transition1 = "src/test/java/models/transitionTest1.bigraph";
        execute();
    }


    // Properties: he_forgot_the_pager = 0, nursestation_meeting = 1, objective_met = 2
    // Note that this test may work only with specific implementations of bigmc (different binaries may mix parallel regions)
    @Test
    void execute() {
        TransitionDotImporter transitionDotImporter = new TransitionDotImporter("src/test/java/models/transition.dot",true);
        transitionDotImporter.processTransition();
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph = GraphsCollection.getInstance().getTransitionGraph().getGraph();
        assertEquals(18, transitionGraph.vertexSet().size());
        for(TransitionVertex tv : transitionGraph.vertexSet()) {
            if(tv.getLabel().contains("Nurse[pager].nil | Doctor[-].nil") || tv.getLabel().contains("Doctor[-].nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("Doctor[-].nil | Agent.nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("Agent.nil | Nurse[pager].nil | Agent.nil | Doctor[-].nil"))
                assertTrue(tv.getPropertiesString().contains("0"));
            else
                assertFalse(tv.getPropertiesString().contains("0"));
            if(tv.getLabel().contains("Nurse[pager].nil | Doctor[pager].nil") || tv.getLabel().contains("Doctor[pager].nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("(Doctor[pager].nil | Agent.nil | Nurse[pager].nil)")
                                    || tv.getLabel().contains("Agent.nil | Nurse[pager].nil | Doctor[pager].nil"))
                assertTrue(tv.getPropertiesString().contains("2"));
            else {
                assertFalse(tv.getPropertiesString().contains("2"));
            }
            if(tv.getLabel().contains("Room[nursestation].(Agent.nil | Nurse[pager].nil | Doctor[pager].nil)"))
                assertTrue(tv.getPropertiesString().contains("1"));
            else
                assertFalse(tv.getPropertiesString().contains("1"));
        }
        assertEquals(63,transitionGraph.edgeSet().size());
        // Check that files have been created
        assertTrue(new File("hospital").exists());
        assertTrue(new File("hospital/hospital.png").exists());
        assertTrue(new File("hospital/transition.dot").exists());
        assertTrue(new File("hospital/transition.png").exists());
        assertTrue(new File("hospital/rules/doctor_moves.png").exists());
        assertTrue(new File("hospital/rules/dreamy_doctor.png").exists());
        assertTrue(new File("hospital/rules/nurse_moves.png").exists());
        assertTrue(new File("hospital.log").exists());
    }



}
