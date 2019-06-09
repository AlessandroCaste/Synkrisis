package core.graphBuilding;

import antlr.bigraph.BigraphParser;
import antlr.bigraph.BigraphVisitor;
import core.graphVisualization.CreateGraphvizModel;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.Stack;


public class GraphBuildingVisitor extends AbstractParseTreeVisitor<Void> implements BigraphVisitor<Void> {

    // This differentiates analysis for models' expressions
    private boolean modelVisited = false;

    // Model name to check file integrity
    private String modelName;


    @Override
    public Void visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    // Graph Representation
    private SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> currentGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    private SimpleDirectedWeightedGraph<Vertex,DefaultWeightedEdge> redex = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    private SimpleDirectedWeightedGraph<Vertex,DefaultWeightedEdge> reactum = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    private boolean nested = false;
    private boolean parallel = false;
    private Stack<Vertex> nodeStack = new Stack<>();               // Stacking of parent nodes, used for parentheses
    private Vertex currentVertex = new Vertex(0,"Root",true);
    private Vertex upperVertex = null;                                   // Direct parent node. -1 equals 'no parent'
    private int nodeCounter = 1;                                    // Represents the growing unique id of every node
    private int nameCounter = -1;                                   // Represents the decreasing unique id of every name
    private int depth = 0;                                          // Nesting depth
    private boolean root = true;
    private String reactionName = "";


    @Override
    public Void visitBigraph(BigraphParser.BigraphContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public Void visitControls(BigraphParser.ControlsContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public Void visitControl_statements(BigraphParser.Control_statementsContext ctx) {return visitChildren(ctx);}


    @Override
    public Void visitNames(BigraphParser.NamesContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitName_statements(BigraphParser.Name_statementsContext ctx) {return visitChildren(ctx);}


    @Override
    public Void visitReactions(BigraphParser.ReactionsContext ctx) {
        if(ctx.IDENTIFIER() != null)
            reactionName = ctx.IDENTIFIER().toString();
        return visitChildren(ctx);
    }


    @Override public Void visitReaction_statement (BigraphParser.Reaction_statementContext ctx){
        // I reset the latest graph
        currentGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        resetGraph();
        visit(ctx.getChild(0));
        redex = currentGraph;
        // Reset tree info for reactum tree
        currentGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        resetGraph();
        visit(ctx.getChild(2));
        reactum = currentGraph;
        createReactionGraph(redex,reactum,reactionName);
        return null;
    }

    // We track usages and also save info on the current control term to verify whether its arity matches links arity
    @SuppressWarnings("Duplicates")
    @Override public Void visitExpression (BigraphParser.ExpressionContext ctx) {

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

    @SuppressWarnings("Duplicates")
    @Override public Void visitRegions (BigraphParser.RegionsContext ctx){
        // GRAPH CREATION: every time there's a parallel region I reset the parent node pointer
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

    @Override public Void visitPrefix (BigraphParser.PrefixContext ctx){
        // GRAPH CREATION: current node becomes a parent node
        nested = true;
        parallel = false;
        upperVertex = currentVertex;
        return visitChildren(ctx);
    }

    @SuppressWarnings("Duplicates")
    @Override public Void visitLinks (BigraphParser.LinksContext ctx){


        // GRAPH CREATION: linking names to nodes
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


    @Override public Void visitModel (BigraphParser.ModelContext ctx){
        // I reset the latest graph
        currentGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        resetGraph();
        modelVisited = true;
        modelName = ctx.IDENTIFIER().getText();
        visitChildren(ctx);
        createModelGraph(currentGraph);
        // Saving model name for graphviz printing
        CreateGraphvizModel.getInstance().setModelName(modelName);
        return null;
    }


    @Override public Void visitProperty (BigraphParser.PropertyContext ctx){
        return null;
    }


    @Override public Void visitProperty_statement (BigraphParser.Property_statementContext ctx){
        return visitChildren(ctx);
    }


    @Override public Void visitBoolean_expression (BigraphParser.Boolean_expressionContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitBinary_operation (BigraphParser.Binary_operationContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitTerm (BigraphParser.TermContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitParameters_list (BigraphParser.Parameters_listContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitParameter (BigraphParser.ParameterContext ctx){
        return visitChildren(ctx);
    }

    private void createModelGraph(SimpleDirectedWeightedGraph<Vertex,DefaultWeightedEdge> model) {
        GraphsCollection.getInstance().addModel(model);
    }

    private void createReactionGraph(SimpleDirectedWeightedGraph<Vertex,DefaultWeightedEdge> redex, SimpleDirectedWeightedGraph<Vertex,DefaultWeightedEdge> reactum, String ruleName) {
        GraphReaction reaction = new GraphReaction(redex,reactum,ruleName);
        GraphsCollection.getInstance().addReaction(reaction);
    }

    private void resetGraph() {
        parallel = false;
        nested = false;
        nodeStack.clear();
        root = true;
        currentVertex = null;
        upperVertex = null;
        depth = 0;
    }

}