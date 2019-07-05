package core.exporting;

import core.graphBuilding.Vertex;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;

public class PrismExporter {

    private static PrismExporter instance;

    private PrismExporter() {}

    public static PrismExporter getInstance() {
        if(instance == null)
            return(instance = new PrismExporter());
        return instance;
    }

    public void receiveLabeledState(Multigraph<Vertex, DefaultEdge> graph, ArrayList<String> propertyLabel) {

    }



}
