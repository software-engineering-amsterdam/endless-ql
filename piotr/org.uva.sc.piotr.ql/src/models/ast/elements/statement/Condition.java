package models.ast.elements.statement;

import grammar.QLParser;

import java.util.ArrayList;

public class Condition implements Statement {

    private String condition;
    private ArrayList<Statement> statementList = new ArrayList<>();

    public Condition(QLParser.IfStatementContext ctx) {
        this.condition = ctx.condition.getText();
    }

    public boolean addStatement(Statement statement) {
        return this.statementList.add(statement);
    }

    @Override
    public void debugPrint() {
        System.out.println("Condition statement: " + this.condition);
        System.out.println("Contains : " + this.statementList.size() + " statements:");
        if (this.statementList.size() > 0) {
            for (Statement statement: this.statementList) {
                statement.debugPrint();
            }
        }
    }
}
