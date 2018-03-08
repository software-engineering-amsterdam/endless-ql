package ast.model.statements;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

import java.util.ArrayList;

public class IfStatement extends Statement {

    private Expression condition;
    private ArrayList<Statement> statementList = new ArrayList<>();
    private ArrayList<Statement> elseStatementList = new ArrayList<>();

    public IfStatement(Expression condition, MetaInformation metaInformation) {
        super(metaInformation);
        this.condition = condition;
    }

    public boolean addStatement(Statement statement) {
        return this.statementList.add(statement);
    }

    public boolean addElseStatement(Statement statement) {
        return this.elseStatementList.add(statement);
    }

    public Expression getCondition() {
        return condition;
    }

    public ArrayList<Statement> getElseStatementList() {
        return elseStatementList;
    }

    public ArrayList<Statement> getStatementList() {
        return statementList;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

}
