package main.org.uva.ql.ast;

import java.util.ArrayList;
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
        this.elseSide = new ArrayList<>();
    }

    @Override
    public String toString() {
        String conditional = String.format("If %s \n", this.condition);
        for (Statement statement : ifSide) {
            conditional += String.format("\t\t%s\n",statement);
        }
        return conditional;
    }
}
