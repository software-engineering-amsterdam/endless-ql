package ql.visitors.interfaces;

import ql.ast.expression.Add;
import ql.ast.expression.And;
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

public interface ExpressionVisitor<T> {

    //Unary boolean
    T visit(Negation expr);
    
    //Unary arithmetic
    T visit(Negative expr);
    T visit(Positive expr);
    
    //Binary boolean
    T visit(And expr);
    T visit(Or expr);
    T visit(Greater expr);
    T visit(GreaterEqual expr);
    T visit(Less expr);
    T visit(LessEqual expr);
    T visit(Equal expr);
    T visit(NotEqual expr);
    
    //Binary arithmetic
    T visit(Add expr);
    T visit(Subtract expr);
    T visit(Multiply expr);
    T visit(Divide expr);
    
    //Primary
    T visit(Identifier expr);
    T visit(BoolLiteral expr);
    T visit(IntLiteral expr);
    T visit(StrLiteral expr);
    T visit(DateLiteral expr);
    T visit(DecimalLiteral expr);
    T visit(MoneyLiteral expr);
}
