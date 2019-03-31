import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayList;

/**
 * This class provides an empty implementation of {@link BigraphVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class BigraphBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements BigraphVisitor<T> {

	// We store identifiers in order to check repetitions and wrong uses
	private ArrayList<String> controls = new ArrayList<>();
	private ArrayList<String> names = new ArrayList<>();
	private ArrayList<String> ruleNames = new ArrayList<>();

	// This serves the purpose of differentiating analysis behaviours for models' rules
	private boolean modelVisited = false;


	// Model shall be submitted to bigmc/external tools only when no error shows up.
	// This variable shall do the trick
	private boolean acceptableModel = true;

	// Exception Handling
	private short WARNING = 0;
	private short ERROR = 1;

	@Override
	public T visitBigraph(BigraphParser.BigraphContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public T visitControls(BigraphParser.ControlsContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public T visitControl_statements(BigraphParser.Control_statementsContext ctx) throws SemanticException {

		// Controls are added to the respective list
		// If the control name is already present an error is signaled
		String controlsIdentifier = ctx.getChild(1).toString();
		if (!controls.contains(controlsIdentifier))
			controls.add(controlsIdentifier);
		else {
			acceptableModel = false;
			throw new SemanticException(ctx, ERROR, "Repeated controls definition");
		}
		return visitChildren(ctx);

	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public T visitNames(BigraphParser.NamesContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public T visitName_statements(BigraphParser.Name_statementsContext ctx) throws SemanticException {

		// Names are added to the respective list
		// If names are already present an error is signaled
		String nameIdentifier = ctx.getChild(1).toString();
		if (!names.contains(nameIdentifier))
			names.add(nameIdentifier);
		else {
			acceptableModel = false;
			throw new SemanticException(ctx, ERROR, "Repeated names declaration");
		}
		return visitChildren(ctx);

	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public T visitReactions(BigraphParser.ReactionsContext ctx) throws SemanticException {

		// Reactions rules should present distinct, unique names!
		if (ctx.getChild(0).toString().equals("rule")) {
			String identifier = ctx.getChild(1).toString();

			if (controls.contains(identifier))
				throw new SemanticException(ctx, WARNING, "Reaction rules shouldn't be named after controls to avoid confusion");
			if (names.contains(identifier))
				throw new SemanticException(ctx, WARNING, "Reaction rules shouldn't share identifier with an outer/inner name");
			if (ruleNames.contains(identifier)) {
				acceptableModel = false;
				throw new SemanticException(ctx, ERROR, "Repeated rule declaration");
			}
			if(acceptableModel)
				ruleNames.add(identifier);
			}
		return visitChildren(ctx);
	}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitReaction_statement (BigraphParser.Reaction_statementContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitExpression (BigraphParser.ExpressionContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitRegions (BigraphParser.RegionsContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitPrefix (BigraphParser.PrefixContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitLinks (BigraphParser.LinksContext ctx){
			if (modelVisited && ctx.getChild(0).toString().equals("@"))
				System.out.println("Hai messo una variabile nel modello, idiota!");
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitModel (BigraphParser.ModelContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitProperty (BigraphParser.PropertyContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitProperty_statement (BigraphParser.Property_statementContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitBoolean_expression (BigraphParser.Boolean_expressionContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitBinary_operation (BigraphParser.Binary_operationContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitTerm (BigraphParser.TermContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitParameters_list (BigraphParser.Parameters_listContext ctx){
			return visitChildren(ctx);
		}
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation returns the result of calling
		 * {@link #visitChildren} on {@code ctx}.</p>
		 */
		@Override public T visitParameter (BigraphParser.ParameterContext ctx){
			return visitChildren(ctx);
		}

		private void reportError (ParserRuleContext ctx, String error){
			System.out.println("[ERROR - Line " + ctx.start.getLine() + "] : " + error);
			acceptableModel = false;
		}

		public String getParseResult () {
			if (acceptableModel)
				return "[RESULT : PASSED] \nModel is ready";
			else
				return "[RESULT : FAILED] \nModel presents unaddressed errors";
		}
}