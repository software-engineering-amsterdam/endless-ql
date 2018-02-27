package ql.visitors;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryOperator;
import ql.ast.expression.BoolLiteral;
import ql.ast.expression.DateLiteral;
import ql.ast.expression.DecimalLiteral;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.IntLiteral;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.MoneyLiteral;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.StrLiteral;
import ql.ast.expression.Subtract;
import ql.ast.expression.UnaryOperator;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class ExpressionVisitorType implements ExpressionVisitor<Type> {

    private List<String> errors = new ArrayList<String>();
    
    public ExpressionVisitorType() {
        errors = new ArrayList<String>();
    }
    
    public ExpressionVisitorType(List<String> errors) {
        this.errors = errors;
    }

    public void check(UnaryOperator op, Type opType, Type type  )
    {
        if(type.isUndefined())
        {
            errors.add("Illegal operation ["+op.getOperator()+opType+"] at "+op.getLocation());
        }
    }
    
    public void check(BinaryOperator op, Type firstOpType, Type secondOpType, Type type  )
    {
        if(type.isUndefined())
        {
            errors.add("Illegal operation ["+firstOpType+" "+op.getOperator()+" "+secondOpType+"] at "+op.getLocation());
        }
    }
    
    @Override
    public Type visit(Negation expr) {
        
        Type opType = expr.getOperand().accept(this);
        Type type   = opType.toValue().negation().getType();
        
        check(expr,opType,type);
        
        return type;
    }

    @Override
    public Type visit(Negative expr) {
        Type opType = expr.getOperand().accept(this);
        Type type   = opType.toValue().negative().getType();
        
        check(expr,opType,type);
        
        return type;
    }

    @Override
    public Type visit(Positive expr) {
        Type opType = expr.getOperand().accept(this);
        Type type   = opType.toValue().positive().getType();
        
        check(expr,opType,type);
        
        return type;
    }

    @Override
    public Type visit(And expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().and(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}
    
    @Override
    public Type visit(Or expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().or(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(Greater expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().greater(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(GreaterEqual expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().greaterEqual(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(Less expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().less(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(LessEqual expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().lessEqual(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(Equal expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().equal(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(NotEqual expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().notEqual(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(Add expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type type           = firstOpType.toValue().add(secondOpType.toValue()).getType();
        
        check(expr,firstOpType,secondOpType,type);
        
        return type;
    }

    @Override
    public Type visit(Subtract expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().subtract(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(Multiply expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().multiplyBy(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(Divide expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
		Type type           = firstOpType.toValue().divideBy(secondOpType.toValue()).getType();

		check(expr,firstOpType,secondOpType,type);

		return type;
	}

    @Override
    public Type visit(Identifier expr) {
        return expr.getType();
    }

    @Override
    public Type visit(BoolLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(IntLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(StrLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(DateLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(DecimalLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(MoneyLiteral expr) {
        return expr.getType();
    }
}
