package classes;

import classes.statements.Statement;

import java.util.ArrayList;
import java.util.List;

public class Block extends TreeNode {
    boolean constraint;
    List<Statement> statements;

    public Block(CodeBlock code, boolean constraint) {
        super(code);
        List<Statement> statements = new ArrayList<>();
    }
}
