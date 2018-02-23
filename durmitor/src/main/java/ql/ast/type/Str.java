package ql.ast.type;

public class Str extends Type {

    private String name = "";
    private String value = "";
    
    @Override
    public String toString() {
        return "string";
    }

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
}
