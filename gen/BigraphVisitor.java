// Generated from C:/Users/Utente/Documents/GitHub/Synkrisis/src\Bigraph.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BigraphParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BigraphVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BigraphParser#bigraph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBigraph(BigraphParser.BigraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#controls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControls(BigraphParser.ControlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#control_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControl_statements(BigraphParser.Control_statementsContext ctx) throws SemanticException;
	/**
	 * Visit a parse tree produced by {@link BigraphParser#names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNames(BigraphParser.NamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#name_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName_statements(BigraphParser.Name_statementsContext ctx) throws SemanticException;
	/**
	 * Visit a parse tree produced by {@link BigraphParser#reactions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReactions(BigraphParser.ReactionsContext ctx) throws SemanticException;
	/**
	 * Visit a parse tree produced by {@link BigraphParser#reaction_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReaction_statement(BigraphParser.Reaction_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(BigraphParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#regions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegions(BigraphParser.RegionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefix(BigraphParser.PrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#links}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinks(BigraphParser.LinksContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(BigraphParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(BigraphParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#property_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty_statement(BigraphParser.Property_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expression(BigraphParser.Boolean_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#binary_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operation(BigraphParser.Binary_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(BigraphParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#parameters_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters_list(BigraphParser.Parameters_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(BigraphParser.ParameterContext ctx);
}