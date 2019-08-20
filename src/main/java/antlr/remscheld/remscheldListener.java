// Generated from /home/ale/Synkrisis/src/test/java/remscheld/remscheld.g4 by ANTLR 4.7.2
package antlr.remscheld;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link remscheldParser}.
 */
public interface remscheldListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link remscheldParser#remscheld}.
	 * @param ctx the parse tree
	 */
	void enterRemscheld(remscheldParser.RemscheldContext ctx);
	/**
	 * Exit a parse tree produced by {@link remscheldParser#remscheld}.
	 * @param ctx the parse tree
	 */
	void exitRemscheld(remscheldParser.RemscheldContext ctx);
	/**
	 * Enter a parse tree produced by {@link remscheldParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(remscheldParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link remscheldParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(remscheldParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link remscheldParser#identifiers}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiers(remscheldParser.IdentifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link remscheldParser#identifiers}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiers(remscheldParser.IdentifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link remscheldParser#children}.
	 * @param ctx the parse tree
	 */
	void enterChildren(remscheldParser.ChildrenContext ctx);
	/**
	 * Exit a parse tree produced by {@link remscheldParser#children}.
	 * @param ctx the parse tree
	 */
	void exitChildren(remscheldParser.ChildrenContext ctx);
}