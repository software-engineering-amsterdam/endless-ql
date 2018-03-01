package ql.visitors.checker.checkers;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryOperator;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.Subtract;
import ql.ast.expression.UnaryOperator;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Type;
import ql.exceptions.IllegalOperation;
import ql.exceptions.QLException;
import ql.visitors.interfaces.ExpressionVisitor;

public class ExpressionVisitorInvalidOperands implements ExpressionVisitor<Type> {

    private List<QLException> errors;
    
    public ExpressionVisitorInvalidOperands() {
        errors = new ArrayList<QLException>();
    }
    
    public ExpressionVisitorInvalidOperands(List<QLException> errors) {
        this.errors = errors;
    }

    public Type check(UnaryOperator op) {
        
        Type resultType = op.getType();
        
        if(resultType.isUndefined()) errors.add(new IllegalOperation(op));
        
        return resultType;
    }
    
    public Type check(BinaryOperator op) {
        
        Type resultType     = op.getType();
        
        if(resultType.isUndefined()) errors.add(new IllegalOperation(op));
        
        return resultType;
    }

    @Override
    public Type visit(Negation expr) {
        return check(expr);
    }

    @Override
    public Type visit(Negative expr) {
        return check(expr);
    }

    @Override
    public Type visit(Positive expr) {
        return check(expr);
    }

    @Override
    public Type visit(And expr) {
        return check(expr);
    }

    @Override
    public Type visit(Or expr) {
        return check(expr);
    }

    @Override
    public Type visit(Greater expr) {
        return check(expr);
    }

    @Override
    public Type visit(GreaterEqual expr) {
        return check(expr);
    }

    @Override
    public Type visit(Less expr) {
        return check(expr);
    }

    @Override
    public Type visit(LessEqual expr) {
        return check(expr);
    }

    @Override
    public Type visit(Equal expr) {
        return check(expr);
    }

    @Override
    public Type visit(NotEqual expr) {
        return check(expr);
    }

    @Override
    public Type visit(Add expr) {
        return check(expr);
    }

    @Override
    public Type visit(Subtract expr) {
        return check(expr);
    }

    @Override
    public Type visit(Multiply expr) {
        return check(expr);
    }

    @Override
    public Type visit(Divide expr) {
        return check(expr);
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

    @Override
    public Type visit(UndefinedLiteral expr) {
        return expr.getType();
    }
}
