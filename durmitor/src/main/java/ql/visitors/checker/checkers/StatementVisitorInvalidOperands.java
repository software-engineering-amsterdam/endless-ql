package ql.visitors.checker.checkers;

import java.util.List;

import ql.ast.expression.Expression;
import ql.ast.expression.literal.Literal;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Type;
import ql.exceptions.Inconvertible;
import ql.exceptions.QLException;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorInvalidOperands implements StatementVisitor {
    
    private List<QLException> errors;

    public StatementVisitorInvalidOperands(List<QLException> errors) {
        this.errors = errors;
    }
    
    public void check(Expression expr) {
        expr.accept(new ExpressionVisitorType(errors));
    }
    
    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        check(stmt.getCondition());
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        check(stmt.getCondition());
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {}

    @Override
    public void visit(ComputedQuestion stmt) {
        
        Type computationType = stmt.getComputation().accept(new ExpressionVisitorType(errors));
        
        if(!computationType.isUndefined())
        {
            if(stmt.getIdentifier().getType().parse(Literal.create(computationType)).isUndefined())
            {
                errors.add(new Inconvertible(stmt.getIdentifier().getType(), computationType));
            }
        }
    }
}
