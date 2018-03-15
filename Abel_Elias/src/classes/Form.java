package classes;

public class Form{
    private String id;
    private Block block;

    public Form(String id, Block block) {
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
