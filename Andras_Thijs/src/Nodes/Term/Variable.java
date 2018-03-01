package Nodes.Term;

public class Variable extends Term {
    private String name;
    private Term value;

    public Variable(String name, Term value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    @Override
    public Term getTerm() { return value; }
}
