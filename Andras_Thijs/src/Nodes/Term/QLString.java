package Nodes.Term;

public class QLString extends Term {
    private String value;

    public QLString(String value) {
        this.value = value;
    }

    public String getString() { return value; }

    public float getValue(){
        return this.value.length();
    }
}
