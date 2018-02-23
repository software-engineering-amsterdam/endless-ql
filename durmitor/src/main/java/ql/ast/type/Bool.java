package ql.ast.type;

public class Bool extends Type {

    private String name = "";
    private Boolean value = false;
    
    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public boolean isBoolean() { 
        return true; 
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = Boolean.parseBoolean(value);
    }
    
    public void setValue(Boolean value) {
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
