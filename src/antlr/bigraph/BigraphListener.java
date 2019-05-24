package antlr.bigraph;// Generated from C:/Users/caste/IdeaProjects/Synkrisis/src\Bigraph.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BigraphParser}.
 */
public interface BigraphListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BigraphParser#bigraph}.
	 * @param ctx the parse tree
	 */
	void enterBigraph(BigraphParser.BigraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#bigraph}.
	 * @param ctx the parse tree
	 */
	void exitBigraph(BigraphParser.BigraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#controls}.
	 * @param ctx the parse tree
	 */
	void enterControls(BigraphParser.ControlsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#controls}.
	 * @param ctx the parse tree
	 */
	void exitControls(BigraphParser.ControlsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#control_statements}.
	 * @param ctx the parse tree
	 */
	void enterControl_statements(BigraphParser.Control_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#control_statements}.
	 * @param ctx the parse tree
	 */
	void exitControl_statements(BigraphParser.Control_statementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#names}.
	 * @param ctx the parse tree
	 */
	void enterNames(BigraphParser.NamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#names}.
	 * @param ctx the parse tree
	 */
	void exitNames(BigraphParser.NamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#name_statements}.
	 * @param ctx the parse tree
	 */
	void enterName_statements(BigraphParser.Name_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#name_statements}.
	 * @param ctx the parse tree
	 */
	void exitName_statements(BigraphParser.Name_statementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#reactions}.
	 * @param ctx the parse tree
	 */
	void enterReactions(BigraphParser.ReactionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#reactions}.
	 * @param ctx the parse tree
	 */
	void exitReactions(BigraphParser.ReactionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#reaction_statement}.
	 * @param ctx the parse tree
	 */
	void enterReaction_statement(BigraphParser.Reaction_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#reaction_statement}.
	 * @param ctx the parse tree
	 */
	void exitReaction_statement(BigraphParser.Reaction_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(BigraphParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(BigraphParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#regions}.
	 * @param ctx the parse tree
	 */
	void enterRegions(BigraphParser.RegionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#regions}.
	 * @param ctx the parse tree
	 */
	void exitRegions(BigraphParser.RegionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#prefix}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(BigraphParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#prefix}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(BigraphParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#links}.
	 * @param ctx the parse tree
	 */
	void enterLinks(BigraphParser.LinksContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#links}.
	 * @param ctx the parse tree
	 */
	void exitLinks(BigraphParser.LinksContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(BigraphParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(BigraphParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(BigraphParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(BigraphParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#property_statement}.
	 * @param ctx the parse tree
	 */
	void enterProperty_statement(BigraphParser.Property_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#property_statement}.
	 * @param ctx the parse tree
	 */
	void exitProperty_statement(BigraphParser.Property_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expression(BigraphParser.Boolean_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expression(BigraphParser.Boolean_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#binary_operation}.
	 * @param ctx the parse tree
	 */
	void enterBinary_operation(BigraphParser.Binary_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#binary_operation}.
	 * @param ctx the parse tree
	 */
	void exitBinary_operation(BigraphParser.Binary_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(BigraphParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(BigraphParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#parameters_list}.
	 * @param ctx the parse tree
	 */
	void enterParameters_list(BigraphParser.Parameters_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#parameters_list}.
	 * @param ctx the parse tree
	 */
	void exitParameters_list(BigraphParser.Parameters_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigraphParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(BigraphParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigraphParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(BigraphParser.ParameterContext ctx);
}