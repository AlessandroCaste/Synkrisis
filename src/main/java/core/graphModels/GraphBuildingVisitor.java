package core.graphModels;

import antlr.bigraph.bigraphParser;
import antlr.bigraph.bigraphVisitor;
import core.exporting.spotExporting.SpotAcceptanceState;
import core.exporting.spotExporting.SpotInfo;
import core.graphModels.verticesAndEdges.RedexReactumPair;
import core.graphModels.verticesAndEdges.Vertex;
import core.graphVisualization.CreateGraphvizImages;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Logger;


public class GraphBuildingVisitor extends AbstractParseTreeVisitor<Void> implements bigraphVisitor<Void> {

    // This differentiates analysis for models' expressions
    private boolean modelVisited = false;

    // Model name to check file integrity
    private String modelName;

    // Keeping track of markers with their ID. Counter starts from 2 since 0 = 'init' and 1 = 'deadlock'
    private HashMap<String,Integer> markerMap = new HashMap<>();
    private int markerCounter = 2;

    // Map to keep track of name nodes
    private HashMap<String, Vertex> nameMap = new HashMap<>();

    // Property string, for file printing
    private String propertiesString;

    // List of rule names
    private ArrayList<String> reactionNames = new ArrayList<>();

    // Reference to a class to store all SPOT info
    private SpotInfo spotInfo;
    private StringBuilder spotSpecifications;
    private boolean spotReady = true;
    private String duplicateSpotStates = "";


    // GraphsCollection for
    private GraphsCollection graphsCollection = GraphsCollection.getInstance();

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
            graphsCollection.addReactionWeight(reactionName,Float.parseFloat(ctx.PROBABILITY().getText()));
        else
            graphsCollection.addReactionWeight(reactionName,1f);
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
        graphsCollection.setModelName(ctx.IDENTIFIER().toString());
        graphsCollection.setReactionNames(reactionNames);

        // I store graph markers
        graphsCollection.addMarkerMap(markerMap);
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
            if (!markerMap.containsKey(identifier)) {
                markerMap.put(identifier,markerCounter);
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
        spotSpecifications = new StringBuilder();
        int startPosition = ctx.start.getStartIndex();
        int endPosition = ctx.stop.getStopIndex();
        Interval interval = new Interval(startPosition, endPosition);
        spotInfo = new SpotInfo(ctx.start.getInputStream().getText(interval));
        return visitChildren(ctx);
    }

    @Override public Void visitAcceptance(bigraphParser.AcceptanceContext ctx) {
        if(ctx.DIGIT() != null)
            spotInfo.setNumberAcceptanceSets(Integer.parseInt(ctx.DIGIT().toString()));
        return visitChildren(ctx);
    }


    // I translate acceptance state specification into simple digits
    // spotSpecification builds the acceptance string
    @Override public Void visitAcceptance_cond1(bigraphParser.Acceptance_cond1Context ctx) {
        for( ParseTree pt : ctx.children)
            if(pt == ctx.acceptance_cond2())
                visit(ctx.getChild(0));
            else if (pt == ctx.acceptance_cond1(0) || ctx.acceptance_cond1(1) != null && pt == ctx.acceptance_cond1(1))
                visit(pt);
            else
                spotSpecifications.append(pt.getText());
        return null;
    }


    // SPOT properties are read as strings, then are compared against marker map
    // This way I can get an int <-> property binding as with prism properties, and verify if an acceptance state
    // has already been added.
    @Override public Void visitAcceptance_cond2(bigraphParser.Acceptance_cond2Context ctx) {
        // I create a map for SPOT properties
        TreeSet<Integer> positiveMarkers = new TreeSet<>();
        TreeSet<Integer> negativeMarkers = new TreeSet<>();
        String acceptanceSpecification = "";
        int associatedID = 0;

        boolean isNegative = false;
        for(ParseTree pt : ctx.children) {
            Token token = (Token)pt.getPayload();
            if(token.getType() == bigraphParser.NOT)
                isNegative = true;
            if(isNegative && token.getType() == bigraphParser.IDENTIFIER) {
                negativeMarkers.add(markerMap.get(pt.getText()));
                isNegative = false;
            } else if (token.getType() == bigraphParser.IDENTIFIER)
                positiveMarkers.add(markerMap.get(pt.getText()));
        }

        // Verifying that the acceptance state has never been inserted into our model
        boolean found = false;
        for(SpotAcceptanceState sp : spotInfo.getAcceptanceStates())
            if(sp.verify(positiveMarkers,negativeMarkers)) {
                found = true;
                spotReady = false;
                duplicateSpotStates = duplicateSpotStates + sp.getAcceptanceStateString() + " ";
            }
        if(!found) {
            int startPosition = ctx.start.getStartIndex();
            int endPosition = ctx.stop.getStopIndex();
            Interval interval = new Interval(startPosition, endPosition);
            if(ctx.FIN()!=null)
                acceptanceSpecification = ctx.start.getInputStream().getText(interval)
                        .replaceAll("Fin","")
                        .replaceAll("\\)","")
                        .replaceAll("\\(","");
            else if(ctx.INF()!=null)
                acceptanceSpecification = ctx.start.getInputStream().getText(interval)
                        .replaceAll("Inf","")
                        .replaceAll("\\)","")
                        .replaceAll("\\(","");
            // I extract the property specification we're substituting in order to store it
            spotInfo.addAcceptanceState(new SpotAcceptanceState(acceptanceCounter, acceptanceSpecification, positiveMarkers, negativeMarkers));
            associatedID = acceptanceCounter;
            acceptanceCounter++;
        }
        if(ctx.FIN()!=null)
            spotSpecifications.append("Fin(").append(associatedID).append(")");
        if(ctx.INF()!=null)
            spotSpecifications.append("Inf(").append(associatedID).append(")");

        return visitChildren(ctx);
    }

    @Override public Void visitPrism_properties(bigraphParser.Prism_propertiesContext ctx) {
        if(spotSpecifications!=null)
            spotInfo.setAcceptanceStatesSpecification(spotSpecifications.toString());
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

    public boolean isSpotReady() {
        return spotReady;
    }

    public String getDuplicateSpotStates() {
        return duplicateSpotStates;
    }

    private void createModelGraph(Multigraph<Vertex,DefaultEdge> model) {
        graphsCollection.addModel(model);
    }

    private void createReactionGraph(Multigraph<Vertex,DefaultEdge> redex, Multigraph<Vertex,DefaultEdge> reactum, String ruleName) {
        RedexReactumPair reaction = new RedexReactumPair(redex,reactum,ruleName);
        graphsCollection.addReaction(reaction);
    }

    public String getPropertiesString() {
        return propertiesString;
    }

    public SpotInfo getAcceptanceInfo() {
        return spotInfo;
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