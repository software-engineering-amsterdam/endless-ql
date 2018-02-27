package classes;

public class TreeNode {
    private CodeBlock codeBlock;

    public TreeNode() {
        this.codeBlock = null;
    }

    public TreeNode(CodeBlock codeBlock) {
        this.codeBlock = codeBlock;
    }

    public CodeBlock getCodeBlock() {
        return this.codeBlock;
    }

}
