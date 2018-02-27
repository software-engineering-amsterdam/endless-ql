package classes;

public class CodeBlock {
    private int blockStart;
    private int blockEnd;

    public CodeBlock(int blockStart, int blockEnd) {
        this.blockStart = blockStart;
        this.blockEnd = blockEnd;
    }

    public int getBlockStart() {
        return blockStart;
    }

    public int getBlockEnd() {
        return blockEnd;
    }
}
