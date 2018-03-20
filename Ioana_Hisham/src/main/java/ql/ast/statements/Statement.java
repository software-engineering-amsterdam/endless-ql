package ql.ast.statements;

import ql.ast.Node;
import ql.visitors.StatementVisitor;

public abstract class Statement extends Node {

    public Statement(int lineNumber) {
        super(lineNumber);
    }

    public abstract <T> T accept(StatementVisitor<T> visitor);
}
