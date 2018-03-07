package ql.ast.form;

import ql.ast.QLNode;
import ql.ast.expression.Identifier;
import ql.ast.statement.Block;
import ql.gui.GUI;
import ql.helpers.Observer;
import ql.visitors.ASTtoGUI;

public class Form extends QLNode implements Observer {
    
    private Identifier id;
    private Block block;
    private GUI gui = new GUI(); 
    
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
    
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void update() {
        gui.resetFrame();
        block.accept(new ASTtoGUI(gui));
    }
}