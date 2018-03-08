package Nodes.Term;

public class Variable extends Term {
    private String name;
    private Term value;


    public Variable(String name){
        this.name = name;
    }

    public Variable(String name, Term value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }

    public Term getTerm() { return value; }

    public float getValue(){
        return this.value.getValue();
    }
}
