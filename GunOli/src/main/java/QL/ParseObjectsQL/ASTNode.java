package QL.ParseObjectsQL;

public class ASTNode {
    private final int line;

    public ASTNode(int line){ this.line = line; }

    public int getLine(){
        return line;
    }
}
