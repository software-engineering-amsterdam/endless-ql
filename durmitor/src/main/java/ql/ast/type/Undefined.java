package ql.ast.type;

public class Undefined extends Type {
    
    private String name = "";
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setValue(String value) {
        // TODO Auto-generated method stub
        
    }

    @Override public String toString() { return "undefined"; }
    @Override public boolean equals(Type t) { return t.isUndefined(); }
    @Override public boolean isUndefined() { return true; }
}
