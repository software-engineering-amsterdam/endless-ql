package org.uva.ql.validation;

import org.uva.ql.ast.Form;

public class Validator {

    public void execute(Form form) {
        QuestionChecker questionChecker = new QuestionChecker(form);
        questionChecker.runCheck();

        SymbolTable symbolTable = questionChecker.getSymbolTable();

        ParameterChecker parameterChecker = new ParameterChecker(form, symbolTable);
        parameterChecker.runCheck();

        DependencyChecker dependencyChecker = new DependencyChecker(parameterChecker.getExpressions());
        dependencyChecker.runCheck();

        TypeChecker typeChecker = new TypeChecker(form, symbolTable);
        typeChecker.runCheck();
    }
}
