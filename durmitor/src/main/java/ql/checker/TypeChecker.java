package ql.checker;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Identifier;
import ql.ast.form.Form;
import ql.visitors.StatementVisitorDependencies;
import ql.visitors.StatementVisitorDuplicateIdentifiers;
import ql.visitors.StatementVisitorDuplicateLabels;
import ql.visitors.StatementVisitorInvalidOperands;
import ql.visitors.StatementVisitorNonBooleanConditions;
import ql.visitors.StatementVisitorUndefinedReferences;

public class TypeChecker {

    private Form form;
    private List<String> errors;
    private List<String> warnings;
    
    
    public TypeChecker(Form form) {
        
        this.form           = form;
        this.errors         = new ArrayList<String>();
        this.warnings       = new ArrayList<String>();
    }
    
    public void checkForm() {
        checkReferencesToUndefinedQuestions();
        checkIdentifiersWithMultipleTypes();
        checkNonBooleanConditions();
        checkInvalidOperands();
        checkCyclicDependencies();
        checkDuplicateLabels();
    }
    
    public void checkReferencesToUndefinedQuestions()
    {
        form.getBlock().accept(new StatementVisitorUndefinedReferences(errors));
    }
    
    public void checkIdentifiersWithMultipleTypes()
    {
        form.getBlock().accept(new StatementVisitorDuplicateIdentifiers(errors));
    }
    
    public void checkNonBooleanConditions()
    {
        form.getBlock().accept(new StatementVisitorNonBooleanConditions(errors));
    }
    
    public void checkInvalidOperands()
    {
        form.getBlock().accept(new StatementVisitorInvalidOperands(errors));
    }
    
    public void checkCyclicDependencies()
    {
        Dependencies dependencies = new Dependencies();
        
        form.getBlock().accept(new StatementVisitorDependencies(dependencies));
        
        List<List<Identifier>> cyclicDependencies = dependencies.getCyclicDependencies();
        
        for(List<Identifier> cd : cyclicDependencies)
        {
            Identifier first    = cd.get(0);
            String error        = "Cyclic dependency found on ["+first.getName()+"] from ";
            error              += first.getName() + " at " + first.getLocation();
            
            for(int i = 1; i < cd.size(); i++)
            {
                error += " to "+cd.get(i).getName() + " at " + cd.get(i).getLocation();
            }
            
            errors.add(error);
        }
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
