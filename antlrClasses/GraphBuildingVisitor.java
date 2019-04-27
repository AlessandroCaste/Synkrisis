import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


// Return Strings are completely ignored here
public class GraphBuildingVisitor extends AbstractParseTreeVisitor<String> implements BigraphVisitor<String> {

    // This differentiates analysis for models' expressions
    private boolean modelVisited = false;

    // Model name to check file integrity
    private String modelName = "null";


    @Override
    public String visitChildren(RuleNode node) {
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
    boolean root = true;
    String reactionName = "";


    @Override
    public String visitBigraph(BigraphParser.BigraphContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public String visitControls(BigraphParser.ControlsContext ctx) {
        return visitChildren(ctx);
    }


    // Controls are added to the respective list
    // If the control name is already present an error is signaled
    @Override
    public String visitControl_statements(BigraphParser.Control_statementsContext ctx) {

        return visitChildren(ctx);

    }


    @Override
    public String visitNames(BigraphParser.NamesContext ctx) {
        return visitChildren(ctx);
    }

    // Names are added to the respective list
    // If names are already present in the model an error is signaled
    @Override
    public String visitName_statements(BigraphParser.Name_statementsContext ctx) {
        return visitChildren(ctx);

    }


    @Override
    public String visitReactions(BigraphParser.ReactionsContext ctx) {
        enable = true;
        if(ctx.IDENTIFIER() != null)
            reactionName = ctx.IDENTIFIER().toString();
        return visitChildren(ctx);
    }


    @Override public String visitReaction_statement (BigraphParser.Reaction_statementContext ctx){
        enable = true;
        visit(ctx.getChild(0));
        redex = currentGraph;
        // Reset tree info for reactum tree
        currentGraph = new Multigraph<>(DefaultEdge.class);
        parallel = false;
        nested = false;
        nodeStack.clear();
        root = true;
        currentVertex = nodeCounter;
        upperVertex = -1;
        depth = 0;
        visit(ctx.getChild(2));
        reactum = currentGraph;
        createGraph(redex,reactum,reactionName);
        return "";
    }

    // We track usages and also save info on the current control term to verify whether its arity matches links arity
    @Override public String visitExpression (BigraphParser.ExpressionContext ctx) {

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
            String s = visit(ctx.expression());
            depth--;
            nodeStack.pop();
            // Note that return Strings have no purpose at the moment, but for future uses we make sure no return is wasted
            if(ctx.regions() != null)
                return s + visit(ctx.regions());
            return s;
        }

        return visitChildren(ctx);
    }

    @Override public String visitRegions (BigraphParser.RegionsContext ctx){
        // GRAPH CREATION: every time there's a parallel region I reset the parent node pointer
        if(ctx.PAR() != null && enable) {
            parallel = true;
            if(!nodeStack.isEmpty())
                upperVertex = nodeStack.get(depth-1);
            else
                upperVertex = -1;
        }
        if(ctx.LOR() != null && enable) {
            parallel = false;
            nested = false;
            nodeStack.clear();
            root = true;
            currentVertex = nodeCounter;
            upperVertex = -1;
            depth = 0;
        }
        return visitChildren(ctx);
    }


    @Override public String visitPrefix (BigraphParser.PrefixContext ctx){
        // GRAPH CREATION: current node becomes a parent node
        nested = true;
        parallel = false;
        upperVertex = currentVertex;
        return visitChildren(ctx);
    }

    // Links are checked for names/controls usages
    // Links also count the identifiers they contain in order to verify arity
    @Override public String visitLinks (BigraphParser.LinksContext ctx){

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


    @Override public String visitModel (BigraphParser.ModelContext ctx){
        enable = false;
        modelVisited = true;
        modelName = ctx.IDENTIFIER().getText();
        return visitChildren(ctx);
    }


    // This visitor serves the purpose of checking the uniqueness of property names
    @Override public String visitProperty (BigraphParser.PropertyContext ctx){
        return visitChildren(ctx);
    }


    @Override public String visitProperty_statement (BigraphParser.Property_statementContext ctx){
        return visitChildren(ctx);
    }


    @Override public String visitBoolean_expression (BigraphParser.Boolean_expressionContext ctx){
        return visitChildren(ctx);
    }

    @Override public String visitBinary_operation (BigraphParser.Binary_operationContext ctx){
        return visitChildren(ctx);
    }

    @Override public String visitTerm (BigraphParser.TermContext ctx){
        return visitChildren(ctx);
    }

    @Override public String visitParameters_list (BigraphParser.Parameters_listContext ctx){
        return visitChildren(ctx);
    }

    @Override public String visitParameter (BigraphParser.ParameterContext ctx){
        return visitChildren(ctx);
    }

    void createGraph(Multigraph<Integer,DefaultEdge> redex,Multigraph<Integer,DefaultEdge> reactum, String ruleName) {
        CreateGraphvizModel.getInstance().createReactions(redex,reactum,nodeMapping,namesMapping,ruleName);
    }

}