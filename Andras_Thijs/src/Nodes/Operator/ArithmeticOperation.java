package Nodes.Operator;

import Nodes.Term.Float;
import Nodes.Term.Integer;
import Nodes.Term.Term;
import QLExceptions.*;

public class ArithmeticOperation extends Operator{

    public ArithmeticOperation(String value){
        super(value);
    }

    /**
     * Implements a arithmetic operator (+, -, /, * and ^)
     * @param left left hand side of the operator
     * @param right right hand side of the operator
     * @return A new intermediary Float term with the result of the calculation
     * @throws SyntaxException when the value of this Operator is not +, -, /, * or ^
     */
    public Float calculate(Term left, Term right) throws SyntaxException, TypeException {
        // Check if the sides are comparable types
        if(!((left instanceof Integer || left instanceof Float) && (right instanceof Integer || right instanceof Float))){
            throw new TypeException();
        }

        switch (this.getValue()){
            case "+": return new Float(left.getValue() + right.getValue());
            case "-": return new Float(left.getValue() - right.getValue());
            case "/": return new Float(left.getValue() / right.getValue());
            case "*": return new Float(left.getValue() * right.getValue());
            case "^": return new Float((float) Math.pow(left.getValue(),right.getValue()));
        }

        throw new SyntaxException("Invalid arithmetic operator", this);
    }
}
