package ast.model;

import ast.model.statement.Statement;
import ast.visitors.ASTNodeVisitor;

import java.util.ArrayList;

public class Form implements VisitableASTNode {

    private String name;
    private ArrayList<Statement> statementList = new ArrayList<>();

    public Form(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(ArrayList<Statement> statementList) {
        this.statementList = statementList;
    }

    public boolean addStatement(Statement statement) {
        return this.statementList.add(statement);
    }

    public void debugPrint() {
        System.out.println("Form: " + this.name);
        for (Statement statement : this.statementList) {
            statement.debugPrint();
        }
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
