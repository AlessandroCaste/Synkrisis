package core;

import antlr.BigraphParser;
import antlr.BigraphVisitor;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.HashMap;
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
    private Multigraph<Integer, DefaultEdge> currentGraph = new Multigraph<>(DefaultEdge.class);
    private Multigraph<Integer,DefaultEdge> redex = new Multigraph<>(DefaultEdge.class);
    private Multigraph<Integer,DefaultEdge> reactum = new Multigraph<>(DefaultEdge.class);
    private boolean nested = false;
    private boolean parallel = false;
    private Stack<Integer> nodeStack = new Stack<>();               // Stacking of parent nodes, used for parentheses
    private int currentVertex = 1;
    private int upperVertex = -1;                                   // Direct parent node. -1 equals 'no parent'
    private boolean enable = false;
    private int nodeCounter = 1;                                    // Represents the growing unique id of every node
    private HashMap<Integer,String> nodeMapping = new HashMap<>();  // Required for storing labels (string) for nodes
    private int nameCounter = -1;                                   // Represents the decreasing unique id of every name
    private HashMap<Integer,String> namesMapping = new HashMap<>(); // Required for storing labels(strings) for names
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
        enable = true;
        if(ctx.IDENTIFIER() != null)
            reactionName = ctx.IDENTIFIER().toString();
        return visitChildren(ctx);
    }


    @Override public Void visitReaction_statement (BigraphParser.Reaction_statementContext ctx){
        // I reset the latest graph
        currentGraph = new Multigraph<>(DefaultEdge.class);
        resetGraph();
        enable = true;
        visit(ctx.getChild(0));
        redex = currentGraph;
        // Reset tree info for reactum tree
        currentGraph = new Multigraph<>(DefaultEdge.class);
        resetGraph();
        visit(ctx.getChild(2));
        reactum = currentGraph;
        createReactionGraph(redex,reactum,reactionName);
        return null;
    }

    // We track usages and also save info on the current control term to verify whether its arity matches links arity
    @Override public Void visitExpression (BigraphParser.ExpressionContext ctx) {

        // GRAPH CREATION: calculating depths and nesting of parents
        if(ctx.LPAR()!=null && enable) {
            depth++;
            nodeStack.push(currentVertex);
        }

        if(ctx.DOLLAR() != null) {
            String identifier = ctx.DOLLAR().toString() + ctx.DIGIT().toString();
            // GRAPH CREATION: taking into account parallel/nesting in expressions
            if(root && enable) {
                root = false;
                nodeMapping.put(nodeCounter,identifier);
                currentGraph.addVertex(nodeCounter);
                nodeCounter++;
            }
            else if(parallel && enable) {
                nodeMapping.put(nodeCounter,identifier);
                currentGraph.addVertex(nodeCounter);
                if(upperVertex != (-1))
                    currentGraph.addEdge(upperVertex,nodeCounter);
                currentVertex = nodeCounter;
                nodeCounter++;
            }
            else if(nested && enable) {
                nodeMapping.put(nodeCounter,identifier);
                currentGraph.addVertex(nodeCounter);
                if(currentVertex != nodeCounter)
                    currentGraph.addEdge(currentVertex,nodeCounter);
                currentVertex = nodeCounter;
                nodeCounter++;
                nested = false;
            }
        }

        if (ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();

            // GRAPH CREATION: taking into account parallel/nesting in expressions
            if(root && enable) {
                root = false;
                nodeMapping.put(nodeCounter,identifier);
                currentGraph.addVertex(nodeCounter);
                nodeCounter++;
            }
            else if(parallel && enable) {
                nodeMapping.put(nodeCounter,identifier);
                currentGraph.addVertex(nodeCounter);
                if(upperVertex != (-1))
                    currentGraph.addEdge(upperVertex,nodeCounter);
                currentVertex = nodeCounter;
                nodeCounter++;
            }
            else if(nested && enable) {
                nodeMapping.put(nodeCounter,identifier);
                currentGraph.addVertex(nodeCounter);
                if(currentVertex != nodeCounter)
                    currentGraph.addEdge(currentVertex,nodeCounter);
                currentVertex = nodeCounter;
                nodeCounter++;
                nested = false;
            }

        }

        // GRAPH CREATION: managing depth when leaving nested expressions
        if(ctx.RPAR()!=null && enable) {
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

    @Override public Void visitRegions (BigraphParser.RegionsContext ctx){
        // GRAPH CREATION: every time there's a parallel region I reset the parent node pointer
        if(ctx.PAR() != null && enable) {
            parallel = true;
            if(!nodeStack.isEmpty())
                upperVertex = nodeStack.get(depth-1);
            else
                upperVertex = -1;
        }
        if(ctx.LOR() != null && enable) {
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

    @Override public Void visitLinks (BigraphParser.LinksContext ctx){

        // GRAPH CREATION: linking names to nodes
        if(ctx.IDENTIFIER() != null && enable) {
            currentGraph.addVertex(nameCounter);
            namesMapping.put(nameCounter,ctx.IDENTIFIER().toString());
            currentGraph.addEdge(currentVertex,nameCounter);
            nameCounter--;
        }
        if(ctx.VARIABLE() != null && enable) {
            currentGraph.addVertex(nameCounter);
            namesMapping.put(nameCounter,ctx.VARIABLE().toString()+ctx.IDENTIFIER());
            currentGraph.addEdge(currentVertex,nameCounter);
            nameCounter--;
        }
        if(ctx.UNLINKED() != null && enable) {
            currentGraph.addVertex(nameCounter);
            namesMapping.put(nameCounter,ctx.UNLINKED().toString());
            currentGraph.addEdge(currentVertex,nameCounter);
            nameCounter--;
        }
        return visitChildren(ctx);
    }


    @Override public Void visitModel (BigraphParser.ModelContext ctx){
        // I reset the latest graph
        currentGraph = new Multigraph<>(DefaultEdge.class);
        resetGraph();
        enable = true;
        modelVisited = true;
        modelName = ctx.IDENTIFIER().getText();
        visitChildren(ctx);
        createModelGraph(currentGraph);
        enable = false;
        return null;
    }


    @Override public Void visitProperty (BigraphParser.PropertyContext ctx){
        return visitChildren(ctx);
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

    private void createModelGraph(Multigraph<Integer,DefaultEdge> model) {
        CreateGraphvizModel.getInstance().createModel(model,nodeMapping,namesMapping);
    }

    private void resetGraph() {
        parallel = false;
        nested = false;
        nodeStack.clear();
        root = true;
        currentVertex = nodeCounter;
        upperVertex = -1;
        depth = 0;
    }

    private void createReactionGraph(Multigraph<Integer,DefaultEdge> redex, Multigraph<Integer,DefaultEdge> reactum, String ruleName) {
        CreateGraphvizModel.getInstance().createReactions(redex,reactum,nodeMapping,namesMapping,ruleName);
    }

    void storeFileName(String fileName) {
        CreateGraphvizModel.getInstance().setFileName(fileName);
    }
}