package classes;

import classes.form.FormVisitor;
import classes.statements.Statement;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

public class Block extends TreeNode {
    private boolean constraint;
    private List<Statement> statements;

    public Block(CodeBlock code, List<Statement> statements, boolean constraint) {
        super(code);
        this.statements = new ArrayList<Statement>();
        this.constraint = constraint;
    }


    public void accept(FormVisitor formVisitor) {
        formVisitor.visitBlock(this);
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
