package ql.exceptions;

import ql.ast.expression.BinaryOperator;
import ql.ast.expression.UnaryOperator;
import ql.ast.type.Type;

public class IllegalOperation extends QLException {

    public IllegalOperation(UnaryOperator op, Type operandType) {
        
        message     = "Illegal operation ["+op.getOperator()+operandType+" ("+op.getOperand()+") ]";
        location    = op.getLocation();
    }
    
    public IllegalOperation(BinaryOperator op, Type firstOperandType, Type secondOperandType) {
        
        message     = "Illegal operation [ "+op.getFirstOperand().getType()+" ["+op.getFirstOperand()+"] ] "+op.getOperator()+" [ "+op.getSecondOperand().getType()+" ["+op.getSecondOperand()+"] ]";
        location    = op.getLocation();
    }
}
