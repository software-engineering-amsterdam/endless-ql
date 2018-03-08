package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.ql.ast.Form;
import org.uva.ql.parsing.ASTBuilder;

import java.io.File;
import java.io.FileReader;

public class QuestionCheckerTest {

    private Form form;
    private QuestionChecker questionChecker;

    @Before
    public void setUp() {
        ASTBuilder parser = new ASTBuilder();
        FileReader reader = new FileReader();


        questionChecker = new QuestionChecker(form);
    }

    @Test
    public void runCheck() {
    }


    @Test
    public void getSymbolTable() {
    }
}