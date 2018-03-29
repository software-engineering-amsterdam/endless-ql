package QL.AST;

public abstract class ASTNode {
    private final int lineNumber;

    public ASTNode(int lineNumber){
        this.lineNumber = lineNumber;
    }

    public int getLineNumber(){
        return lineNumber;
    }
}
