package org.uva.ql.ast;


import org.uva.ql.ast.expression.Expression;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class Conditional extends Statement {

    private Expression condition;
    private List<Statement> ifSide;
    private List<Statement> elseSide;

    public Conditional(Expression condition, List<Statement> ifSide, List<Statement> elseSide) {
        this.condition = condition;
        this.ifSide = ifSide;
        this.elseSide = elseSide;
    }

    public Conditional(Expression condition, List<Statement> ifSide) {
        this.condition = condition;
        this.ifSide = ifSide;
        this.elseSide = new ArrayList<>();
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getIfSide() {
        return ifSide;
    }

    public List<Statement> getElseSide() {
        return elseSide;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        String conditional = String.format("If %s \n", this.condition);
        for (Statement statement : ifSide) {
            conditional += String.format("\t\t%s\n", statement);
        }
        return conditional;
    }
}
