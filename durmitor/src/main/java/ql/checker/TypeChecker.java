package ql.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.expression.Operator;
import ql.ast.form.Form;
import ql.ast.type.Type;
import ql.visitors.ConditionChecker;
import ql.visitors.ConflictingIdChecker;
import ql.visitors.DuplicateLabelChecker;
import ql.visitors.OperandChecker;
import ql.visitors.ReferenceChecker;

public class TypeChecker {

    private List<String> errors;
    private List<String> warnings;
    
    
    public TypeChecker() {
        this.errors     = new ArrayList<String>();
        this.warnings   = new ArrayList<String>();
    }
    
    public List<Identifier> checkIdentifiers(Form form)
    {
        ConflictingIdChecker checker = new ConflictingIdChecker(form, errors);
        
        return checker.getDuplicates();
    }
    
    public List<Identifier> checkReferences(Form form, Map<String,Type> symbolTable)
    {
        ReferenceChecker checker = new ReferenceChecker(form, symbolTable, errors);
        
        return checker.getUndefinedReferences();
    }
    
    public List<Expression> checkConditions(Form form, Map<String,Type> symbolTable)
    {
        ConditionChecker checker = new ConditionChecker(form, symbolTable, errors);
        
        return checker.getInvalidConditions();
    }
    
    public List<Operator> checkOperands(Form form, Map<String,Type> symbolTable)
    {
        OperandChecker checker = new OperandChecker(form, symbolTable, errors);
        
        return checker.getIllegalOperations();
    }
    
    public List<String> checkLabels(Form form)
    {
        DuplicateLabelChecker checker = new DuplicateLabelChecker(form, warnings);
        
        return checker.getDuplicates();
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public boolean hasWarnings() {
        return !errors.isEmpty();
    }
    
    public List<String> getWarnings() {
        return warnings;
    }

    public void printErrors() {
        for(String msg : errors) System.err.println("ERROR: " + msg);
    }
    
    public void printWarnings() {
        for(String msg : warnings) System.out.println("WARNING: " + msg);
    }
}
