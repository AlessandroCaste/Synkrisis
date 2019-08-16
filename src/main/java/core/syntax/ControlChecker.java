package core.syntax;

import org.antlr.v4.runtime.ParserRuleContext;

// Stores information about a control node and its context for checks and further analysis
class ControlChecker {

    private ParserRuleContext ctx;
    private String identifier;
    private int arity;
    private boolean valid;


    ControlChecker(ParserRuleContext ctx, String identifier, int arity, boolean valid) {
        this.ctx = ctx;
        this.identifier = identifier;
        this.arity = arity;
        this.valid = valid;
    }

    boolean isValid() {
        return valid;
    }

    ParserRuleContext getCtx() {
        return ctx;
    }

    int getArity() {
        return arity;
    }

    String getName() {
        return identifier;
    }

}

