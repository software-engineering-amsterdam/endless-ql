package QLS.classes.blocks;

import javax.sound.sampled.Line;
import java.util.List;

public class Block {
    private List<LineInBlock> blockElements;

    public Block(List<LineInBlock> blockElements) {
        this.blockElements = blockElements;
    }
}
