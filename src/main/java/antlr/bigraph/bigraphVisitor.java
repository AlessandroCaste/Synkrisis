// Generated from C:/Users/Utente/Documents/GitHub/Synkrisis/src/main/java/antlr/g4models\bigraph.g4 by ANTLR 4.7.2
package antlr.bigraph;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link bigraphParser}.
 *
 * @param <Void> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface bigraphVisitor<Void> extends ParseTreeVisitor<Void> {
	/**
	 * Visit a parse tree produced by {@link bigraphParser#bigraph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitBigraph(bigraphParser.BigraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#controls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitControls(bigraphParser.ControlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#control_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitControl_statements(bigraphParser.Control_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitNames(bigraphParser.NamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#name_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitName_statements(bigraphParser.Name_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#reactions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitReactions(bigraphParser.ReactionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#reaction_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitReaction_statement(bigraphParser.Reaction_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitExpression(bigraphParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#regions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitRegions(bigraphParser.RegionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitPrefix(bigraphParser.PrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#links}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitLinks(bigraphParser.LinksContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitModel(bigraphParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#marker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitMarker(bigraphParser.MarkerContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#marker_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitMarker_statement(bigraphParser.Marker_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitProperty(bigraphParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#property_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitProperty_statements(bigraphParser.Property_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitBoolean_expression(bigraphParser.Boolean_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#binary_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitBinary_operation(bigraphParser.Binary_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitTerm(bigraphParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#parameters_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitParameters_list(bigraphParser.Parameters_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link bigraphParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitParameter(bigraphParser.ParameterContext ctx);
}