package ql.ast.type;

public class Decimal extends Numeric {

    private String name = "";
    
    @Override
    public String toString() {
        return "decimal";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
