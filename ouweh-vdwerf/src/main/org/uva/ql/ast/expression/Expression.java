package main.org.uva.ql.ast.expression;

import main.org.uva.ql.ast.TreeNode;
import main.org.uva.ql.visitor.ExpressionVisitor;

public abstract class Expression extends TreeNode {

    public abstract <T, C> T accept(ExpressionVisitor<T, C> visitor, C context);
}
