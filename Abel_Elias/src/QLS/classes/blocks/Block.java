package QLS.classes.blocks;

import javax.sound.sampled.Line;
import java.util.List;

public class Block {

    private List<Element> blockElements;

    public Block(List<Element> blockElements) {
        this.blockElements = blockElements;
    }

    public List<Element> getBlockElements() {
        return blockElements;
    }

    public void setBlockElements(List<Element> blockElements) {
        this.blockElements = blockElements;
    }
}
