package ql.ast.type;

public class Int extends Numeric {

    private String name = "";
    private int value = 0;
    
    @Override
    public String toString() {
        return "integer";
    }

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
}
