package org.uva.ql.app;

import org.uva.ql.ast.Form;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.gui.GUIHandler;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.LogHandler;
import org.uva.ql.validation.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class App {

    private App() {
        Logger logger = Logger.getGlobal();
        LogManager.getLogManager().reset();
        logger.addHandler(new LogHandler());


        String input = readFile("input/default.ql");

        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        Validator validator = new Validator(form);
        validator.run();

        FormEvaluator formEvaluator = new FormEvaluator(new ExpressionTable(), new StatementTable(), new ValueTable(), form);

        GUIHandler guiHandler = new GUIHandler(formEvaluator);

    }

    private String readFile(String location) {
        try {
            byte[] a = Files.readAllBytes(Paths.get(location));
            return new String(a);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        new App();
    }
}
