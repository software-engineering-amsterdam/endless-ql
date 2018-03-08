package Nodes.Operator;

import Nodes.Term.Boolean;
import Nodes.Term.Float;
import Nodes.Term.Integer;
import Nodes.Term.Term;

public class ArithmeticOperation extends Operator{

    public ArithmeticOperation(String value){
        super(value);
    }

    public Float calculate(Term left, Term right) throws UnsupportedOperationException{

        // Check if the sides are comparable types
        if(!((left instanceof Integer || left instanceof Float) && (right instanceof Integer || right instanceof Float))){
            throw new UnsupportedOperationException();
        }

        switch (this.getValue()){
            case "+": return new Float(left.getValue() + right.getValue());
            case "-": return new Float(left.getValue() - right.getValue());
            case "/": return new Float(left.getValue() / right.getValue());
            case "*": return new Float(left.getValue() * right.getValue());
            default: ; //TODO throw error
        }
        // TODO throw error if code gets here
        return new Float(-1);
    }


}
