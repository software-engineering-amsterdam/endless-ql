package Nodes.Term;

public class String extends Term {
    private java.lang.String value;

    public String(java.lang.String value) {
        this.value = value;
    }

    @Override
    public java.lang.String getString() { return value; }
}
