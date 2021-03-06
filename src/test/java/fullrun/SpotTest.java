package fullrun;

import core.graphs.storing.GraphsCollection;
import core.graphs.transitiongraph.TransitionEdge;
import core.graphs.transitiongraph.TransitionGraph;
import core.graphs.transitiongraph.TransitionVertex;
import core.setup.Main;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SpotTest {

    // Checking the HOA output of a spot exporting
    // Test mainly verifies acceptance states are correctly assigned

    SpotTest(){
        testSpot();
    }

    @Test
    void testSpot(){
        String filepath = "src/test/java/models/exportingTest1.bigraph";
        Main.main(new String[] {"-l",filepath,"-o","spot"});
        assertTrue(new File("nodes").exists());
        assertTrue(new File("nodes/transition.dot").exists());
        assertTrue(new File("nodes/spot/nodes.hoa").exists());

        File hoaFile = new File("nodes/spot/nodes.hoa");
        TransitionGraph transitionGraph = GraphsCollection.getInstance().getTransitionGraph();
        DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> graph = transitionGraph.getTransitionJgraph();
        HashMap<String,Integer> reactionMap = new HashMap<>();
        int counter = 0;
        for(String s : transitionGraph.getReactionNames()) {
            reactionMap.put(s, counter);
            counter++;
        }
        HashMap<TransitionVertex,String> idMap = transitionGraph.getIDMap();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(hoaFile));
            assertEquals("HOA: v1", reader.readLine());
            assertEquals("name: \"nodes\"",reader.readLine());
            assertEquals("States: 20",reader.readLine());
            assertEquals("Start: 0",reader.readLine());
            String apLine = reader.readLine();
            assertTrue(apLine.contains("r1"));
            assertTrue(apLine.contains("r2"));
            assertTrue(apLine.contains("r3"));
            assertTrue(apLine.contains("r4"));
            assertTrue(apLine.contains("4"));
            String accName = reader.readLine();
            assertTrue(accName.contains("generalized-Buchi 2"));
            String acceptance = reader.readLine();
            assertTrue(acceptance.contains("2 Inf(0)&Inf(1)"));
            assertEquals("--BODY--",reader.readLine());
            for(TransitionVertex tv : graph.vertexSet()) {
                String stateLine = reader.readLine();
                if(tv.getLabel().equals("(online_node[served_id].nil | online_node[served_id].nil | online_node[served_id].nil)"))
                    assertEquals(("State: " + idMap.get(tv)) + " {0}",stateLine.trim());
                else if(tv.getLabel().equals("(online_node[id].nil | online_node[id].nil | online_node[id].nil)"))
                    assertEquals(("State: " + idMap.get(tv) + " {1}"),stateLine.trim());
                else
                    assertEquals(("State: " + idMap.get(tv)),stateLine.trim());
                for(TransitionEdge te : graph.outgoingEdgesOf(tv)) {
                    assertEquals("["+ reactionMap.get(te.getLabel()) +"] " + idMap.get(graph.getEdgeTarget(te)),reader.readLine());
                }
            }
            assertEquals("--END--",reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
