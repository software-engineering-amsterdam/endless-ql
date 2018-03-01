package models.ast.elements.block;

import grammar.QLParser;

import java.util.ArrayList;

public class Condition implements Block {

    private String condition;
    private ArrayList<Block> blockList = new ArrayList<>();

    public Condition(QLParser.IfBlockContext ctx) {
        this.condition = ctx.condition.getText();
    }

    public boolean addBlock(Block block) {
        return this.blockList.add(block);
    }

    @Override
    public void print() {
        System.out.println("Condition block: " + this.condition);
        System.out.println("Contains : " + this.blockList.size() + " blocks:");
        if (this.blockList.size() > 0) {
            for (Block block: this.blockList) {
                block.print();
            }
        }
    }
}
