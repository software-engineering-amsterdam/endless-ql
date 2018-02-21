package ql.ast;

public class QLNode {

    private int line;
    private int column;
    
    public QLNode() {
        this.line   = 0;
        this.column = 0;
    }
    
    public QLNode(int line, int column) {
        this.line   = line;
        this.column = column;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }
    
    public void setLocation(int line, int column) {
        this.line   = line;
        this.column = column;
    }
    
    public String getLocation() {
        return "line " + this.line + ":" + this.column;
    }
}
