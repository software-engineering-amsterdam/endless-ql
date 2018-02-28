package org.uva.ql.validation;

import org.uva.ql.ast.*;

import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator implements StatementVisitor<Void, String> {

    private SymbolTable symbolTable;
    private List<Question> questions;

    public Validator() {
        this.symbolTable = new SymbolTable();
        this.questions = new ArrayList<>();
    }

    public void execute (Form form) {
        // Collect all questions from the form & add them to the list.
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        // Add relevant data to the symbol symbolTable.
        for (Question question : this.questions) {
            System.out.println(question);
            this.symbolTable.add(question.getName(), question.getType());
        }

        // Check if all question phrases & ID's are unique.
        findDuplicates();

        ParameterChecker parameterChecker = new ParameterChecker(form, symbolTable);

        // TODO do type checking.
        //requires form and type symbolTable
        TypeChecker typeChecker = new TypeChecker();


        // TODO dependencies

        // TODO expressions

        System.out.println("Total number of questions: " + symbolTable.size());
    }

    public boolean findDuplicates() {
        Set<String> questionIDs = new HashSet<>();
        Set<String> questionTexts = new HashSet<>();

        for (Question question : this.questions) {
            if (!questionIDs.add(question.getName())) {
                System.out.printf("WARNING: (var could be overwritten) question name %s already exists\n", question.getName());
            }

            if (!questionTexts.add(question.getContent())) {
                System.out.printf("WARNING: Question content %s already exists\n", question.getContent());
            }
        }
        // TODO return something useful here.
        return false;
    }

    @Override
    public Void visit(Question question, String context) {
        this.questions.add(question);

        return null;
    }

    @Override
    public Void visit(Conditional conditional, String context) {

        for (Statement statement : conditional.getIfSide()) {
            statement.accept(this, null);
        }

        for (Statement statement : conditional.getElseSide()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visit(CalculatedQuestion question, String context) {
        questions.add(question);
        symbolTable.add(question.getName(), question.getType());
        return null;
    }
}
