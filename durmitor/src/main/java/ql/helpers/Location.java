package ql.helpers;

public class Location {

    private int line;
    private int column;
    private int offset;
    private int length;
    
    public Location() {
        line    = 1;
        column  = 0;
        offset  = 0;
        length  = 0;
    }
    
    public Location(int line, int column, int offset, int length) {
        this.line   = line;
        this.column = column;
        this.offset = offset;
        this.length = length;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public int getLength() {
        return length;
    }
    
    public boolean equals(Location location) {
        return  offset == location.getOffset() && 
                length == location.getLength();
    }
    
    public boolean contains(Location location) {
        if(offset > location.getOffset()) return false;
        return (offset + length) >= (location.getOffset() + location.getLength());
    }
    
    public boolean precedes(Location location) {
        if(equals(location)) return false;
        if(contains(location)) return true;
        return offset <= location.getOffset();
    }
    
    public String toString() {
        return "line "+line+":"+column;
    }
}
