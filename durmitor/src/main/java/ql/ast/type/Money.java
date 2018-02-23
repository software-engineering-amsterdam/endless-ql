package ql.ast.type;

public class Money extends Type {

    private String name = "";
    private long value = 0L;
    
    @Override
    public String toString() {
        return "money";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }
    
    @Override
    public void setValue(String value) {
        this.value = Long.parseLong(value);
    }

    public void setValue(long value) {
        this.value = value;
    }
}
