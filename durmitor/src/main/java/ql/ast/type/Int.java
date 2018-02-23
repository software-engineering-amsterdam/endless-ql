package ql.ast.type;

public class Int extends Numeric {
    
    private String name = "";
    private int value = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    @Override public String toString() { return "integer"; }
    @Override public boolean equals(Type t) { return t.isInteger(); }
    @Override public boolean isInteger() { return true; }
}
