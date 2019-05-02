package logic;// Generated from C:/Users/caste/IdeaProjects/Synkrisis/src\Bigraph.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BigraphParser}.
 *
 * Use {@link Void} for operations with no return type.
 */
public interface BigraphVisitor<Void> extends ParseTreeVisitor<Void> {
	/**
	 * Visit a parse tree produced by {@link BigraphParser#bigraph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitBigraph(BigraphParser.BigraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#controls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitControls(BigraphParser.ControlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#control_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitControl_statements(BigraphParser.Control_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitNames(BigraphParser.NamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#name_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitName_statements(BigraphParser.Name_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#reactions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitReactions(BigraphParser.ReactionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#reaction_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitReaction_statement(BigraphParser.Reaction_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitExpression(BigraphParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#regions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitRegions(BigraphParser.RegionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitPrefix(BigraphParser.PrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#links}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitLinks(BigraphParser.LinksContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitModel(BigraphParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitProperty(BigraphParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#property_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitProperty_statement(BigraphParser.Property_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitBoolean_expression(BigraphParser.Boolean_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#binary_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitBinary_operation(BigraphParser.Binary_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitTerm(BigraphParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#parameters_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitParameters_list(BigraphParser.Parameters_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigraphParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitParameter(BigraphParser.ParameterContext ctx);
}