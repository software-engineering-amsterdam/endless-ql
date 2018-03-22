package org.uva.ql.validation.checker;

import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterChecker extends Checker {

    private SymbolTable symbolTable;
    private List<Parameter> parameters;

    public ParameterChecker(SymbolTable symbolTable, List<Parameter> parameters) {
        this.symbolTable = symbolTable;
        this.parameters = parameters;
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        for (Parameter parameter : parameters) {
            if (!symbolTable.contains(parameter.getID())) {
                String message = String.format("Referenced parameter is not declared: %s", parameter);

                result.addError(message);
                logger.severe(message);
            }
        }

        return result;
    }
}
