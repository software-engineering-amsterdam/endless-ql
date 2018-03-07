package org.uva.ql.validation;

import org.uva.ql.ast.*;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class QuestionChecker implements StatementVisitor<Void, String> {

    private SymbolTable symbolTable;
    private List<Question> questions;

    QuestionChecker(){
        this.symbolTable = new SymbolTable();
        this.questions = new ArrayList<>();
    }

    public boolean findDuplicates(Form form) {
        Set<String> questionIDs = new HashSet<>();
        Set<String> questionTexts = new HashSet<>();

        // Collect all questions from the form & add them to the list.
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        // Add relevant data to the symbol symbolTable.
        for (Question question : this.questions) {
            this.symbolTable.add(question.getName(), question.getType());
        }

        for (Question question : this.questions) {
            if (!questionIDs.add(question.getName())) {
                Logger.getGlobal().info("WARNING: (var could be overwritten) question name "+question.getName()+" already exists");
            }

            if (!questionTexts.add(question.getContent())) {
                Logger.getGlobal().info("WARNING: Question content "+question.getContent()+" already exists");
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

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}
