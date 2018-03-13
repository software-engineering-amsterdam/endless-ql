package classes.statements;

import classes.CodeBlock;
import classes.TreeNode;
import classes.expressions.Expression;
import parsing.AST_Visitor;

public abstract class Statement extends TreeNode {
    public Statement(CodeBlock code) {
        super(code);
    }
    public abstract void accept(StatementVisitor visitor);
    public abstract void accept(StatementVisitor visitor, Expression expression);

}
