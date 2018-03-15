package Nodes.Operator;

import Nodes.Term.Boolean;
import Nodes.Term.Term;
import Nodes.Term.Variable;

public abstract class Operator {

    private String value;

    public Operator(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //public Term calculate(Variable left, Term right) { return calculate(left.getTerm(), right); }

    //public Term calculate(Term left, Variable right) { return calculate(left, right.getTerm()); }

    public Term calculate(Term left, Term right) { throw new UnsupportedOperationException(); } // TODO: Change to some type error
}
