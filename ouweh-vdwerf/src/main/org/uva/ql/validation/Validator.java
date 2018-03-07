package org.uva.ql.validation;

import org.uva.ql.ast.Form;

public class Validator {

    private final QuestionChecker questionChecker;
    private final ParameterChecker parameterChecker;
    private final DependencyChecker dependencyChecker;
    private final TypeChecker typeChecker;

    public Validator(Form form) {
        this.questionChecker = new QuestionChecker(form);
        this.parameterChecker = new ParameterChecker(form, questionChecker.getSymbolTable());
        this.dependencyChecker = new DependencyChecker(parameterChecker.getExpressions());
        this.typeChecker = new TypeChecker(form, questionChecker.getSymbolTable());
    }

    public void run() {
        questionChecker.runCheck();
        parameterChecker.runCheck();
        dependencyChecker.runCheck();
        typeChecker.runCheck();
    }
}
