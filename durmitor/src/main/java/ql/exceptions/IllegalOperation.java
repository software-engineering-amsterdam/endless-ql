package ql.exceptions;

import ql.ast.expression.BinaryOperator;
import ql.ast.expression.UnaryOperator;

public class IllegalOperation extends QLException {

    public IllegalOperation(UnaryOperator op) {
        
        message     = "Illegal operation ["+op.getOperator()+op.getOperand().getType()+"]";
        location    = op.getLocation();
    }
    
    public IllegalOperation(BinaryOperator op) {
        
        message     = "Illegal operation ["+op.getFirstOperand().getType()+" "+op.getOperator()+" "+op.getSecondOperand().getType()+"]";
        location    = op.getLocation();
    }
}
