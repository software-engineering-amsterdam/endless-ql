package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.ql.app.InputHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.parsing.ASTBuilder;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class QuestionCheckerTest {

    private QuestionChecker questionChecker;

    @Before
    public void setUp() {
        String input = new InputHandler().readFile("input/test.questionChecker.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);
        questionChecker = new QuestionChecker(form);
    }

    @Test
    public void runCheck() {
        LogHandler handler = (LogHandler) Logger.getGlobal().getHandlers()[0];
        questionChecker.runCheck();
        assertEquals(false, handler.hasErrors());
    }


    @Test
    public void getSymbolTable() {
        // TODO final expected shape of the symbol table, and compare with the results.
    }
}