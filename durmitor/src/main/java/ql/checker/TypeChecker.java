package ql.checker;

import java.util.LinkedList;
import java.util.List;

import ql.ast.expression.Identifier;
import ql.ast.form.Form;
import ql.exceptions.CyclicDependency;
import ql.helpers.Dependencies;
import ql.helpers.MessageBag;
import ql.visitors.checker.checkers.StatementVisitorDependencies;
import ql.visitors.checker.checkers.StatementVisitorDuplicateIdentifiers;
import ql.visitors.checker.checkers.StatementVisitorDuplicateLabels;
import ql.visitors.checker.checkers.StatementVisitorInvalidOperands;
import ql.visitors.checker.checkers.StatementVisitorNonBooleanConditions;
import ql.visitors.checker.checkers.StatementVisitorUndefinedReferences;

public class TypeChecker {

    private Form form;
    private MessageBag errors;
    private MessageBag warnings;
    
    public TypeChecker(Form form) {
        
        this.form           = form;
        this.errors         = new MessageBag();
        this.warnings       = new MessageBag();
    }
    
    public TypeChecker(Form form, MessageBag errors) {
        
        this.form           = form;
        this.errors         = errors;
        this.warnings       = new MessageBag();
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
        
        List<LinkedList<Identifier>> cyclicDependencies = dependencies.getCyclicDependencies();
        
        for(LinkedList<Identifier> cd : cyclicDependencies) errors.add(new CyclicDependency(cd));
    }
    
    public void checkDuplicateLabels()
    {
        form.getBlock().accept(new StatementVisitorDuplicateLabels(warnings));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public MessageBag getErrors() {
        return errors;
    }
    
    public boolean hasWarnings() {
        return !errors.isEmpty();
    }
    
    public MessageBag getWarnings() {
        return warnings;
    }
}
