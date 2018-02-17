package ql.visitors;

import java.util.List;

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
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.visitors.interfaces.ExpressionVisitor;

public class ReferenceCollector extends AbstractCollector<Identifier> implements ExpressionVisitor {
    
    @Override
    public List<Identifier> collect(Form form) {
        
        collection.clear();
        
        visit(form.getBlock());
        
        return collection;
    }
    
    @Override
    public void visit(Negation expr) {
        expr.getExpression().accept(this);
    }

    @Override
    public void visit(Negative expr) {
        expr.getExpression().accept(this);
    }

    @Override
    public void visit(Positive expr) {
        expr.getExpression().accept(this);
    }

    @Override
    public void visit(And expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Or expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Greater expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(GreaterEqual expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Less expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(LessEqual expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Equal expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(NotEqual expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Add expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Subtract expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Multiply expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Divide expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);
    }

    @Override
    public void visit(Identifier expr) {
        collection.add(expr);
    }

    @Override
    public void visit(BoolLiteral expr) {}

    @Override
    public void visit(IntLiteral expr) {}

    @Override
    public void visit(StrLiteral expr) {}

    @Override
    public void visit(DateLiteral expr) {}

    @Override
    public void visit(DecimalLiteral expr) {}

    @Override
    public void visit(MoneyLiteral expr) {}

    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        stmt.getCondition().accept(this);
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        stmt.getCondition().accept(this);
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {}
    
    @Override
    public void visit(ComputedQuestion stmt) {
        stmt.getExpression().accept(this);
    }
}
