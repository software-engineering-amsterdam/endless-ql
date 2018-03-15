package Nodes.Term;

public class QLInteger extends Term {
    private int value;

    public QLInteger(int value) {
        this.value = value;
    }

    public int getInteger() { return value; }

    public float getValue() {return (float) value;}
}
