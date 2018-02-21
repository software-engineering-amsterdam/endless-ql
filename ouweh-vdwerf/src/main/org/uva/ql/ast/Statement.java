package main.org.uva.ql.ast;

import main.org.uva.ql.visitor.StatementVisitor;

public abstract class Statement extends TreeNode {

    public abstract <T, C> T accept(StatementVisitor<T, C> visitor, C context);
}
