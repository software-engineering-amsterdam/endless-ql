package QL.ParseObjectsQL;

import sun.tools.jstat.Token;

public class ASTNode {
    private final int line;

    public ASTNode(int line){ this.line = line; }

    public int getLine(){
        return line;
    }
}
