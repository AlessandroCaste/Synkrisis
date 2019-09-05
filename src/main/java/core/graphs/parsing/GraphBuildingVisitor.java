package core.graphs.parsing;

import antlr.bigraph.bigraphParser;
import antlr.bigraph.bigraphVisitor;
import core.exporting.Exporter;
import core.exporting.spotExporting.SpotAcceptanceState;
import core.exporting.spotExporting.SpotInfo;
import core.graphs.specificationgraph.Vertex;
import core.graphs.storing.GraphsCollection;
import core.graphs.storing.RedexReactumPair;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.util.*;
import java.util.logging.Logger;


public class GraphBuildingVisitor extends AbstractParseTreeVisitor<Void> implements bigraphVisitor<Void> {

    // This differentiates analysis for models' expressions
    private boolean modelVisited = false;

    // Model name to check file integrity
    private String modelName;

    // Keeping track of markers with their ID. Counter starts from 2 since 0 = 'init' and 1 = 'deadlock'
    private HashMap<String,Integer> markersMap = new HashMap<>();
    private int markerCounter = 0;

    // Map to keep track of name nodes
    private HashMap<String, Vertex> nameMap = new HashMap<>();

    // Property string, for file printing DELETE
    private String propertiesString;

    // List of rule names DELETABLE
    private ArrayList<String> reactionNames = new ArrayList<>();

    // Spot Exporting
    private SpotInfo spotInfo;
    private StringBuilder spotSpecifications;
    private boolean spotReady = true;
    private StringBuilder spotErrorsString = new StringBuilder();
    private int declaredAcceptanceStates = 0; // Number of states declared
    private int acceptanceCounter = 0; // Unique states found

    // Storing external properties for all other model checkers
    private Exporter exporter = Exporter.getInstance();
    private String currentPropertyLanguage;
    private String currentFormat;

    // GraphsCollection
    private GraphsCollection graphsCollection = GraphsCollection.getInstance();


    private static Logger logger = Logger.getLogger("Report");


    // Graph Representation
    private DirectedMultigraph<Vertex, DefaultEdge> currentGraph = new DirectedMultigraph<>(DefaultEdge.class);
    private DirectedMultigraph<Vertex, DefaultEdge>  redex = new DirectedMultigraph<>(DefaultEdge.class);
    private DirectedMultigraph<Vertex, DefaultEdge>  reactum = new DirectedMultigraph<>(DefaultEdge.class);
    private boolean nested = false;
    private boolean parallel = false;
    private Stack<Vertex> nodeStack = new Stack<>();              // Stacking of parent nodes, used for parentheses
    private Vertex currentVertex = new Vertex(0,"Root",true);
    private Vertex upperVertex = null;                            // Direct parent node. -1 equals 'no parent'
    private HashSet<String> activeControls;                       // Saving the active controls
    private HashSet<String> outerNames;                           // Saving the outer names
    private int nodeCounter = 1;                                  // Represents the growing unique id of every node
    private int depth = 0;                                        // Nesting depth
    private boolean root = true;
    private String reactionName = "";


