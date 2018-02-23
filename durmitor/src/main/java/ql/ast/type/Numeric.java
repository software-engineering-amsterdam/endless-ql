package ql.ast.type;

public class Numeric extends Type {

    private String name = "";
    
    @Override
    public String toString() {
        return "numeric";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(String value) {
        // TODO Auto-generated method stub
        
    }
}
