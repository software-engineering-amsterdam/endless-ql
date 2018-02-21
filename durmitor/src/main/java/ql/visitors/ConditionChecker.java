package ql.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ql.ast.expression.Expression;
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Type;
import ql.visitors.interfaces.StatementVisitor;

public class ConditionChecker implements StatementVisitor {
    
    private Form form;
    private Map<String,Type> symbolTable;
    private List<String> errors;
    private List<Expression> conditions;

    public ConditionChecker(Form form, Map<String,Type> symbolTable, List<String> errors) {
        
        this.form           = form;
        this.symbolTable    = symbolTable;
        this.errors         = errors;
        this.conditions     = new ArrayList<Expression>();
    }
    
    public List<Expression> getInvalidConditions() {
        
        visit(form.getBlock());
        
        return conditions;
    }
    
    private void check(Expression condition) {
        
        Type type = condition.getType(symbolTable);
        
        if(!type.isBoolean())
        {
            conditions.add(condition);
            errors.add("Non-boolean condition [ " + condition + " ] with type [ " + type + " ] found @ "+ condition.getLocation());
        }
    }
    
    @Override
    public void visit(Block stmts) {
        for(Statement stmt : stmts.getStatements()) {
           stmt.accept(this);
        }
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
    public void visit(AnswerableQuestion stmt) {
    }

    @Override
    public void visit(ComputedQuestion stmt) {
    }
}
