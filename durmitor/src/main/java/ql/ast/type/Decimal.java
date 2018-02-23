package ql.ast.type;

public class Decimal extends Numeric {
    
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override public String toString() { return "decimal"; }
    @Override public boolean equals(Type t) { return t.isDecimal(); }
    @Override public boolean isDecimal() { return true; }
}
