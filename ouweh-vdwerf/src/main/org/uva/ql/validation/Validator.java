package org.uva.ql.validation;

import org.uva.ql.ast.Form;

public class Validator {

    public Validator() {
    }

    public void execute (Form form) {
        QuestionChecker questionChecker = new QuestionChecker();
        questionChecker.findDuplicates(form);

        SymbolTable symbolTable = questionChecker.getSymbolTable();

        ParameterChecker parameterChecker = new ParameterChecker(form, symbolTable);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterChecker.getExpressions());
        dependencyChecker.execute();

        TypeChecker typeChecker = new TypeChecker(form, symbolTable);
        typeChecker.execute();
    }
}
