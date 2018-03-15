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

public class TypeCheckerTest {

    private LogHandler logHandler;

    @Before
    public void setUp() {
        Logger logger = Logger.getGlobal();
        LogManager.getLogManager().reset();
        this.logHandler = new LogHandler();
        logger.addHandler(logHandler);
    }

    @Test
    public void correctTypesRunCheck() {
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
        symbolTable.add("parameter", new IntegerType());


        TypeChecker typeChecker = new TypeChecker(form, symbolTable);
        typeChecker.runCheck();

        assertFalse(logHandler.hasWarnings());
    }

    @Test
    public void incorrectTypesRunCheck() {
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


        TypeChecker typeChecker = new TypeChecker(form, symbolTable);
        typeChecker.runCheck();

        assertTrue(logHandler.hasWarnings());
    }
}