import org.antlr.v4.runtime.ParserRuleContext;

public class SemanticException extends Exception {

    private short WARNING = 0;
    private short ERROR   = 1;

    public SemanticException(ParserRuleContext ctx, int type, String text) {
        if(type == WARNING)
            System.out.println("[WARNING - Line ");
        else if(type == ERROR)
            System.out.println("[WARNING - Line ");
        System.out.println(ctx.start.getLine() + "] : " + text );
    }

}
