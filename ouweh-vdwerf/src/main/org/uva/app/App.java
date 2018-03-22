package org.uva.app;

import org.uva.gui.GUIHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.QLValidator;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.collector.QuestionContext;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.evaluator.StyleEvaluator;
import org.uva.qls.validation.QLSValidator;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class App {

    private App() {
        Logger logger = Logger.getGlobal();
        LogManager.getLogManager().reset();

        String input = new IOHandler().readFile("input/original.ql");
//        String input = new IOHandler().getUserInput("ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        String qlsInput = new IOHandler().readFile("input/default.qls");
//        String input = new IOHandler().getUserInput("qls");
        QLSBuilder QLSBuilder = new QLSBuilder();
        Stylesheet stylesheet = QLSBuilder.buildAST(qlsInput);

        QLValidator validator = new QLValidator(form);
        ValidationResult validationResult = validator.run();

        QLSValidator qlsValidator = new QLSValidator(form, stylesheet);
        validationResult = validationResult.merge(qlsValidator.run());

        FormEvaluator formEvaluator = new FormEvaluator(new ExpressionTable(), new StatementTable(), new ValueTable(), form);
        StyleEvaluator styleEvaluator = new StyleEvaluator();
        styleEvaluator.setStylesheet(stylesheet);

        GUIHandler guiHandler = new GUIHandler(formEvaluator, styleEvaluator, validationResult);
    }

    public static void main(String[] args) {
        new App();
    }
}
