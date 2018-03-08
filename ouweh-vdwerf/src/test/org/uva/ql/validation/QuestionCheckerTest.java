package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.ql.app.InputHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.parsing.ASTBuilder;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class QuestionCheckerTest {

    private QuestionChecker questionChecker;

    @Before
    public void setUp() {
        Logger logger = Logger.getGlobal();
        LogManager.getLogManager().reset();
        logger.addHandler(new LogHandler());

        String input = new InputHandler().readFile("input/test/questionChecker.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);
        questionChecker = new QuestionChecker(form);
    }

    @Test
    public void runCheck() {
        LogHandler logHandler = (LogHandler) Logger.getGlobal().getHandlers()[0];
        questionChecker.runCheck();
        assertEquals(true, logHandler.hasWarnings());
    }

    @Test
    public void getSymbolTable() {
        final int EXPECTED_ENTRIES = 3;
        final String EXPECTED_TABLE = "{v1=BooleanType, v2=IntegerType, v3=StringType}";

        SymbolTable symbolTable = questionChecker.getSymbolTable();
        assertEquals(EXPECTED_ENTRIES, symbolTable.size());
        assertEquals(EXPECTED_TABLE, symbolTable.toString());
    }
}