package Nodes.Term;

public class Boolean extends Term {
    private boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getBoolean() { return value; }
}
