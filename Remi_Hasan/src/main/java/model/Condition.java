package model;

import expression.Expression;

import java.util.ArrayList;

// TODO rename to something like conditionblock for consistency
public class Condition extends Statement {
    public Expression condition;
    public ArrayList<Statement> conditionTrueStatements;
    public ArrayList<Statement> conditionFalseSatements;

    // TODO force condition to be of evaluated type ExpressionVariableBoolean
    public Condition(Expression condition, ArrayList<Statement> conditionTrueStatements, ArrayList<Statement> conditionFalseSatements) {
        this.condition = condition;
        this.conditionTrueStatements = conditionTrueStatements;
        this.conditionFalseSatements = conditionFalseSatements;
    }

    @Override
    public boolean isCondition() {
        return true;
    }
}