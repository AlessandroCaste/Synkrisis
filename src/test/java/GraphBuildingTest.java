import antlr.bigraph.bigraphLexer;
import antlr.bigraph.bigraphParser;
import core.graphs.GraphBuildingVisitor;
import core.graphs.customized.vertices.Vertex;
import core.graphs.storing.GraphsCollection;
import core.graphs.storing.RedexReactumPair;
import core.syntax.ErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphBuildingTest {

    private String additionPath;
    private String hospitalPath;
    private String namesWeightsTest;

    GraphBuildingTest() {
        additionPath = "src/test/java/models/addition.bigraph";
        hospitalPath = "src/test/java/models/hospital.bigraph";
        namesWeightsTest = "src/test/java/models/namesWeightsTest.bigraph";

        completeAdditionCheck();
        checkLinks();
    }


    @Test
    void completeAdditionCheck() {

        GraphBuildingVisitor graphBuildingVisitor = createVisitor(additionPath);
        Multigraph<Vertex, DefaultEdge> graph = GraphsCollection.getInstance().getModel();
        assertEquals(graph.vertexSet().size(),17);

        // I compare the label to verify mapping is correct
        ArrayList<String> labels = new ArrayList<>();
        labels.add("IN");
        labels.add("L");
        labels.add("Succ");
        labels.add("Succ");
        labels.add("Zero");
        labels.add("R");
        labels.add("Succ");
        labels.add("Succ");
        labels.add("Zero");
        labels.add("IN");
        labels.add("L");
        labels.add("Succ");
        labels.add("Zero");
        labels.add("R");
        labels.add("Succ");
        labels.add("Succ");
        labels.add("Zero");


        // Vertices are correctly set
        int i = 0;
        for(Vertex v : graph.vertexSet()) {
            assertEquals(v.getLabel(), labels.get(i));
            i++;
        }

        // Edges are correctly set
        ArrayList<DefaultEdge> edgeList = new ArrayList<>(graph.edgeSet());
        assertEquals(edgeList.size(),15);

        assertEquals("IN L",graph.getEdgeSource(edgeList.get(0)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(0)).getLabel());
        assertEquals("L Succ",graph.getEdgeSource(edgeList.get(1)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(1)).getLabel());
        assertEquals("Succ Succ", graph.getEdgeSource(edgeList.get(2)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(2)).getLabel());
        assertEquals("Succ Zero",graph.getEdgeSource(edgeList.get(3)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(3)).getLabel());
        assertEquals("Succ Zero",graph.getEdgeSource(edgeList.get(3)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(3)).getLabel());
        assertEquals("IN R", graph.getEdgeSource(edgeList.get(4)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(4)).getLabel());
        assertEquals("R Succ", graph.getEdgeSource(edgeList.get(5)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(5)).getLabel());
        assertEquals("Succ Succ",graph.getEdgeSource(edgeList.get(6)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(6)).getLabel());
        assertEquals("Succ Zero", graph.getEdgeSource(edgeList.get(7)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(7)).getLabel());
        assertEquals("IN L", graph.getEdgeSource(edgeList.get(8)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(8)).getLabel());
        assertEquals("L Succ", graph.getEdgeSource(edgeList.get(9)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(9)).getLabel());
        assertEquals("Succ Zero", graph.getEdgeSource(edgeList.get(10)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(10)).getLabel());
        assertEquals("IN R", graph.getEdgeSource(edgeList.get(11)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(11)).getLabel());
        assertEquals("R Succ", graph.getEdgeSource(edgeList.get(12)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(12)).getLabel());
        assertEquals("Succ Succ", graph.getEdgeSource(edgeList.get(13)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(13)).getLabel());
        assertEquals("Succ Zero", graph.getEdgeSource(edgeList.get(14)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(14)).getLabel());


        // Checking Redex 1
        ArrayList<RedexReactumPair> reactionsList = GraphsCollection.getInstance().getReactionsList();
        RedexReactumPair regression1 = reactionsList.get(0);
        RedexReactumPair regression2 = reactionsList.get(1);
        graph = regression1.getRedex();

        assertEquals(graph.vertexSet().size(),5);


        labels = new ArrayList<>();
        labels.add("IN");
        labels.add("L");
        labels.add("$1");
        labels.add("R");
        labels.add("Zero");
        labels.add("$1");


        // Vertices are correctly set
        i = 0;
        for(Vertex v : graph.vertexSet()) {
            assertEquals(v.getLabel(), labels.get(i));
            i++;
        }

        // Edges are correctly set
        edgeList = new ArrayList<>(graph.edgeSet());
        assertEquals(edgeList.size(),4);

        assertEquals("IN L", graph.getEdgeSource(edgeList.get(0)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(0)).getLabel());
        assertEquals("L $1", graph.getEdgeSource(edgeList.get(1)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(1)).getLabel());
        assertEquals("IN R", graph.getEdgeSource(edgeList.get(2)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(2)).getLabel());
        assertEquals("R Zero", graph.getEdgeSource(edgeList.get(3)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(3)).getLabel());

        // Checking Reactum 1
        graph = regression1.getReactum();

        assertEquals(graph.vertexSet().size(),1);

        for(Vertex v : graph.vertexSet())
            assertEquals(v.getLabel(), labels.get(i));

        edgeList = new ArrayList<>(graph.edgeSet());
        assertTrue(edgeList.isEmpty());

        // Checking Redex 2

        graph = regression2.getRedex();
        assertEquals(graph.vertexSet().size(),6);

        labels = new ArrayList<>();
        labels.add("IN");
        labels.add("L");
        labels.add("$1");
        labels.add("R");
        labels.add("Succ");
        labels.add("$2");

        // Vertices are correctly set
        i = 0;
        for(Vertex v : graph.vertexSet()) {
            assertEquals(v.getLabel(), labels.get(i));
            i++;
        }

        // Edges are correctly set
        edgeList = new ArrayList<>(graph.edgeSet());
        assertEquals(edgeList.size(),5);

        assertEquals(graph.getEdgeSource(edgeList.get(0)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(0)).getLabel(),"IN L");
        assertEquals(graph.getEdgeSource(edgeList.get(1)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(1)).getLabel(),"L $1");
        assertEquals(graph.getEdgeSource(edgeList.get(2)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(2)).getLabel(),"IN R");
        assertEquals(graph.getEdgeSource(edgeList.get(3)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(3)).getLabel(),"R Succ");
        assertEquals(graph.getEdgeSource(edgeList.get(4)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(4)).getLabel(),"Succ $2");

        // Checking Reactum 2

        graph = regression2.getReactum();
        assertEquals(graph.vertexSet().size(),6);

        labels = new ArrayList<>();
        labels.add("IN");
        labels.add("L");
        labels.add("Succ");
        labels.add("$1");
        labels.add("R");
        labels.add("$2");

        // Vertices are correctly set
        i = 0;
        for(Vertex v : graph.vertexSet()) {
            assertEquals(v.getLabel(), labels.get(i));
            i++;
        }

        // Edges are correctly set
        edgeList = new ArrayList<>(graph.edgeSet());
        assertEquals(edgeList.size(),5);

        assertEquals("IN L", graph.getEdgeSource(edgeList.get(0)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(0)).getLabel());
        assertEquals("L Succ", graph.getEdgeSource(edgeList.get(1)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(1)).getLabel());
        assertEquals("Succ $1", graph.getEdgeSource(edgeList.get(2)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(2)).getLabel());
        assertEquals("IN R", graph.getEdgeSource(edgeList.get(3)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(3)).getLabel());
        assertEquals("R $2", graph.getEdgeSource(edgeList.get(4)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(4)).getLabel());




    }


    @Test
    void checkLinks() {

        GraphBuildingVisitor graphBuildingVisitor = createVisitor(hospitalPath);
        Multigraph<Vertex, DefaultEdge> graph = GraphsCollection.getInstance().getModel();
        assertEquals(graph.vertexSet().size(),13);

        // I compare the label to verify mapping is correct
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Room");
        labels.add("neurology");
        labels.add("Doctor");
        labels.add("pager");
        labels.add("Agent");
        labels.add("Room");
        labels.add("corridor");
        labels.add("Agent");
        labels.add("Agent");
        labels.add("Room");
        labels.add("nursestation");
        labels.add("Agent");
        labels.add("Nurse");


        // Vertices are correctly set
        int i = 0;
        for(Vertex v : graph.vertexSet()) {
            assertEquals(v.getLabel(), labels.get(i));
            if(i == 1 || i == 3 || i == 6 || i == 10)
                assertFalse(v.isControl());
            else
                assertTrue(v.isControl());
            i++;
        }

        // Edges are correctly set
        ArrayList<DefaultEdge> edgeList = new ArrayList<>(graph.edgeSet());
        assertEquals(edgeList.size(),11);

        assertEquals("Room neurology", graph.getEdgeSource(edgeList.get(0)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(0)).getLabel());
        assertEquals("Room Doctor",graph.getEdgeSource(edgeList.get(1)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(1)).getLabel());
        assertEquals("Doctor pager", graph.getEdgeSource(edgeList.get(2)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(2)).getLabel());
        assertEquals("Room Agent", graph.getEdgeSource(edgeList.get(3)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(3)).getLabel());
        assertEquals("Room corridor", graph.getEdgeSource(edgeList.get(4)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(4)).getLabel());
        assertEquals("Room Agent", graph.getEdgeSource(edgeList.get(5)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(5)).getLabel());
        assertEquals("Room Agent", graph.getEdgeSource(edgeList.get(6)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(6)).getLabel());
        assertEquals("Room nursestation", graph.getEdgeSource(edgeList.get(7)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(7)).getLabel());
        assertEquals("Room Agent", graph.getEdgeSource(edgeList.get(8)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(8)).getLabel());
        assertEquals("Room Nurse", graph.getEdgeSource(edgeList.get(9)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(9)).getLabel());


        // Verifying model gets correctly print after execution of visitor
        GraphsCollection.getInstance().printModel();
    }

    // Testing if weights are correctly input
    @Test
    void checkNamesAndWeights() {

        GraphBuildingVisitor graphBuildingVisitor = createVisitor(namesWeightsTest);
        Multigraph<Vertex, DefaultEdge> graph = GraphsCollection.getInstance().getModel();
        assertEquals(graph.vertexSet().size(), 4);

        // I compare the label to verify mapping is correct
        ArrayList<String> labels = new ArrayList<>();
        labels.add("C");
        labels.add("name1");
        labels.add("name2");
        labels.add("name3");

        // Vertices are correctly set
        int i = 0;
        for (Vertex v : graph.vertexSet()) {
            assertEquals(v.getLabel(), labels.get(i));
            if (i == 0 )
                assertTrue(v.isControl());
            else
                assertFalse(v.isControl());
            i++;
        }

        // Edges are correctly set
        ArrayList<DefaultEdge> edgeList = new ArrayList<>(graph.edgeSet());
        assertEquals(edgeList.size(), 3);

        assertEquals("C name1", graph.getEdgeSource(edgeList.get(0)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(0)).getLabel());
        assertEquals("C name2", graph.getEdgeSource(edgeList.get(1)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(1)).getLabel());
        assertEquals("C name3", graph.getEdgeSource(edgeList.get(2)).getLabel() + " " + graph.getEdgeTarget(edgeList.get(2)).getLabel());


        // Verifying model gets correctly print after execution of visitor
        GraphsCollection.getInstance().printModel();
    }


    GraphBuildingVisitor createVisitor(String path) {
        try {
            File inputFile = new File(path);
            InputStream inputStream = new FileInputStream(inputFile);
            Lexer lexer = new bigraphLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            bigraphParser parser = new bigraphParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ErrorListener.INSTANCE);
            ParseTree tree = parser.bigraph();
            GraphBuildingVisitor visitor = new GraphBuildingVisitor();
            visitor.visit(tree);
            return visitor;
        } catch (IOException | ParseCancellationException e) {
            e.printStackTrace();
            return null;
        }
    }


}
