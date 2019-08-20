// Generated from /home/ale/Synkrisis/src/test/java/remscheld/remscheld.g4 by ANTLR 4.7.2
package antlr.remscheld;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link remscheldParser}.
 *
 * @param <Void> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface remscheldVisitor<Void> extends ParseTreeVisitor<Void> {
	/**
	 * Visit a parse tree produced by {@link remscheldParser#remscheld}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitRemscheld(remscheldParser.RemscheldContext ctx);
	/**
	 * Visit a parse tree produced by {@link remscheldParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitNode(remscheldParser.NodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link remscheldParser#identifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitIdentifiers(remscheldParser.IdentifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link remscheldParser#children}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Void visitChildren(remscheldParser.ChildrenContext ctx);
}