package Nodes.Operator;

import Nodes.Term.Boolean;
import Nodes.Term.Term;
import QLExceptions.*;

public class EqualOperation extends  Operator{

    public EqualOperation(String value){
        super(value);
    }

    public Boolean calculate(Term left, Term right) throws SyntaxException, TypeException {
        if(this.getValue().equals("=="))
            return new Boolean(left.getValue() == right.getValue());

        if(this.getValue().equals("!="))
            return new Boolean(left.getValue() != right.getValue());

        //TODO: Typechecking!

        throw new SyntaxException();
    }
}
