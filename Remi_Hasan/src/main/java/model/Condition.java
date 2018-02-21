<<<<<<< HEAD
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
=======
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
>>>>>>> 3c171d077d7945c6cc73b62beb833d1ee457800c
