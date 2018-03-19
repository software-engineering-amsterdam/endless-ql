package QLS.classes;

import java.util.List;

public class Section {
    private String id;
    private List<Block> blocks;

    public Section(String id, List<Block> blocks) {
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
