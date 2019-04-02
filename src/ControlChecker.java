public class ControlChecker {

    private BigraphParser.ExpressionContext ctx;
    private int arity;
    private boolean valid;

    public ControlChecker(BigraphParser.ExpressionContext ctx, int arity, boolean valid) {
        this.ctx = ctx;
        this.arity = arity;
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public BigraphParser.ExpressionContext getCtx() {
        return ctx;
    }

    public int getArity() {
        return arity;
    }

    public String getName() {
        return ctx.IDENTIFIER().toString();
    }

}

