package core.graphModels;

import antlr.bigraph.bigraphParser;
import antlr.bigraph.bigraphVisitor;
import core.graphModels.verticesAndEdges.RedexReactumPair;
import core.graphModels.verticesAndEdges.Vertex;
import core.graphVisualization.CreateGraphvizImages;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.*;
import java.util.logging.Logger;


public class GraphBuildingVisitor extends AbstractParseTreeVisitor<Void> implements bigraphVisitor<Void> {

    // This differentiates analysis for models' expressions
    private boolean modelVisited = false;

    // Model name to check file integrity
    private String modelName;

    // Keeping track of markers with their ID
    private BidiMap<Integer,String> markerMap = new TreeBidiMap<>();
    private int markerCounter = 0;

    // Map to keep track of name nodes
    private HashMap<String, Vertex> nameMap = new HashMap<>();

    // Property string, for file printing
    private String propertiesString;

    // List of rule names
    private ArrayList<String> reactionNames = new ArrayList<>();

    // List of SPOT acceptance states
    // Each state will be associated to an integer
    private ArrayList<SpotAcceptanceState> acceptanceStates = new ArrayList<>();
    private int acceptanceCounter = 0;

    private static Logger logger = Logger.getLogger("Report");


    @Override
    public Void visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    // Graph Representation
    private Multigraph<Vertex, DefaultEdge> currentGraph = new Multigraph<>(DefaultEdge.class);
    private Multigraph<Vertex, DefaultEdge>  redex = new Multigraph<>(DefaultEdge.class);
    private Multigraph<Vertex, DefaultEdge>  reactum = new Multigraph<>(DefaultEdge.class);
    private boolean nested = false;
    private boolean parallel = false;
    private Stack<Vertex> nodeStack = new Stack<>();               // Stacking of parent nodes, used for parentheses
    private Vertex currentVertex = new Vertex(0,"Root",true);
    private Vertex upperVertex = null;                                   // Direct parent node. -1 equals 'no parent'
    private int nodeCounter = 1;                                    // Represents the growing unique id of every node
    private int depth = 0;                                          // Nesting depth
    private boolean root = true;
    private String reactionName = "";


