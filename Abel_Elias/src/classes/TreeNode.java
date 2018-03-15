package classes;

public abstract class TreeNode {
    private CodeBlock codeBlock;

    public TreeNode(CodeBlock codeBlock) {
        this.codeBlock = codeBlock;
    }

    public CodeBlock getCodeBlock() {
        return this.codeBlock;
    }
}
