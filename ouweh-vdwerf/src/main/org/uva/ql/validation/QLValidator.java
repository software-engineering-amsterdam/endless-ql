package org.uva.ql.validation;

import org.uva.app.LogHandler;
import org.uva.ql.ast.*;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.logging.Logger;

public class QLValidator implements StatementVisitor<Void, String> {

    private final LogHandler handler;
    private Form form;


    private ArrayList<Question> questions = new ArrayList<>();
    private SymbolTable symbolTable = new SymbolTable();

    public QLValidator(Form form) {
        this.form = form;
        this.handler = (LogHandler) Logger.getGlobal().getHandlers()[0];

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        for (Question question : this.questions) {
            this.symbolTable.add(question.getName(), question.getType());
        }
    }

    private ArrayList<Checker> getCheckers() {
        ArrayList<Checker> checkers = new ArrayList<>();

        QuestionChecker questionChecker = new QuestionChecker(this.questions);
        checkers.add(questionChecker);

        ParameterChecker parameterChecker = new ParameterChecker(this.form, this.symbolTable);
        checkers.add(parameterChecker);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterChecker.getExpressions());
        checkers.add(dependencyChecker);

        TypeChecker typeChecker = new TypeChecker(this.form, this.symbolTable);
        checkers.add(typeChecker);

        return checkers;
    }

    public void run() {
        for (Checker checker : getCheckers()) {
            if (handler.hasErrors()) {
                break;
            }
            checker.runCheck();
        }
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
        this.questions.add(question);
        this.symbolTable.add(question.getName(), question.getType());
        return null;
    }

    public ArrayList<Question> getQuestions () {
        return this.questions;
    }
}
