package ast.model;

import ast.model.statements.Statement;
import ast.visitors.ASTNodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class Form extends ASTNode {

    private String name;
    private List<Statement> statementList = new ArrayList<>();

    public Form(String name, MetaInformation metaInformation) {
        super(metaInformation);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public boolean addStatement(Statement statement) {
        return this.statementList.add(statement);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
