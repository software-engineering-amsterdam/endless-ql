package classes;

import org.antlr.v4.runtime.ParserRuleContext;

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

    public static CodeBlock getCodeBlock(ParserRuleContext context) {
        return new CodeBlock(context.getStart().getLine(), context.getStop().getLine());
    }
}
