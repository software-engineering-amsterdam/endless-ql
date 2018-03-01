package Nodes.Term;

public class String extends Term {
    private String value;

    public String(String value) {
        this.value = value;
    }

    @Override
    public String getString() { return value; }
}
