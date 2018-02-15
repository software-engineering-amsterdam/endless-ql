package ParseObjects;

public class Form {
    private String name;
    private Block block;

    public Form(){
        setName("");
        setBlock(null);
    }

    public Form(String s, Block b){
        setName(s);
        setBlock(b);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

}
