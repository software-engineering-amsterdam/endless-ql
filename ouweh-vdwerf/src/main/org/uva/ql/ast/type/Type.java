package main.org.uva.ql.ast.type;

import main.org.uva.ql.ast.TreeNode;
import main.org.uva.ql.visitor.TypeVisitor;

public abstract class Type extends TreeNode {

    public abstract <T, C> T accept(TypeVisitor<T, C> visitor, C context);
}
