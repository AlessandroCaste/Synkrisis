package core.graphModels.exporting;

import core.graphModels.GraphsCollection;
import core.graphModels.verticesAndEdges.TransitionEdge;
import core.graphModels.verticesAndEdges.TransitionVertex;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.util.ArrayList;

class WeightNormalizer {

    private DirectedWeightedPseudograph<TransitionVertex, TransitionEdge> transitionGraph;

    WeightNormalizer(DirectedWeightedPseudograph<TransitionVertex,TransitionEdge> transitionGraph) {
        this.transitionGraph = transitionGraph;
        lessThanOne();
    }

    private void lessThanOne() {

        ArrayList<String> reactionsAlreadyChecked;

        for(TransitionVertex tv : transitionGraph.vertexSet()) {
            reactionsAlreadyChecked = new ArrayList<>();
            float weightSum = 0;
            for (TransitionEdge te : transitionGraph.outgoingEdgesOf(tv)) {
                if (!reactionsAlreadyChecked.contains(te.getLabel())) {
                    reactionsAlreadyChecked.add(te.getLabel());
                    weightSum += te.getWeight();
                }
            }
            if (weightSum < 1) {
                double weight = (1 - weightSum);
                weight = (double) Math.round(weight * 1e3) / 1e3;
                System.out.println(weight);
                transitionGraph.addEdge(tv, tv, new TransitionEdge("loop", weight));
            }
        }
        GraphsCollection.getInstance().printPrismTransition(transitionGraph);

    }

}
