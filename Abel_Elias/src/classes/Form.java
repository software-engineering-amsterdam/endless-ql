package classes;

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
}
