package org.uva.ql.validation;

import org.uva.ql.ast.Conditional;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.Question;

import org.uva.ql.ast.Statement;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator implements StatementVisitor<Void, String> {

    private SymbolTable table;
    private List<Question> questions;

    public Validator() {
        this.table = new SymbolTable();
        this.questions = new ArrayList<>();
    }

    public void execute (Form form) {
        // Collect all questions from the form & add them to the list.
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        // Add relevant data to the symbol table.
        for (Question question : this.questions) {
            System.out.println(question);
            this.table.add(question.getName(), question.getType());
        }

        // Check if all question phrases & ID's are unique.
        findDuplicates();

        // TODO do type checking.
        //requires form and type table
        TypeChecker typeChecker = new TypeChecker();


        // TODO dependencies

        // TODO expressions

        System.out.println("Total number of questions: " + table.size());
    }

    public boolean findDuplicates() {
        Set<String> questionIDs = new HashSet<>();
        Set<String> questionTexts = new HashSet<>();

        for (Question question : this.questions) {
            if (!questionIDs.add(question.getName())) {
                System.out.printf("\nWARNING: (var could be overwritten) question name %s already exists.", question.getName());
            }

            if (!questionTexts.add(question.getContent())) {
                System.out.printf("\nWARNING: Question content %s already exists.", question.getContent());
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
}
