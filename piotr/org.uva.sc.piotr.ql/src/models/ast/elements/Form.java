package models.ast.elements;

import java.util.ArrayList;

public class Form {

    private String name;
    private ArrayList<Block> blockList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<Block> blockList) {
        this.blockList = blockList;
    }

    public boolean addBlock(Block block) {
        return this.blockList.add(block);
    }

    public void print() {
        System.out.println("Form: " + this.name);
        for (Block block : this.blockList) {
            block.print();
        }
    }

}
