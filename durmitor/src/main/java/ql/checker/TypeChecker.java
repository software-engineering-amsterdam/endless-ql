package ql.checker;

import java.util.ArrayList;
import java.util.List;

import ql.ast.form.Form;
import ql.helpers.symboltable.SymbolTable;
import ql.visitors.StatementVisitorDuplicateIdentifiers;
import ql.visitors.StatementVisitorDuplicateLabels;
import ql.visitors.StatementVisitorInvalidOperands;
import ql.visitors.StatementVisitorNonBooleanConditions;
import ql.visitors.StatementVisitorUndefinedReferences;

public class TypeChecker {

    private Form form;
    private SymbolTable symbolTable;
    private List<String> errors;
    private List<String> warnings;
    
    
    public TypeChecker(Form form) {
        
        this.form           = form;
        this.symbolTable    = new SymbolTable(form);
        this.errors         = new ArrayList<String>();
        this.warnings       = new ArrayList<String>();
    }
    
    public void checkForm() {
        checkReferencesToUndefinedQuestions();
        checkIdentifiersWithMultipleTypes();
        checkNonBooleanConditions();
        checkInvalidOperands();
        // TODO: Check cyclic dependencies
        checkDuplicateLabels();
    }
    
    public void checkReferencesToUndefinedQuestions()
    {
        form.getBlock().accept(new StatementVisitorUndefinedReferences(symbolTable, errors));
    }
    
    public void checkIdentifiersWithMultipleTypes()
    {
        form.getBlock().accept(new StatementVisitorDuplicateIdentifiers(symbolTable, errors));
    }
    
    public void checkNonBooleanConditions()
    {
        form.getBlock().accept(new StatementVisitorNonBooleanConditions(symbolTable, errors));
    }
    
    public void checkInvalidOperands()
    {
        form.getBlock().accept(new StatementVisitorInvalidOperands(symbolTable, errors));
    }
    
    public void checkDuplicateLabels()
    {
        form.getBlock().accept(new StatementVisitorDuplicateLabels(warnings));
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
