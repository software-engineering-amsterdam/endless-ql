package org.uva.ql.validation;

import org.uva.ql.ast.Form;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Validator {

    private ArrayList<Checker> checkers = new ArrayList<>();
    private final LogHandler handler;

    public Validator(Form form) {
        QuestionChecker questionChecker = new QuestionChecker(form);
        checkers.add(questionChecker);

        ParameterChecker parameterChecker = new ParameterChecker(form, questionChecker.getSymbolTable());
        checkers.add(parameterChecker);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterChecker.getExpressions());
        checkers.add(dependencyChecker);

        TypeChecker typeChecker = new TypeChecker(form, questionChecker.getSymbolTable());
        checkers.add(typeChecker);

        this.handler = (LogHandler) Logger.getGlobal().getHandlers()[0];
    }

    public void run() {
        for (Checker checker : checkers) {
            if(handler.hasErrors()) {
                break;
            }
            checker.runCheck();
        }
    }
}
