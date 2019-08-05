import core.graphModels.storing.GraphsCollection;
import core.graphModels.verticesAndEdges.TransitionEdge;
import core.graphModels.verticesAndEdges.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// I verify importing is in check with bigmc results
class TransitionTest {

    private String transition1;


    TransitionTest() {
        transition1 = "src/test/java/models/transitionTest1.bigraph";
        transition1Test();
    }


    // Properties: objective_met = 2, he_forgot_the_pager = 3, nursestation_meeting = 4
    // Markers 0 and 1 are reserved for init and deadlock, necessary in PRISM exporting
    @Test
    void transition1Test() {
        core.Main.main(new String[] {"-l","src/test/java/models/hospital.bigraph","-m", "1500"});
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph = GraphsCollection.getInstance().getTransitionGraph();
        assertEquals(18, transitionGraph.vertexSet().size());
        for(TransitionVertex tv : transitionGraph.vertexSet()) {
            if(tv.getLabel().contains("Nurse[pager].nil | Doctor[-].nil") || tv.getLabel().contains("Doctor[-].nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("Doctor[-].nil | Agent.nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("Agent.nil | Nurse[pager].nil | Agent.nil | Doctor[-].nil"))
                assertTrue(tv.getPropertiesString().contains("3"));
            else
                assertFalse(tv.getPropertiesString().contains("3"));
            if(tv.getLabel().contains("Nurse[pager].nil | Doctor[pager].nil") || tv.getLabel().contains("Doctor[pager].nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("(Doctor[pager].nil | Agent.nil | Nurse[pager].nil)"))
                assertTrue(tv.getPropertiesString().contains("2"));
            else {
                assertFalse(tv.getPropertiesString().contains("2"));
            }
            if (tv.getLabel().contains("Agent.nil | Nurse[pager].nil | Doctor[pager].nil"))
                assertTrue(tv.getPropertiesString().contains("4"));
            else
                assertFalse(tv.getPropertiesString().contains("4"));

        }
    }


}
