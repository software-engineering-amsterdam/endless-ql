package models.ast.elements.statement;

import models.ast.elements.expressions.Expression;

import java.util.ArrayList;

public class IfStatement implements Statement {

    private Expression conditionExpression;
    private ArrayList<Statement> statementList = new ArrayList<>();

    public IfStatement(Expression conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public boolean addStatement(Statement statement) {
        return this.statementList.add(statement);
    }

    @Override
    public void debugPrint() {
        System.out.println("Condition statement: " + this.conditionExpression);
        System.out.println("Contains : " + this.statementList.size() + " statements:");
        if (this.statementList.size() > 0) {
            for (Statement statement: this.statementList) {
                statement.debugPrint();
            }
        }
    }
}
