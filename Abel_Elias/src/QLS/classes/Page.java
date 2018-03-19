package QLS.classes;

import java.util.List;

public class Page {

    private String id;
    private List<Block> blocks;

    public Page(String id, List<Block> blocks) {
        this.id = id;
        this.blocks = blocks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

}
