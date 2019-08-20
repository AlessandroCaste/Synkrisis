package core.syntax;

import antlr.remscheld.remscheldParser;
import antlr.remscheld.remscheldVisitor;
import core.graphs.customized.vertices.TypedVertex;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class RemscheldVisitor<Void> extends AbstractParseTreeVisitor<Void> implements remscheldVisitor<Void> {

    // Graph Representation
    private DirectedWeightedPseudograph<TypedVertex, DefaultEdge> graph;
    private HashMap<String,Integer> markersMap;
    private String modelName;
    private ArrayList<String> reactionNames;
    private Stack<TypedVertex> nodeStack = new Stack<>();
    private int depth = 0;
    private int nodeCounter = 0;
    private TypedVertex currentNode;

    @Override public Void visitRemscheld(remscheldParser.RemscheldContext ctx) { return visitChildren(ctx); }


    @Override public Void visitNode(remscheldParser.NodeContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitIdentifiers(remscheldParser.IdentifiersContext ctx) {
        String vertexType = ctx.ID(1).toString();
        String label = ctx.ID(0).toString();
        currentNode = new TypedVertex(nodeCounter,vertexType,label,true);
        if(nodeStack.get(depth-1)!=null)
            graph.addEdge(nodeStack.get(depth-1),currentNode, new DefaultEdge());
        return visitChildren(ctx);
    }


    @Override public Void visitChildren(remscheldParser.ChildrenContext ctx) {
        depth++;
        nodeStack.push(currentNode);
        visitChildren(ctx);
        depth--;
        return null;
    }

    DirectedWeightedPseudograph<TypedVertex, DefaultEdge> getGraph(){
        return graph;
    }

}