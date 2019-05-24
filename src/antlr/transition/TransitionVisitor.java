// Generated from /home/ale/Synkrisis/src/core/g4model/Transition.g4 by ANTLR 4.7.2
package antlr.transition;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TransitionParser}.
 *
 * @param <Void> The return type of the visit operation.
 */
public interface TransitionVisitor<Void> extends ParseTreeVisitor<Void> {
	/**
	 * Visit a parse tree produced by {@link TransitionParser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitTransition(TransitionParser.TransitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TransitionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitExpression(TransitionParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TransitionParser#regions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitRegions(TransitionParser.RegionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TransitionParser#prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitPrefix(TransitionParser.PrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link TransitionParser#links}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitLinks(TransitionParser.LinksContext ctx);
	/**
	 * Visit a parse tree produced by {@link TransitionParser#state}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitState(TransitionParser.StateContext ctx);
	/**
	 * Visit a parse tree produced by {@link TransitionParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitProperties(TransitionParser.PropertiesContext ctx);
}