package Nodes.Term;

public class Integer extends Term {
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    public int getInteger() { return value; }

    public float getValue() {return (float) value;}
}
