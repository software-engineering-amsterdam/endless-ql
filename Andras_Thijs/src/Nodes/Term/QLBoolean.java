package Nodes.Term;

public class QLBoolean extends Term {
    private boolean value;

    public QLBoolean(boolean value) {
        this.value = value;
    }

    public boolean getBoolean() { return value; }
}
