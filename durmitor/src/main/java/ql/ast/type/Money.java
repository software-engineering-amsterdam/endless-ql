package ql.ast.type;

public class Money extends Type {
    
    private String value;
    private long whole;
    private byte part;
    
    public Money(long whole,byte part) {
        this.whole = whole;
        this.part = part;
        this.value = whole + "." + part;
    }
    
    public Money() { 
        this.whole = 0;
        this.part = 0;
        this.value = "0.00";
    }
}
