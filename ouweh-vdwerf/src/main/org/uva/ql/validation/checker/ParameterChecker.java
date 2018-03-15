package org.uva.ql.validation.checker;

import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterChecker extends Checker {

    private SymbolTable symbolTable;
    private Map<String, List<Parameter>> parameterMapping;

    public ParameterChecker(SymbolTable symbolTable, Map<String, List<Parameter>> parameterMapping) {
        this.symbolTable = symbolTable;
        this.parameterMapping = parameterMapping;
    }

    @Override
    public void runCheck() {
        for (HashMap.Entry<String, List<Parameter>> entry : parameterMapping.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                if (!symbolTable.contains(parameter.getID())) {
                    logger.severe(String.format("Referenced parameter does not exist: %s", parameter));
                }
            }
        }
    }
}
