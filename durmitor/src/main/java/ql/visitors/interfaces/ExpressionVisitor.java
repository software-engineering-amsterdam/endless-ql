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
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;

public interface ExpressionVisitor {

    //Unary boolean
    void visit(Negation expr);
    
    //Unary arithmetic
    void visit(Negative expr);
    void visit(Positive expr);
    
    //Binary boolean
    void visit(And expr);
    void visit(Or expr);
    void visit(Greater expr);
    void visit(GreaterEqual expr);
    void visit(Less expr);
    void visit(LessEqual expr);
    void visit(Equal expr);
    void visit(NotEqual expr);
    
    //Binary arithmetic
    void visit(Add expr);
    void visit(Subtract expr);
    void visit(Multiply expr);
    void visit(Divide expr);
    
    //Primary
    void visit(Identifier expr);
    void visit(BoolLiteral expr);
    void visit(IntLiteral expr);
    void visit(StrLiteral expr);
    void visit(DateLiteral expr);
    void visit(DecimalLiteral expr);
    void visit(MoneyLiteral expr);
    
    //Statement
    void visit(Block block);
    void visit(IfThen stmt);
    void visit(IfThenElse stmt);
    void visit(AnswerableQuestion stmt);
    void visit(ComputedQuestion stmt);
}
