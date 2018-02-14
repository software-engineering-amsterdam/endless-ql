package ql.ast.type;

public class Date extends Type {
    
    private java.util.Date value;
    
    public Date() {
        this.value = new java.util.Date();
    }
}
