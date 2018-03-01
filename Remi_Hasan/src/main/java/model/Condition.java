package model;

import expression.Expression;

import java.util.ArrayList;
import java.util.List;

// TODO rename to something like conditionblock for consistency
public class Condition extends Statement {
    public Expression condition;
    public List<Statement> trueStatements;
    public List<Statement> falseStatements;

    // TODO force condition to be of evaluated type ExpressionVariableBoolean
    public Condition(Expression condition, List<Statement> conditionTrueStatements, List<Statement> conditionFalseStatements) {
        this.condition = condition;
        this.trueStatements = conditionTrueStatements;
        this.falseStatements = conditionFalseStatements;
    }

    @Override
    public boolean isCondition() {
        return true;
    }
}