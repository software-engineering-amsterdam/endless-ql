package Nodes.Term;

public class Boolean extends Term {
    private boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }

    public boolean getBoolean() { return value; }

    @Override
    public float getValue() {
        return (this.value ? 1 : 0);
    }
}
