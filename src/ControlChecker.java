class ControlChecker {

    private BigraphParser.ExpressionContext ctx;
    private int arity;
    private boolean valid;

    public ControlChecker(BigraphParser.ExpressionContext ctx, int arity, boolean valid) {
        this.ctx = ctx;
        this.arity = arity;
        this.valid = valid;
    }

    boolean isValid() {
        return valid;
    }

    BigraphParser.ExpressionContext getCtx() {
        return ctx;
    }

    int getArity() {
        return arity;
    }

    String getName() {
        return ctx.IDENTIFIER().toString();
    }

}