    @Override
    public Void visitBigraph(bigraphParser.BigraphContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public Void visitControls(bigraphParser.ControlsContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public Void visitControl_statements(bigraphParser.Control_statementsContext ctx) {return visitChildren(ctx);}


    @Override
    public Void visitNames(bigraphParser.NamesContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitName_statements(bigraphParser.Name_statementsContext ctx) {return visitChildren(ctx);}


    @Override
    public Void visitReactions(bigraphParser.ReactionsContext ctx) {
        if(ctx.IDENTIFIER() != null) {
            reactionName = ctx.IDENTIFIER().toString();
            reactionNames.add(reactionName);
        }
        return visitChildren(ctx);
    }


    @Override public Void visitReaction_statement (bigraphParser.Reaction_statementContext ctx){
        // Storing probabilities
        if(ctx.PROBABILITY() != null)
            GraphsCollection.getInstance().addReactionWeight(reactionName,Float.parseFloat(ctx.PROBABILITY().getText()));
        else
            GraphsCollection.getInstance().addReactionWeight(reactionName,1f);
        // I reset the latest graph
        currentGraph = new Multigraph<>(DefaultEdge.class);
        resetGraph();
        visit(ctx.getChild(0));
        redex = currentGraph;
        // Reset tree info for reactum tree
        currentGraph = new Multigraph<>(DefaultEdge.class);
        resetGraph();
        if(ctx.PROBABILITY() != null)
            visit(ctx.getChild(5));
        else
            visit(ctx.getChild(2));
        reactum = currentGraph;
        createReactionGraph(redex,reactum,reactionName);
        return null;
    }

    // We track usages and also save info on the current control term to verify whether its arity matches links arity
    @SuppressWarnings("Duplicates")
    @Override public Void visitExpression (bigraphParser.ExpressionContext ctx) {

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
            if(root) {
                root = false;
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                currentVertex = vertex;
                nodeCounter++;
            }
            else if(parallel) {
                Vertex vertex = new Vertex(nodeCounter,identifier,true);
                currentGraph.addVertex(vertex);
                if(upperVertex != null)
                    currentGraph.addEdge(upperVertex,vertex);
                currentVertex = vertex;
                nodeCounter++;
            }
            else if(nested) {
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
    @Override public Void visitRegions (bigraphParser.RegionsContext ctx){
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

    @Override public Void visitPrefix (bigraphParser.PrefixContext ctx){
        // GRAPH CREATION: current node becomes a parent node
        nested = true;
        parallel = false;
        upperVertex = currentVertex;
        return visitChildren(ctx);
    }

    @SuppressWarnings("Duplicates")
    @Override public Void visitLinks (bigraphParser.LinksContext ctx){


        // GRAPH CREATION: linking names to nodes
        if(ctx.IDENTIFIER() != null ) {
            String nameLabel;
            if(ctx.VARIABLE() != null) {
                nameLabel = "@" + ctx.IDENTIFIER().toString();
            } else
                nameLabel = ctx.IDENTIFIER().toString();
            Vertex vertex;
            if(!nameMap.containsKey(nameLabel)) {
                vertex = new Vertex(nodeCounter, nameLabel, false);
                nameMap.put(nameLabel,vertex);
            }
            else
                vertex = nameMap.get(nameLabel);
            currentGraph.addVertex(vertex);
            currentGraph.addEdge(currentVertex, vertex);
            nameMap.put(nameLabel,vertex);
            nodeCounter++;

        }
        else if(ctx.VARIABLE() != null ) {
            Vertex vertex = new Vertex(nodeCounter,ctx.VARIABLE().toString()+ctx.IDENTIFIER(),false);
            currentGraph.addVertex(vertex);
            currentGraph.addEdge(currentVertex,vertex);
            nodeCounter++;
        }
        else if(ctx.UNLINKED() != null ) {
            Vertex vertex = new Vertex(nodeCounter,ctx.UNLINKED().toString(),false);
            currentGraph.addVertex(vertex);
            currentGraph.addEdge(currentVertex,vertex);
            nodeCounter++;
        }
        return visitChildren(ctx);
    }


    @Override public Void visitModel (bigraphParser.ModelContext ctx){

        // I store model name and reactions
        GraphsCollection.getInstance().setModelName(ctx.IDENTIFIER().toString());
        GraphsCollection.getInstance().setReactionNames(reactionNames);

        // I store graph markers
        GraphsCollection.getInstance().addMarkerMap(markerMap);
        // I reset the latest graph
        currentGraph = new Multigraph<>(DefaultEdge.class);
        resetGraph();
        modelVisited = true;
        modelName = ctx.IDENTIFIER().getText();
        visitChildren(ctx);
        createModelGraph(currentGraph);
        // Saving model name for graphviz printing
        CreateGraphvizImages.getInstance().setModelName(modelName);
        return null;
    }


    @Override public Void visitMarker (bigraphParser.MarkerContext ctx){
        if(ctx.MARKER() != null) {
            String identifier = ctx.IDENTIFIER().getText();
            if (!markerMap.containsValue(identifier)) {
                markerMap.put(markerCounter,identifier);
                markerCounter++;
            }
        }
        return visitChildren(ctx);
    }


    @Override public Void visitMarker_statement (bigraphParser.Marker_statementContext ctx){
        return null;
    }

    @Override public Void visitProperties (bigraphParser.PropertiesContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitSpot_statement(bigraphParser.Spot_statementContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitAcc_name(bigraphParser.Acc_nameContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitAcceptance(bigraphParser.AcceptanceContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitAcceptance_cond1(bigraphParser.Acceptance_cond1Context ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitAcceptance_cond2(bigraphParser.Acceptance_cond2Context ctx) {
        // I create a map for SPOT properties
        if(ctx.IDENTIFIER() != null) {
            String acceptanceIdentifier = ctx.IDENTIFIER().toString();
            TreeSet<String> acceptanceSet = new TreeSet<>(Arrays.asList(acceptanceIdentifier.replace("[","")
                                                                .replace("]","")
                                                                .split("\\s*,\\s*")));
            // Identical acceptance states are merged
            boolean found = false;
            for(SpotAcceptanceState s : acceptanceStates) {
                if(s.compare(acceptanceSet))
                    found = true;
            }
            if(!found) {
                acceptanceStates.add(new SpotAcceptanceState(acceptanceCounter, acceptanceSet));
                acceptanceCounter++;
            }
        }
        return visitChildren(ctx);
    }

    @Override public Void visitPrism_properties(bigraphParser.Prism_propertiesContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitPrism_statements (bigraphParser.Prism_statementsContext ctx) {
        // Since they do vary a lot and have not a fixed, specified grammar we just print properties to file
        if(ctx.children.size() > 1) {
            int startPosition = ctx.start.getStartIndex();
            int endPosition = ctx.stop.getStopIndex();
            Interval interval = new Interval(startPosition, endPosition);
            propertiesString = ctx.start.getInputStream().getText(interval);
        }
        return visitChildren(ctx);
    }

    @Override public Void visitBoolean_expression (bigraphParser.Boolean_expressionContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitBinary_operation (bigraphParser.Binary_operationContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitTerm (bigraphParser.TermContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitParameters_list (bigraphParser.Parameters_listContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitParameter (bigraphParser.ParameterContext ctx){
        return visitChildren(ctx);
    }

    private void createModelGraph(Multigraph<Vertex,DefaultEdge> model) {
        GraphsCollection.getInstance().addModel(model);
    }

    private void createReactionGraph(Multigraph<Vertex,DefaultEdge> redex, Multigraph<Vertex,DefaultEdge> reactum, String ruleName) {
        RedexReactumPair reaction = new RedexReactumPair(redex,reactum,ruleName);
        GraphsCollection.getInstance().addReaction(reaction);
    }

    public String getPropertiesString() {
        return propertiesString;
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