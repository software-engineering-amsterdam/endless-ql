package Nodes.Operator;

import Nodes.Term.Boolean;
import Nodes.Term.Term;

public class EqualOperation extends  Operator{

    public EqualOperation(String value){
        super(value);
    }

    public Boolean calculate(Term left, Term right) {

        if(this.getValue().equals("=="))
            return new Boolean(left.getValue() == right.getValue());

        if(this.getValue().equals("!="))
            return new Boolean(left.getValue() != right.getValue());

        // TODO throw error if code gets here
        return new Boolean(left.getTerm() == right.getTerm());
    }
}
