// Generated from /home/ale/Synkrisis/src/core/g4model/Transition.g4 by ANTLR 4.7.2
package antlr.transition;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TransitionParser}.
 */
public interface TransitionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TransitionParser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(TransitionParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TransitionParser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(TransitionParser.TransitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TransitionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TransitionParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TransitionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TransitionParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TransitionParser#regions}.
	 * @param ctx the parse tree
	 */
	void enterRegions(TransitionParser.RegionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TransitionParser#regions}.
	 * @param ctx the parse tree
	 */
	void exitRegions(TransitionParser.RegionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TransitionParser#prefix}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(TransitionParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link TransitionParser#prefix}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(TransitionParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link TransitionParser#links}.
	 * @param ctx the parse tree
	 */
	void enterLinks(TransitionParser.LinksContext ctx);
	/**
	 * Exit a parse tree produced by {@link TransitionParser#links}.
	 * @param ctx the parse tree
	 */
	void exitLinks(TransitionParser.LinksContext ctx);
	/**
	 * Enter a parse tree produced by {@link TransitionParser#state}.
	 * @param ctx the parse tree
	 */
	void enterState(TransitionParser.StateContext ctx);
	/**
	 * Exit a parse tree produced by {@link TransitionParser#state}.
	 * @param ctx the parse tree
	 */
	void exitState(TransitionParser.StateContext ctx);
	/**
	 * Enter a parse tree produced by {@link TransitionParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(TransitionParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TransitionParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(TransitionParser.PropertiesContext ctx);
}