    @Override
    public Void visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }



    @Override
    public Void visitBigraph(bigraphParser.BigraphContext ctx) {
        System.out.println("\nBUILDING REPRESENTATION");
        System.out.println("***********************");
        activeControls = new HashSet<>();
        outerNames = new HashSet<>();
        return visitChildren(ctx);
    }


    @Override
    public Void visitControls(bigraphParser.ControlsContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public Void visitControl_statements(bigraphParser.Control_statementsContext ctx) {
        if(ctx.ACTIVE()!= null)
            activeControls.add(ctx.IDENTIFIER().toString());
        return visitChildren(ctx);
    }


    @Override
    public Void visitNames(bigraphParser.NamesContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitName_statements(bigraphParser.Name_statementsContext ctx) {
        if(ctx.OUTER() != null)
            outerNames.add(ctx.IDENTIFIER().toString());
        return visitChildren(ctx);
    }


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
        currentGraph = new DirectedMultigraph<>(DefaultEdge.class);
        resetGraph();
        visit(ctx.getChild(0));
        redex = currentGraph;
        // Reset tree info for reactum tree
        currentGraph = new DirectedMultigraph<>(DefaultEdge.class);
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
            if(activeControls.contains(identifier))
                currentVertex.setActiveControl();

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
            boolean isOuter = outerNames.contains(ctx.IDENTIFIER().toString());
            if(ctx.VARIABLE() != null) {
                nameLabel = "@" + ctx.IDENTIFIER().toString();
            } else
                nameLabel = ctx.IDENTIFIER().toString();
            Vertex vertex;
            if(!nameMap.containsKey(nameLabel)) {
                vertex = new Vertex(nodeCounter, nameLabel, false);
                if(isOuter)
                    vertex.setOuterName();
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

        // I reset the latest graph
        currentGraph = new DirectedMultigraph<>(DefaultEdge.class);
        resetGraph();
        modelVisited = true;
        modelName = ctx.IDENTIFIER().getText();
        visitChildren(ctx);
        createModelGraph(currentGraph);
        return null;
    }


    @Override public Void visitMarker (bigraphParser.MarkerContext ctx){
        if(ctx.MARKER() != null) {
            String identifier = ctx.IDENTIFIER().getText();
            if (!markersMap.containsKey(identifier)) {
                markersMap.put(identifier,markerCounter);
                markerCounter++;
            }
        }
        return visitChildren(ctx);
    }


    @Override public Void visitMarker_statement (bigraphParser.Marker_statementContext ctx){
        return null;
    }

    @Override public Void visitProperties (bigraphParser.PropertiesContext ctx) {
        graphsCollection.setMarkersMap(markersMap);
        return visitChildren(ctx);
    }

    @Override public Void visitSpot_statement(bigraphParser.Spot_statementContext ctx) {
        // No SPOT specification
        if(ctx.SPOT()==null)
            spotReady = false;
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
        if(spotSpecifications == null)
            spotSpecifications = new StringBuilder();
        if(spotInfo == null)
            spotInfo = new SpotInfo("acc-name: undefined-acceptance");
        if(ctx.DIGIT() != null) {
            declaredAcceptanceStates = Integer.parseInt(ctx.DIGIT().toString());
            spotInfo.setNumberAcceptanceSets(declaredAcceptanceStates);
        }
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
    // spotErrorsString records error messages
    @Override public Void visitAcceptance_cond2(bigraphParser.Acceptance_cond2Context ctx) {
        // I create a map for SPOT properties
        TreeSet<Integer> positiveMarkers = new TreeSet<>();
        TreeSet<Integer> negativeMarkers = new TreeSet<>();
        String acceptanceSpecification = "";
        int associatedID = 0;
        boolean degenerateSpot = false;

        // Generating the acceptance state by assigning to it its properties ID and verifying they don't directly negate each other
        boolean isNegative = false;
        int markerID;
        for(ParseTree pt : ctx.children) {
            Token token = (Token)pt.getPayload();
            if(token.getType() == bigraphParser.NOT)
                isNegative = true;
            if(isNegative && token.getType() == bigraphParser.IDENTIFIER) {
                markerID = markersMap.get(pt.getText());
                if(!positiveMarkers.contains(markerID))
                    negativeMarkers.add(markersMap.get(pt.getText()));
                else {
                    spotErrorsString.append("Can't ask property ").append(pt.getText()).append(" and its negation to be true at the same time in an acceptance state\n");
                    spotReady = false;
                }
                isNegative = false;
            } else if (token.getType() == bigraphParser.IDENTIFIER) {
                if((pt.getText().equals("t") || pt.getText().equals("f")) && ctx.FIN()==null && ctx.INF()==null) {
                    spotInfo.addAcceptanceState(new SpotAcceptanceState(pt.getText()));
                    degenerateSpot = true;
                    spotInfo.setBooleanAcceptanceState();
                } else {
                    markerID = markersMap.get(pt.getText());
                    if (!negativeMarkers.contains(markerID))
                        positiveMarkers.add(markersMap.get(pt.getText()));
                    else {
                        spotErrorsString.append("Can't ask property ").append(pt.getText()).append(" and its negation to be true at the same time in an acceptance state\n");
                        spotReady = false;
                    }
                    positiveMarkers.add(markersMap.get(pt.getText()));
                }
            }
        }

        if(!degenerateSpot) {
            // Verifying that the acceptance state has never been inserted into our model
            boolean found = false;
            for (SpotAcceptanceState sp : spotInfo.getAcceptanceStates())
                if (sp.verify(positiveMarkers, negativeMarkers)) {
                    found = true;
                    spotReady = false;
                    spotErrorsString.append("Found duplicated acceptance state: ").append(sp.getAcceptanceStateString()).append("\n");
                }
            if (!found) {
                int startPosition = ctx.start.getStartIndex();
                int endPosition = ctx.stop.getStopIndex();
                Interval interval = new Interval(startPosition, endPosition);
                if (ctx.FIN() != null)
                    acceptanceSpecification = ctx.start.getInputStream().getText(interval)
                            .replaceAll("Fin", "")
                            .replaceAll("\\)", "")
                            .replaceAll("\\(", "");
                else if (ctx.INF() != null)
                    acceptanceSpecification = ctx.start.getInputStream().getText(interval)
                            .replaceAll("Inf", "")
                            .replaceAll("\\)", "")
                            .replaceAll("\\(", "");
                // I extract the property specification we're substituting in order to store it
                spotInfo.addAcceptanceState(new SpotAcceptanceState(acceptanceCounter, acceptanceSpecification, positiveMarkers, negativeMarkers));
                associatedID = acceptanceCounter;
                acceptanceCounter++;
            }
            if (ctx.FIN() != null)
                spotSpecifications.append("Fin(").append(associatedID).append(")");
            if (ctx.INF() != null)
                spotSpecifications.append("Inf(").append(associatedID).append(")");
        }

        return visitChildren(ctx);
    }

    @Override public Void visitExtra_properties(bigraphParser.Extra_propertiesContext ctx) {
        if(spotSpecifications!=null) {
            spotInfo.setAcceptanceStatesSpecification(spotSpecifications.toString());
            if (!spotInfo.checkStatesNumber()) {
                spotReady = false;
                spotErrorsString.append("Number of specified acceptance states doesn't match");
            }
        }
        if(ctx.IDENTIFIER()!=null && ctx.IDENTIFIER().size() > 0) {
            currentPropertyLanguage = ctx.IDENTIFIER(0).toString();
            currentFormat = ctx.IDENTIFIER(1).toString();
        }
        return visitChildren(ctx);
    }

    @Override public Void visitExtra_statements (bigraphParser.Extra_statementsContext ctx) {
        // Since they do vary a lot and have not a fixed, specified grammar we just print properties to file
        if(ctx.children!= null) {
            int startPosition = ctx.start.getStartIndex();
            int endPosition = ctx.stop.getStopIndex();
            Interval interval = new Interval(startPosition, endPosition);
            propertiesString = ctx.start.getInputStream().getText(interval);
        } else
            propertiesString = "";
        exporter.addPropertyFile(currentPropertyLanguage,currentFormat,propertiesString);
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

    public String getSpotErrorsString() {
        return spotErrorsString.toString();
    }

    private void createModelGraph(DirectedMultigraph<Vertex,DefaultEdge> model) {
        graphsCollection.addModel(model);
    }

    private void createReactionGraph(DirectedMultigraph<Vertex,DefaultEdge> redex, DirectedMultigraph<Vertex,DefaultEdge> reactum, String ruleName) {
        RedexReactumPair reaction = new RedexReactumPair(redex,reactum,ruleName);
        graphsCollection.addReaction(reaction);
    }

    public SpotInfo getSpotInfo() {
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