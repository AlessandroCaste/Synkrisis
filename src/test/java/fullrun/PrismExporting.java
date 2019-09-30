package fullrun;

import core.graphs.storing.GraphsCollection;
import core.graphs.transitiongraph.TransitionEdge;
import core.graphs.transitiongraph.TransitionGraph;
import core.graphs.transitiongraph.TransitionVertex;
import core.setup.Main;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrismExporting {

    // Testing PRISM tweaks in the Virtualization model.
    // To better understand the test, it's advised to reference the DLTS print by Synkrisis

    PrismExporting() {
        testPrism();
    }

    @Test
    void testPrism() {
        String filepath = "src/test/java/models/Virtualization.bigraph";
        Main.main(new String[]{"-l", filepath, "-o", "prism"});
        assertTrue(new File("virtualization").exists());
        assertTrue(new File("virtualization/transition.dot").exists());
        assertTrue(new File("virtualization/prism/virtualization.tra").exists());
        assertTrue(new File("virtualization/prism/virtualization.lab").exists());
        assertTrue(new File("virtualization/prism/prism.prop").exists());

        TransitionGraph tg = GraphsCollection.getInstance().getTransitionGraph();
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> jgraph = tg.getTransitionJgraph();

        for (TransitionVertex tv : jgraph.vertexSet()) {

            // This node is well specified
            switch (tv.getVertexID()) {
                case "0":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 2);
                    for (TransitionEdge te : jgraph.outgoingEdgesOf(tv)) {
                        if (te.getLabel().equals("a"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.95);
                        else if (te.getLabel().equals("af"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.05);
                    }
                    break;
                // This node has two edges with p = 1; are probabilities split?
                case "1":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 2);
                    for (TransitionEdge te : jgraph.outgoingEdgesOf(tv)) {
                        if (te.getLabel().equals("l"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.5);
                        else if (te.getLabel().equals("c"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.5);
                    }
                    break;
                // Self-loops, with the exception of state 4 (not modified)
                case "3":
                case "7":
                case "4":
                case "5":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 1);
                    for (TransitionEdge te : jgraph.outgoingEdgesOf(tv))
                        assertEquals(jgraph.getEdgeWeight(te), 1);
                    break;
                // Here a self-loop is added
                case "6":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 2);
                    for (TransitionEdge te : jgraph.outgoingEdgesOf(tv)) {
                        switch (te.getLabel()) {
                            case "t":
                                assertEquals(jgraph.getEdgeWeight(te), 0.9);
                                break;
                            case "f":
                                assertEquals(jgraph.getEdgeWeight(te), 0.07);
                                break;
                            case "s-loop":
                                assertEquals(jgraph.getEdgeWeight(te), 0.03);
                                break;
                        }
                    }
                    break;
            }
        }
    }
}



