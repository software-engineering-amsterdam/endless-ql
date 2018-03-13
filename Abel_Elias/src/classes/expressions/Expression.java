package classes.expressions;

import classes.CodeBlock;
import classes.TreeNode;

public abstract class Expression extends TreeNode {

    public Expression(CodeBlock fragment) {
        super(fragment);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);


};