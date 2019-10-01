package fullrun;

import core.exporting.Exporter;
import core.exporting.prismExporting.PrismExporter;
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

class PrismTest {

    // Testing PRISM tweaks in the Virtualization model.
    // To better understand the test, it's advised to reference the DLTS print by Synkrisis through BigMC

    PrismTest() {
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
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> jgraph = Exporter.getInstance().getPrismGraph();

        for (TransitionVertex tv : jgraph.vertexSet()) {

            // This node is well specified
            switch (tv.getLabel()) {
                case "(VM.FrontEnd.User[id].Message.nil | VM.AuthServer[network].LocalStorage.nil | VM.CredStorage[network].nil | VM.GlobalMsgStorage.nil)":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 2);
                    for (TransitionEdge te : jgraph.outgoingEdgesOf(tv)) {
                        if (te.getLabel().equals("a"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.95);
                        else if (te.getLabel().equals("af"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.05);
                    }
                    break;
                // This node has two edges with p = 1; are probabilities split?
                case "(VM.FrontEnd.User[id].nil | VM.AuthServer[network].(User[id].Message.nil | LocalStorage.nil) | VM.CredStorage[network].nil | VM.GlobalMsgStorage.nil)":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 2);
                    for (TransitionEdge te : jgraph.outgoingEdgesOf(tv)) {
                        if (te.getLabel().equals("l"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.5);
                        else if (te.getLabel().equals("c"))
                            assertEquals(jgraph.getEdgeWeight(te), 0.5);
                    }
                    break;
                // Self-loops, with the exception of state 4 (not modified)
                case "(VM.FrontEnd.UnauthorizedUser[id].nil | VM.AuthServer[network].LocalStorage.nil | VM.CredStorage[network].nil | VM.GlobalMsgStorage.nil)":
                case "(VM.CredStorage[network].nil | VM.AuthServer[network].LocalStorage.nil | VM.FrontEnd.User[served].nil | VM.GlobalMsgStorage.nil)":
                case "(VM.GlobalMsgStorage.nil | VM.CredStorage[network].nil | VM.AuthServer[network].LocalStorage.Failure.nil | VM.FrontEnd.User[id].nil)":
                case "(VM.GlobalMsgStorage.Message.nil | VM.CredStorage[network].nil | VM.AuthServer[network].(User[id].nil | LocalStorage.nil) | VM.FrontEnd.User[id].nil)":
                case "(VM.GlobalMsgStorage.Message.nil | VM.CredStorage[network].nil | VM.AuthServer[network].LocalStorage.nil | VM.FrontEnd.User[served].nil)":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 1);
                    for (TransitionEdge te : jgraph.outgoingEdgesOf(tv))
                        assertEquals(jgraph.getEdgeWeight(te), 1);
                    break;
                // Here a self-loop is added
                case "(VM.GlobalMsgStorage.nil | VM.CredStorage[network].nil | VM.AuthServer[network].(LocalStorage.Message.nil | User[id].nil) | VM.FrontEnd.User[id].nil)":
                    assertEquals(jgraph.outgoingEdgesOf(tv).size(), 3);
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



