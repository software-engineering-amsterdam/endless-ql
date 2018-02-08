package ql.ast;

public abstract class AstNode {

    public final static int FORM = 1;
    public final static int BLOCK = 2;

    final Ast ast;
    private int nodeType = 0;
    private AstNode parent = null;

    AstNode(Ast ast) {
        if (ast == null) {
            throw new IllegalArgumentException();
        }

        this.ast = ast;
        setNodeType(getNodeType0());
    }

    abstract int getNodeType0();

    public final int getNodeType() {
        return nodeType;
    }

    private void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public AstNode getParent() {
        return parent;
    }

    public void setParent(AstNode parent) {
        this.parent = parent;
    }
}
