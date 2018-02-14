package main.org.uva.ql.ast;

import java.util.List;

public class Form extends TreeNode {

    private List<Statement> statements;

    public Form(List<Statement> statements) {
        this.statements = statements;
    }

}
