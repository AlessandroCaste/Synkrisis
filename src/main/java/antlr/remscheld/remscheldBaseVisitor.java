// Generated from /home/ale/Synkrisis/src/test/java/remscheld/remscheld.g4 by ANTLR 4.7.2
package antlr.remscheld;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link remscheldVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <Void> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class remscheldBaseVisitor<Void> extends AbstractParseTreeVisitor<Void> implements remscheldVisitor<Void> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Void visitRemscheld(remscheldParser.RemscheldContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Void visitNode(remscheldParser.NodeContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Void visitIdentifiers(remscheldParser.IdentifiersContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Void visitChildren(remscheldParser.ChildrenContext ctx) { return visitChildren(ctx); }
}