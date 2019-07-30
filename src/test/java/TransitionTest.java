import core.graphModels.TransitionDotImporter;
import core.graphModels.storing.GraphsCollection;
import core.graphModels.verticesAndEdges.TransitionEdge;
import core.graphModels.verticesAndEdges.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.jupiter.api.Test;

// I verify importing is in check with bigmc results
class TransitionTest {

    private String transition1;


    TransitionTest() {
        transition1 = "src/test/java/models/transitionTest1.bigraph";


        transition1Test();
    }


    @Test
    void transition1Test() {
        new TransitionDotImporter(transition1);
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph = GraphsCollection.getInstance().getTransitionGraph();
        assertEquals(transitionGraph.vertexSet().size(), 19);
    }


}
