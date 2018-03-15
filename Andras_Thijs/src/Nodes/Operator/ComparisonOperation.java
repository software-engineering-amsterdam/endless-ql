package Nodes.Operator;

import Nodes.Term.Boolean;
import Nodes.Term.Float;
import Nodes.Term.Integer;
import Nodes.Term.Term;
import Nodes.Type;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

public class ComparisonOperation extends Operator{

    public ComparisonOperation(String value){
        super(value);
    }

    public Boolean calculate(Term left, Term right) throws TypeException, SyntaxException {

        // Check if the sides are comparable types
        if(!((left instanceof Integer || left instanceof Float) && (right instanceof Integer || right instanceof Float))){
            throw new TypeException();
        }

        switch (this.getValue()){
            case ">=": return new Boolean(left.getValue() >= right.getValue());
            case "<=": return new Boolean(left.getValue() <= right.getValue());
            case ">": return new Boolean(left.getValue() > right.getValue());
            case "<": return new Boolean(left.getValue() < right.getValue());
        }

        throw new SyntaxException("Invalid comparison operator", this);
    }
}
