package models.ast.elements;

import java.util.ArrayList;

public class ConditionBlock extends Block {
    private ArrayList<Block> blockList;

    public ArrayList<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<Block> blockList) {
        this.blockList = blockList;
    }

    @Override
    public void print() {
        System.out.println("Condition block");
    }
}
