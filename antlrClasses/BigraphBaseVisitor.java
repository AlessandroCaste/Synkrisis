import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.ArrayList;
import java.util.HashMap;



//Implementation adopts String in order to pass results across visitors
// While this may force some additional casting using String is a more general approach
public class BigraphBaseVisitor extends AbstractParseTreeVisitor<String> implements BigraphVisitor<String> {

    // We store identifiers in order to check repetitions and wrong uses
    private HashMap<String,Integer> controlsMap = new HashMap<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> ruleNames = new ArrayList<>();

    // Maps to track usages
    private HashMap<String,Integer> controlsUsage = new HashMap<>();
    private HashMap<String,Integer> namesUsage = new HashMap<>();


    // This differentiates analysis for models' expressions
    private boolean modelVisited = false;

    // Model name to check file integrity
    private String modelName = "null";


    @Override
    public String visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    // Model shall be submitted to bigmc/external tools only when no error shows up.
    // This variable shall do the trick
    private boolean acceptableModel = true;

    // Exception Handling
    private short WARNING = 0;
    private short ERROR = 1;

    // I use a ControlChecker class to store all info needed for arity control and a global variable to track link arity
    private ControlChecker lastControl;
    private int linkArity = 0;
    private boolean invalidControl = false;
    private boolean validControl = true;

