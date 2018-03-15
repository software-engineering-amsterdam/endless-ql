package org.uva.ql.validation.checker;

import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.validation.SymbolTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterChecker extends Checker {

    private SymbolTable symbolTable;
    private Map<String, List<Parameter>> expressions;

    public ParameterChecker(SymbolTable symbolTable, Map<String, List<Parameter>> expressions) {
        this.symbolTable = symbolTable;
        this.expressions = expressions;
    }

    @Override
    public void runCheck() {
        for (HashMap.Entry<String, List<Parameter>> entry : expressions.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                System.out.println(parameter);
                if (!symbolTable.contains(parameter.getID())) {
                    logger.severe(String.format("Referenced parameter does not exist: %s", parameter));
                }
            }
        }
    }
}
