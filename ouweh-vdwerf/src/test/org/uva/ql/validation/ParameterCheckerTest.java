package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.ql.ast.CalculatedQuestion;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.Statement;
import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.validation.checker.ParameterChecker;
import org.uva.ql.validation.collector.ParameterMapping;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParameterCheckerTest {


    @Test
    public void runCheckEmptySymbolTable() {
        List<Statement> statements;
        statements = new ArrayList<>(Arrays.asList(
                new CalculatedQuestion(
                        "name",
                        "content",
                        new IntegerType(),
                        new Parameter("parameter")
                )
        ));
        Form form = new Form("form", statements);

        ParameterChecker parameterChecker = new ParameterChecker(new SymbolTable(form), new ParameterMapping(form).getParameterMapping());

        assertTrue(parameterChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckInSymbolTable() {
        List<Statement> statements;
        statements = new ArrayList<>(Arrays.asList(
                new CalculatedQuestion(
                        "name",
                        "content",
                        new IntegerType(),
                        new Parameter("parameter")
                )
        ));
        Form form = new Form("form", statements);

        SymbolTable symbolTable = new SymbolTable(form);
        symbolTable.add("parameter", new BooleanType());
        ParameterChecker parameterChecker = new ParameterChecker(symbolTable, new ParameterMapping(form).getParameterMapping());
        parameterChecker.runCheck();

        assertFalse(parameterChecker.runCheck().hasWarnings());
    }
}