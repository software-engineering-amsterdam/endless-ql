package ql.visitors;

import java.util.List;

import ql.ast.expression.Expression;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Type;
import ql.helpers.symboltable.SymbolTable;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorNonBooleanConditions implements StatementVisitor {
    
    private SymbolTable symbolTable;
    private List<String> errors;

    public StatementVisitorNonBooleanConditions(SymbolTable symbolTable, List<String> errors) {
        
        this.symbolTable    = symbolTable;
        this.errors         = errors;
    }
    
    private void checkCondition(Expression condition) {
        
        Type conditionType = condition.accept(new ExpressionVisitorType(symbolTable));
        
        if(!conditionType.isBoolean())
        {
            errors.add("Non-boolean condition ["+condition+"] found at "+condition.getLocation());
        }
    }
    
    @Override
    public void visit(Block stmts) {
        for(Statement stmt : stmts.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        checkCondition(stmt.getCondition());
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        checkCondition(stmt.getCondition());
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {
    }

    @Override
    public void visit(ComputedQuestion stmt) {
    }
}
