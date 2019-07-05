// Generated from C:/Users/Utente/Documents/GitHub/Synkrisis/src/main/java/antlr/g4models\bigraph.g4 by ANTLR 4.7.2
package antlr.bigraph;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link bigraphParser}.
 */
public interface bigraphListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link bigraphParser#bigraph}.
	 * @param ctx the parse tree
	 */
	void enterBigraph(bigraphParser.BigraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#bigraph}.
	 * @param ctx the parse tree
	 */
	void exitBigraph(bigraphParser.BigraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#controls}.
	 * @param ctx the parse tree
	 */
	void enterControls(bigraphParser.ControlsContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#controls}.
	 * @param ctx the parse tree
	 */
	void exitControls(bigraphParser.ControlsContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#control_statements}.
	 * @param ctx the parse tree
	 */
	void enterControl_statements(bigraphParser.Control_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#control_statements}.
	 * @param ctx the parse tree
	 */
	void exitControl_statements(bigraphParser.Control_statementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#names}.
	 * @param ctx the parse tree
	 */
	void enterNames(bigraphParser.NamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#names}.
	 * @param ctx the parse tree
	 */
	void exitNames(bigraphParser.NamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#name_statements}.
	 * @param ctx the parse tree
	 */
	void enterName_statements(bigraphParser.Name_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#name_statements}.
	 * @param ctx the parse tree
	 */
	void exitName_statements(bigraphParser.Name_statementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#reactions}.
	 * @param ctx the parse tree
	 */
	void enterReactions(bigraphParser.ReactionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#reactions}.
	 * @param ctx the parse tree
	 */
	void exitReactions(bigraphParser.ReactionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#reaction_statement}.
	 * @param ctx the parse tree
	 */
	void enterReaction_statement(bigraphParser.Reaction_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#reaction_statement}.
	 * @param ctx the parse tree
	 */
	void exitReaction_statement(bigraphParser.Reaction_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(bigraphParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(bigraphParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#regions}.
	 * @param ctx the parse tree
	 */
	void enterRegions(bigraphParser.RegionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#regions}.
	 * @param ctx the parse tree
	 */
	void exitRegions(bigraphParser.RegionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#prefix}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(bigraphParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#prefix}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(bigraphParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#links}.
	 * @param ctx the parse tree
	 */
	void enterLinks(bigraphParser.LinksContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#links}.
	 * @param ctx the parse tree
	 */
	void exitLinks(bigraphParser.LinksContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(bigraphParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(bigraphParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#marker}.
	 * @param ctx the parse tree
	 */
	void enterMarker(bigraphParser.MarkerContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#marker}.
	 * @param ctx the parse tree
	 */
	void exitMarker(bigraphParser.MarkerContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#marker_statement}.
	 * @param ctx the parse tree
	 */
	void enterMarker_statement(bigraphParser.Marker_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#marker_statement}.
	 * @param ctx the parse tree
	 */
	void exitMarker_statement(bigraphParser.Marker_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(bigraphParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(bigraphParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#property_statements}.
	 * @param ctx the parse tree
	 */
	void enterProperty_statements(bigraphParser.Property_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#property_statements}.
	 * @param ctx the parse tree
	 */
	void exitProperty_statements(bigraphParser.Property_statementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expression(bigraphParser.Boolean_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expression(bigraphParser.Boolean_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#binary_operation}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operation(bigraphParser.Binary_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#binary_operation}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operation(bigraphParser.Binary_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(bigraphParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(bigraphParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#parameters_list}.
	 * @param ctx the parse tree
	 */
	void enterParameters_list(bigraphParser.Parameters_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#parameters_list}.
	 * @param ctx the parse tree
	 */
	void exitParameters_list(bigraphParser.Parameters_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link bigraphParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(bigraphParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link bigraphParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(bigraphParser.ParameterContext ctx);
}