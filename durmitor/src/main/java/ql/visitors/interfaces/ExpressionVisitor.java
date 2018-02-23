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

public interface ExpressionVisitor<E> {

    //Unary boolean
    E visit(Negation expr);
    
    //Unary arithmetic
    E visit(Negative expr);
    E visit(Positive expr);
    
    //Binary boolean
    E visit(And expr);
    E visit(Or expr);
    E visit(Greater expr);
    E visit(GreaterEqual expr);
    E visit(Less expr);
    E visit(LessEqual expr);
    E visit(Equal expr);
    E visit(NotEqual expr);
    
    //Binary arithmetic
    E visit(Add expr);
    E visit(Subtract expr);
    E visit(Multiply expr);
    E visit(Divide expr);
    
    //Primary
    E visit(Identifier expr);
    E visit(BoolLiteral expr);
    E visit(IntLiteral expr);
    E visit(StrLiteral expr);
    E visit(DateLiteral expr);
    E visit(DecimalLiteral expr);
    E visit(MoneyLiteral expr);
}
