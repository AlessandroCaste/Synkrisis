package core.syntaxAnalysis;

import antlr.bigraph.bigraphParser;

class ControlChecker {

    private bigraphParser.ExpressionContext ctx;
    private int arity;
    private boolean valid;

    ControlChecker(bigraphParser.ExpressionContext ctx, int arity, boolean valid) {
        this.ctx = ctx;
        this.arity = arity;
        this.valid = valid;
    }

    boolean isValid() {
        return valid;
    }

    bigraphParser.ExpressionContext getCtx() {
        return ctx;
    }

    int getArity() {
        return arity;
    }

    String getName() {
        return ctx.IDENTIFIER().toString();
    }

}

