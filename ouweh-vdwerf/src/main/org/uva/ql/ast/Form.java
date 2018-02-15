package main.org.uva.ql.ast;

import java.util.List;

public class Form extends TreeNode {

    private final String id;
    private List<Statement> statements;

    public Form(String id, List<Statement> statements) {
        this.id = id;
        this.statements = statements;
    }

    @Override
    public String toString() {
        String form = String.format("Form: %s", this.id);
        for (Statement statement : this.statements) {
            form += String.format("\n\t%s", statement.toString());
        }
        return form;
    }
}
