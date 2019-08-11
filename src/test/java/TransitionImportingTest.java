import core.graphs.TransitionDotImporter;
import core.graphs.customized.TransitionGraph;
import core.graphs.customized.edges.TransitionEdge;
import core.graphs.customized.vertices.TransitionVertex;
import core.graphs.storing.GraphsCollection;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// I verify importing is in check with bigmc results
class TransitionImportingTest {

    private String transition1;
    private String transition2;


    TransitionImportingTest() {
        executeModel();
    }


    // Note that this test may work only with specific implementations of bigmc (different binaries may mix parallel regions)
    @Test
    void executeModel() {
        TransitionDotImporter transitionDotImporter = new TransitionDotImporter("src/test/java/models/transition.dot",true);
        transitionDotImporter.processTransition();
        TransitionGraph transitionGraph = GraphsCollection.getInstance().getTransitionGraph();
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> graph = transitionGraph.getGraph();
        assertEquals(18, graph.vertexSet().size());
        for(TransitionVertex tv : graph.vertexSet()) {
            if(tv.getLabel().contains("Nurse[pager].nil | Doctor[-].nil")
                                    || tv.getLabel().contains("Doctor[-].nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("Doctor[-].nil | Agent.nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("Agent.nil | Nurse[pager].nil | Agent.nil | Doctor[-].nil"))
                assertTrue(transitionGraph.vertexContainsMarker(tv,"he_forgot_the_pager"));
            else
                assertFalse(transitionGraph.vertexContainsMarker(tv,"he_forgot_the_pager"));
            if(tv.getLabel().contains("Nurse[pager].nil | Doctor[pager].nil") || tv.getLabel().contains("Doctor[pager].nil | Nurse[pager].nil")
                                    || tv.getLabel().contains("(Doctor[pager].nil | Agent.nil | Nurse[pager].nil)")
                                    || tv.getLabel().contains("Agent.nil | Nurse[pager].nil | Doctor[pager].nil"))
                assertTrue(transitionGraph.vertexContainsMarker(tv,"objective_met"));
            else {
                assertFalse(transitionGraph.vertexContainsMarker(tv,"objective_met"));
            }
            if(tv.getLabel().contains("Room[nursestation].(Agent.nil | Nurse[pager].nil | Doctor[pager].nil)"))
                assertTrue(transitionGraph.vertexContainsMarker(tv,"nursestation_meeting"));
            else
                assertFalse(transitionGraph.vertexContainsMarker(tv,"nursestation_meeting"));
        }
        assertEquals(63,graph.edgeSet().size());
    }

}
