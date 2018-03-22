package ql.visitors.checker.checkers;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Add;
import ql.ast.expression.And;
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
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.visitors.interfaces.ExpressionVisitor;

public class ExpressionVisitorIdentifier implements ExpressionVisitor<List<Identifier>> {
    
    private List<Identifier> idents;

    public ExpressionVisitorIdentifier() {
        
        this.idents = new ArrayList<Identifier>();
    }
    
    @Override
    public List<Identifier> visit(Negation expr) {
        expr.getOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Negative expr) {
        expr.getOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Positive expr) {
        expr.getOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(And expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Or expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Greater expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(GreaterEqual expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Less expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(LessEqual expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Equal expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(NotEqual expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Add expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Subtract expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Multiply expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Divide expr) {
        expr.getFirstOperand().accept(this);
        expr.getSecondOperand().accept(this);
        return idents;
    }

    @Override
    public List<Identifier> visit(Identifier expr) {
        idents.add(expr);
        return idents;
    }

    @Override
    public List<Identifier> visit(BoolLiteral expr) {
        return idents;
    }

    @Override
    public List<Identifier> visit(IntLiteral expr) {
        return idents;
    }

    @Override
    public List<Identifier> visit(StrLiteral expr) {
        return idents;
    }

    @Override
    public List<Identifier> visit(DateLiteral expr) {
        return idents;
    }

    @Override
    public List<Identifier> visit(DecimalLiteral expr) {
        return idents;
    }

    @Override
    public List<Identifier> visit(MoneyLiteral expr) {
        return idents;
    }

    @Override
    public List<Identifier> visit(UndefinedLiteral expr) {
        return idents;
    }
}
