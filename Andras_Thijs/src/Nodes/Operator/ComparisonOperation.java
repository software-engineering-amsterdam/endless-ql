package Nodes.Operator;

import Nodes.Term.*;
import QLExceptions.*;

public class ComparisonOperation extends Operator{

    public ComparisonOperation(String value){
        super(value);
    }

    public QLBoolean calculate(Term left, Term right) throws TypeException, SyntaxException {

        // Check if the sides are comparable types
        if(!((left instanceof QLInteger || left instanceof QLFloat) && (right instanceof QLInteger || right instanceof QLFloat))){
            throw new TypeException();
        }

        switch (this.getValue()){
            case ">=": return new QLBoolean(left.getValue() >= right.getValue());
            case "<=": return new QLBoolean(left.getValue() <= right.getValue());
            case ">": return new QLBoolean(left.getValue() > right.getValue());
            case "<": return new QLBoolean(left.getValue() < right.getValue());
        }

        throw new SyntaxException("Invalid comparison operator", this);
    }
}
