package model;

import expression.Expression;

import java.util.ArrayList;

// TODO rename to something like conditionblock for consistency
public class Condition extends Statement {
    public Expression condition;
    public ArrayList<Statement> statements;

    // TODO force condition to be of evaluated type ExpressionVariableBoolean
    public Condition(Expression condition, ArrayList<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    @Override
    public boolean isCondition() {
        return true;
    }
}
