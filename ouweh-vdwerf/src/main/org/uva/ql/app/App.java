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
        try {
            Logger logger = Logger.getGlobal();
            LogManager.getLogManager().reset();
            logger.addHandler(new LogHandler());

            byte[] a = Files.readAllBytes(Paths.get("input/default.ql"));
            String input = new String(a);

            ASTBuilder builder = new ASTBuilder();
            Form form = builder.buildAST(input);

            Validator validator = new Validator();
            validator.execute(form);

            FormEvaluator formEvaluator = new FormEvaluator(new ExpressionTable(), new StatementTable(), new ValueTable(), form);
            
            GUIHandler guiHandler = new GUIHandler(formEvaluator);

        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main (String [] args) {
        new App();
    }
}
