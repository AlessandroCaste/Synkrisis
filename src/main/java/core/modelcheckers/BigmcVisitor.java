package core.modelcheckers;

import antlr.bigraph.bigraphParser;
import antlr.bigraph.bigraphVisitor;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;


public class BigmcVisitor extends AbstractParseTreeVisitor<Void> implements bigraphVisitor<Void> {

    // Verifies the model can be submitted as it is to bigmc
    private boolean bigmcReady = true;

    @Override
    public Void visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    @Override public Void visitBigraph(bigraphParser.BigraphContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public Void visitControls(bigraphParser.ControlsContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitControl_statements(bigraphParser.Control_statementsContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public Void visitNames(bigraphParser.NamesContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitName_statements(bigraphParser.Name_statementsContext ctx) {
        return visitChildren(ctx);

    }

    @Override
    public Void visitReactions(bigraphParser.ReactionsContext ctx) {
        return visitChildren(ctx);
    }


    @Override public Void visitReaction_statement (bigraphParser.Reaction_statementContext ctx){
        if(ctx.PROBABILITY() != null) {
            bigmcReady = false;
        }
        return visitChildren(ctx);
    }

    @Override public Void visitExpression (bigraphParser.ExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitRegions (bigraphParser.RegionsContext ctx){return visitChildren(ctx);
    }


    @Override public Void visitPrefix (bigraphParser.PrefixContext ctx){return visitChildren(ctx);}

    @Override public Void visitLinks (bigraphParser.LinksContext ctx){
        return visitChildren(ctx);
    }


    @Override public Void visitModel (bigraphParser.ModelContext ctx){
        return visitChildren(ctx);
    }


    @Override public Void visitMarker (bigraphParser.MarkerContext ctx) {
        return visitChildren(ctx);
    }


    @Override public Void visitMarker_statement (bigraphParser.Marker_statementContext ctx){
        return visitChildren(ctx);
    }

    @Override public Void visitProperties (bigraphParser.PropertiesContext ctx) {
        if(ctx.PROPERTIES()!=null)
            bigmcReady = false;
        return visitChildren(ctx);
    }

    @Override public Void visitSpot_statement(bigraphParser.Spot_statementContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitAcc_name(bigraphParser.Acc_nameContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitAcceptance(bigraphParser.AcceptanceContext ctx) { return visitChildren(ctx); }

    @Override public Void visitAcceptance_cond1(bigraphParser.Acceptance_cond1Context ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitAcceptance_cond2(bigraphParser.Acceptance_cond2Context ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitPrism_properties(bigraphParser.Prism_propertiesContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Void visitPrism_statements(bigraphParser.Prism_statementsContext ctx) {
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

    boolean isBigmcReady() { return bigmcReady; }

}