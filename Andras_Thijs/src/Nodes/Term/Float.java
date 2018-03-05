package Nodes.Term;

public class Float extends Term {
    private float value;

    public Float(float value) {
        this.value = value;
    }

    @Override
    public float getFloat() { return value; }
}
