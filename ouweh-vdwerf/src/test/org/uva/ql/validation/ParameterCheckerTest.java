package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.ql.ast.CalculatedQuestion;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.Statement;
import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParameterCheckerTest {

    private LogHandler logHandler;

    @Before
    public void setUp() {
        Logger logger = Logger.getGlobal();
        LogManager.getLogManager().reset();
        this.logHandler = new LogHandler();
        logger.addHandler(logHandler);
    }

    @Test
    public void runCheckEmptySymbolTable() {
        ArrayList<Statement> statements;
        statements = new ArrayList<>(Arrays.asList(
                new CalculatedQuestion(
                        "name",
                        "content",
                        new IntegerType(),
                        new Parameter("parameter")
                )
        ));
        Form form = new Form("form", statements);

        ParameterChecker parameterChecker = new ParameterChecker(form, new SymbolTable());
        parameterChecker.runCheck();

        assertTrue(this.logHandler.hasWarnings());
    }

    @Test
    public void runCheckInSymbolTable() {
        ArrayList<Statement> statements;
        statements = new ArrayList<>(Arrays.asList(
                new CalculatedQuestion(
                        "name",
                        "content",
                        new IntegerType(),
                        new Parameter("parameter")
                )
        ));
        Form form = new Form("form", statements);

        SymbolTable symbolTable = new SymbolTable();
        symbolTable.add("parameter", new BooleanType());
        ParameterChecker parameterChecker = new ParameterChecker(form, symbolTable);
        parameterChecker.runCheck();

        assertFalse(this.logHandler.hasWarnings());
    }
}