    @Override
    public String visitBigraph(BigraphParser.BigraphContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public String visitControls(BigraphParser.ControlsContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public String visitControl_statements(BigraphParser.Control_statementsContext ctx) {

        // Controls are added to the respective list
        // If the control name is already present an error is signaled
        String controlsIdentifier = ctx.IDENTIFIER().toString();
        if(names.contains(controlsIdentifier))
            reportError(ctx,ERROR,"Controls can't be declared after previously declared names");
        if (!controlsMap.containsKey(controlsIdentifier) && !names.contains(controlsIdentifier)) {
            int arity = Integer.parseInt(ctx.DIGIT().getText());
            controlsMap.put(controlsIdentifier,arity);
            controlsUsage.put(controlsIdentifier,0);
        }
        else {
            acceptableModel = false;
            reportError(ctx, ERROR, "Repeated control declaration");
        }
        return visitChildren(ctx);

    }


    @Override
    public String visitNames(BigraphParser.NamesContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public String visitName_statements(BigraphParser.Name_statementsContext ctx) {

        // Names are added to the respective list
        // If names are already present an error is signaled
        String nameIdentifier = ctx.getChild(1).toString();
        if (!names.contains(nameIdentifier)) {
            names.add(nameIdentifier);
            namesUsage.put(nameIdentifier,0);
        }
        else {
            acceptableModel = false;
            reportError(ctx, ERROR, "Repeated name declaration");
        }
        return visitChildren(ctx);

    }



    @Override
    public String visitReactions(BigraphParser.ReactionsContext ctx) {

        // Reactions rules should present distinct, unique names!
        if (ctx.RULE() != null) {

            String identifier = ctx.IDENTIFIER().toString();

            if (controlsMap.containsKey(identifier))
                reportError(ctx, WARNING, "Reaction rules shouldn't be named after controlsMap to avoid confusion");
            if (names.contains(identifier))
                reportError(ctx, WARNING, "Reaction rules shouldn't share identifier with an outer/inner name");
            if (ruleNames.contains(identifier)) {
                acceptableModel = false;
                reportError(ctx, ERROR, "Repeated rule declaration");
            }
            if(acceptableModel)
                ruleNames.add(identifier);
        }
        return visitChildren(ctx);
    }


    @Override public String visitReaction_statement (BigraphParser.Reaction_statementContext ctx){
        return visitChildren(ctx);
    }


    @Override public String visitExpression (BigraphParser.ExpressionContext ctx) {

        // Reporting the usage identifiers in rule IDENTIFIER (LSQ links RSQ)?
        if (ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();

            // I update the number of usages of the corresponding name
            if (namesUsage.containsKey(identifier)) {
                namesUsage.put(identifier, namesUsage.get(identifier) + 1);
            }

            // An error is thrown if there's a link without context to sustain it
            else if (!controlsUsage.containsKey(identifier) && ctx.links() != null) {
                reportError(ctx, ERROR, "Attempt to use an undeclared control: " + identifier);
                lastControl = new ControlChecker(ctx,0,invalidControl);
            }

            // Otherwise I check the controls set to find a match..
            else if (controlsUsage.containsKey(identifier)) {

                int controlArity = controlsMap.get(identifier);
                // ..and I eventually update the number of usages
                controlsUsage.put(identifier, controlsUsage.get(identifier) + 1);

                // Checking whether the arity of IDENTIFIER is respected I use the ControlChecker class
                lastControl = new ControlChecker(ctx, controlArity,validControl);
            }
        }

            return visitChildren(ctx);
    }

    @Override public String visitRegions (BigraphParser.RegionsContext ctx){
        return visitChildren(ctx);
    }


    @Override public String visitPrefix (BigraphParser.PrefixContext ctx){
        return visitChildren(ctx);
    }


    @Override public String visitLinks (BigraphParser.LinksContext ctx){
        if(modelVisited && ctx.VARIABLE() != null)
            reportError(ctx,ERROR,"Variable used in model definition");
        if(ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();
            if(namesUsage.containsKey(identifier))
                namesUsage.put(identifier, namesUsage.get(identifier)+1);
            if(controlsUsage.containsKey(identifier))
                controlsUsage.put(identifier, controlsUsage.get(identifier)+1);
        }
        // I evaluate recursively the number of arguments in a link
        if(ctx.COMMA() != null ) {
            linkArity++;
        } else {
            linkArity++;
            if(linkArity != lastControl.getArity() && lastControl.isValid())
                reportError(lastControl.getCtx(),ERROR,"Control " + lastControl.getName() + " has arity " + lastControl.getArity() + " not " + linkArity);
            linkArity = 0;
        }
        return visitChildren(ctx);
    }


    @Override public String visitModel (BigraphParser.ModelContext ctx){
        modelName = ctx.getChild(1).getText();
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitProperty (BigraphParser.PropertyContext ctx){
        return visitChildren(ctx);
    }


    @Override public String visitProperty_statement (BigraphParser.Property_statementContext ctx){
        return visitChildren(ctx);
    }


    @Override public String visitBoolean_expression (BigraphParser.Boolean_expressionContext ctx){
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitBinary_operation (BigraphParser.Binary_operationContext ctx){
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitTerm (BigraphParser.TermContext ctx){
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitParameters_list (BigraphParser.Parameters_listContext ctx){
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitParameter (BigraphParser.ParameterContext ctx){
        return visitChildren(ctx);
    }

    private void reportError (ParserRuleContext ctx, int type, String text){
        String reportString = "";
        if(type == WARNING)
            reportString = "[WARNING - Line ";
        else if(type == ERROR) {
            reportString = "[ERROR - Line ";
            acceptableModel = false;
        }
        reportString = reportString + ctx.start.getLine() + "] : " + text;
        System.out.println(reportString);
    }

    public String getParseResult () {
        if (acceptableModel)
            return "[RESULT : PASSED] \nModel is ready";
        else
            return "[RESULT : FAILED]";
    }

    // This method returns all controls and names whose usage value has remained stuck to 0
    public String checkUnusedVariables(){

        ArrayList<String> unusedControls = new ArrayList<>();
        ArrayList<String> unusedNames	 = new ArrayList<>();
        StringBuilder returnString = new StringBuilder();

        for(String key : controlsUsage.keySet())
            if(controlsUsage.get(key) == 0)
                unusedControls.add(key);

        for (String key : namesUsage.keySet())
            if(namesUsage.get(key) == 0)
                unusedNames.add(key);

        if(unusedControls.size()>0) {
            returnString.append("[WARNING] The following controls are declared and never used:\n");
            for(String s : unusedControls)
                returnString.append(s).append(" ");
            returnString.append("\n");
        }
        if(unusedNames.size()>0) {
            returnString.append("[WARNING] The following names are declared and never used:\n");
            for(String s : unusedNames)
                returnString.append(s).append(" ");
        }

        if(unusedControls.size() == 0 && unusedNames.size() == 0)
            returnString.append("[REPORT] All controlsMap and names declared are used");
        return returnString.toString();
    }

    public boolean checkModelName(String fileName) {
        if (!fileName.equals(modelName+".txt") || !fileName.equals(modelName+".bigraph")) {
            acceptableModel = false;
            return false;
        } else
            return true;
    }

}