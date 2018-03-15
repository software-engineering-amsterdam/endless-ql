package org.uva.ql.validation;

import org.uva.app.LogHandler;
import org.uva.ql.ast.*;
import org.uva.ql.validation.checker.*;
import org.uva.ql.validation.collector.ParameterMapping;
import org.uva.ql.validation.collector.QuestionContext;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QLValidator {

    private final LogHandler handler;
    private Form form;

    public QLValidator(Form form) {
        this.form = form;
        this.handler = (LogHandler) Logger.getGlobal().getHandlers()[0];
    }

    private List<Checker> getCheckers() {
        List<Checker> checkers = new ArrayList<>();

        QuestionChecker questionChecker = new QuestionChecker(new QuestionContext(form).getQuestions());
        checkers.add(questionChecker);
        
        ParameterChecker parameterChecker = new ParameterChecker(new SymbolTable(new QuestionContext(form).getQuestions()), new ParameterMapping(form).getParameterMapping());
        checkers.add(parameterChecker);

        DependencyChecker dependencyChecker = new DependencyChecker(new ParameterMapping(form).getParameterMapping());
        checkers.add(dependencyChecker);

        TypeChecker typeChecker = new TypeChecker(this.form, new SymbolTable(new QuestionContext(form).getQuestions()));
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
}
