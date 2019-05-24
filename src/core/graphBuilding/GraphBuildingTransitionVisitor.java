// Generated from /home/ale/Synkrisis/src/core/g4model/Transition.g4 by ANTLR 4.7.2
package core.graphBuilding;
import antlr.transition.TransitionParser;
import antlr.transition.TransitionVisitor;
import core.graphVisualization.CreateGraphvizModel;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

@SuppressWarnings("Duplicates")
public class GraphBuildingTransitionVisitor<Void> extends AbstractParseTreeVisitor<Void> implements TransitionVisitor<Void> {

    // Graph Representation
    private Multigraph<Vertex, DefaultEdge> currentGraph = new Multigraph<>(DefaultEdge.class);
    int stateNumber = 1;
    private boolean nested = false;
    private boolean parallel = false;
    private Stack<Vertex> nodeStack = new Stack<>();               // Stacking of parent nodes, used for parentheses
    private Vertex currentVertex = new Vertex(0,"Root",true);
    private Vertex upperVertex = null;                              // Direct parent node. -1 equals 'no parent'
    private int nodeCounter = 1;                                    // Represents the growing unique id of every node
    private int nameCounter = -1;                                   // Represents the decreasing unique id of every name
    private int depth = 0;                                          // Nesting depth
    private boolean root = true;
    private ArrayList<String> propertiesList = new ArrayList<>();

    @Override public Void visitTransition(TransitionParser.TransitionContext ctx) { return visitChildren(ctx); }

    @Override public Void visitExpression(TransitionParser.ExpressionContext ctx) {
        // GRAPH CREATION: calculating depths and nesting of parents
        if(ctx.LPAR()!=null ) {
            depth++;
            nodeStack.push(currentVertex);
        }

        if(ctx.DOLLAR() != null) {
            String identifier = ctx.DOLLAR().toString() + ctx.DIGIT().toString();
            // GRAPH CREATION: taking into account parallel/nesting in expressions, for $-preceded elements
            if(root ) {
                root = false;
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                nodeCounter++;
            }
            else if(parallel ) {
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                if(upperVertex != null)
                    currentGraph.addEdge(upperVertex,vertex);
                currentVertex = vertex;
                nodeCounter++;
            }
            else if(nested ) {
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                if(currentVertex.getVertexId() != nodeCounter)
                    currentGraph.addEdge(currentVertex,vertex);
                currentVertex = vertex;
                nodeCounter++;
                nested = false;
            }
        }

        if (ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();

            // GRAPH CREATION: taking into account parallel/nesting in expressions
            if(root ) {
                root = false;
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                currentVertex = vertex;
                nodeCounter++;
            }
            else if(parallel ) {
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                if(upperVertex != null)
                    currentGraph.addEdge(upperVertex,vertex);
                currentVertex = vertex;
                nodeCounter++;
            }
            else if(nested ) {
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                if(currentVertex != vertex)
                    currentGraph.addEdge(currentVertex,vertex);
                currentVertex = vertex;
                nodeCounter++;
                nested = false;
            }

        }

        // GRAPH CREATION: managing depth when leaving nested expressions
        if(ctx.RPAR()!=null ) {
            visit(ctx.expression());
            depth--;
            nodeStack.pop();
            // Note that return Strings have no purpose at the moment, but for future uses we make sure no return is wasted
            if(ctx.regions() != null)
                visit(ctx.regions());
            return null;
        }

        return visitChildren(ctx);
    }


    @Override public Void visitRegions(TransitionParser.RegionsContext ctx) {         // GRAPH CREATION: every time there's a parallel region I reset the parent node pointer
        if(ctx.PAR() != null ) {
            parallel = true;
            if(!nodeStack.isEmpty())
                upperVertex = nodeStack.get(depth-1);
            else
                upperVertex = null;
        }
        if(ctx.LOR() != null ) {
            resetGraph();
        }
        return visitChildren(ctx);
    }

    @Override public Void visitPrefix(TransitionParser.PrefixContext ctx) {         // GRAPH CREATION: current node becomes a parent node
        nested = true;
        parallel = false;
        upperVertex = currentVertex;
        return visitChildren(ctx);
    }

    @Override public Void visitLinks(TransitionParser.LinksContext ctx) { // GRAPH CREATION: linking names to nodes
        if(ctx.IDENTIFIER() != null ) {
            Vertex vertex = new Vertex(nameCounter,ctx.IDENTIFIER().toString(),false);
            currentGraph.addVertex(vertex);
            currentGraph.addEdge(currentVertex,vertex);
            nameCounter--;
        }
        else if(ctx.VARIABLE() != null ) {
            Vertex vertex = new Vertex(nameCounter,ctx.VARIABLE().toString()+ctx.IDENTIFIER(),false);
            currentGraph.addVertex(vertex);
            currentGraph.addEdge(currentVertex,vertex);
            nameCounter--;
        }
        else if(ctx.UNLINKED() != null ) {
            Vertex vertex = new Vertex(nameCounter,ctx.UNLINKED().toString(),false);
            currentGraph.addVertex(vertex);
            currentGraph.addEdge(currentVertex,vertex);
            nameCounter--;
        }
        return visitChildren(ctx);
    }

    @Override public Void visitState(TransitionParser.StateContext ctx) {
        // I reset the latest graph
        currentGraph = new Multigraph<>(DefaultEdge.class);
        resetGraph();
        stateNumber = Integer.parseInt(ctx.DIGIT().getText());
        // TODO
        visitChildren(ctx);
        CreateGraphvizModel.getInstance().setModelName(Integer.toString(stateNumber));
        stateNumber++;
        CreateGraphvizModel.getInstance().createModel(currentGraph);
        return null;
    }

    @Override public Void visitProperties(TransitionParser.PropertiesContext ctx) {
        int numberOfProperties = ctx.IDENTIFIER().size();
        for(int i = 0; i < numberOfProperties; i++)
            propertiesList.add(ctx.IDENTIFIER(i).getText());
        return visitChildren(ctx); }

    private void resetGraph() {
        parallel = false;
        nested = false;
        nodeStack.clear();
        root = true;
        currentVertex = null;
        upperVertex = null;
        depth = 0;
        propertiesList = new ArrayList<>();
    }


}