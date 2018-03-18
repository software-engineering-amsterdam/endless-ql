package QLS.classes;

import QL.classes.Block;

public class Stylesheet{
    private String id;
    private Block block;

    public Stylesheet(String id, Block block) {
        this.id = id;
        this.block = block;
    }

    public String getId() {
        return this.id;
    }

    public Block getBlock() {
        return this.block;
    }
}