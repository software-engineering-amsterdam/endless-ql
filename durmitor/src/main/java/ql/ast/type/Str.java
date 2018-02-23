package ql.ast.type;

public class Str extends Type {
    
    private String name = "";
    private String value = "";
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    @Override public String toString() { return "string"; }
    @Override public boolean equals(Type t) { return t.isString(); }
    @Override public boolean isString() { return true; }
    
    // String as first operand
    @Override public Type add(Type secondOperand) { return secondOperand.addTo(this); }
    
    // String as second operand
    @Override public Type addTo(Str firstOperand) { return new Str(); }
}
