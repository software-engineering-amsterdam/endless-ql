package ql.ast.form;

import ql.ast.QLNode;
import ql.ast.expression.Identifier;
import ql.ast.statement.Block;

public class Form extends QLNode {
    
    private Identifier id;
    private Block block;
    
    public Form(Identifier id, Block block) {
        this.id = id;
        this.block = block;
    }
    
    public Identifier getId() {
        return id;
    }

    public Block getBlock() {
        return block;
    }

    public String toString() {
        return "form " + id.toString() + " " + block.toString();
    }
}