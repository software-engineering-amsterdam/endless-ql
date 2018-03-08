package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.ql.app.InputHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.parsing.ASTBuilder;

public class QuestionCheckerTest {

    private Form form;
    private QuestionChecker questionChecker;

    @Before
    public void setUp() {
        String input = new InputHandler().readFile("input/test.questionChecker.ql");
        ASTBuilder builder = new ASTBuilder();
        form = builder.buildAST(input);
        questionChecker = new QuestionChecker(form);
    }

    @Test
    public void runCheck() {
    }


    @Test
    public void getSymbolTable() {
    }
}