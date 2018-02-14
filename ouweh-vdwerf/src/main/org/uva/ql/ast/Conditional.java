package main.org.uva.ql.ast;

import java.util.List;

public class Conditional extends Statement{

    private Expression condition;
    private List<Statement> ifSide;
    private List<Statement> elseSide;

    public Conditional (Expression condition, List<Statement> ifSide, List<Statement> elseSide) {
        this.condition = condition;
        this.ifSide = ifSide;
        this.elseSide = elseSide;
    }

    public Conditional (Expression condition, List<Statement> ifSide) {
        this.condition = condition;
        this.ifSide = ifSide;
    }
}
