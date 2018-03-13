package classes.form;

import classes.Block;
import classes.CodeBlock;
import classes.TreeNode;

public class Form extends TreeNode {
    private String id;
    private Block block;

    public Form(String id, Block block, CodeBlock code) {
        super(code);
        this.id = id;
        this.block = block;
    }

    public String getId() {
        return this.id;
    }

    public Block getBlock() {
        return this.block;
    }

    public void accept(FormVisitor formVisitor) {
        formVisitor.visitForm(this);
    }
}
