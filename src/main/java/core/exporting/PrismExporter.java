package core.exporting;

import core.graphBuilding.Vertex;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.ArrayList;

public class PrismExporter {

    private static PrismExporter instance;

    private PrismExporter() {}

    public static PrismExporter getInstance() {
        if(instance == null)
            return(instance = new PrismExporter());
        return instance;
    }

    public void receiveLabeledState(SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> graph, ArrayList<String> propertyLabel) {

    }



}
