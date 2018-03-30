package ql.gui.controller;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.ast.ASTBuilder;
import ql.ast.model.Form;
import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.gui.model.FormModel;
import ql.gui.view.ErrorMessageView;
import ql.gui.view.WindowView;
import ql.gui.view.panels.FormPanel;
import ql.logic.collectors.CollectConditionsVisitor;
import ql.logic.collectors.CollectQuestionsVisitor;
import ql.logic.collectors.CollectReferencesVisitor;
import ql.logic.validators.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OpenFileController implements ActionListener {

    WindowView windowView;

    public OpenFileController(WindowView windowView) {
        this.windowView = windowView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            String selectedFilePath = selectedFile.getAbsolutePath();

            // Build form
            Form form = BuildFormController.buildForm(selectedFilePath, windowView);

            // if no form -> stop
            if (form == null)
                return;

            // Collecting data

            // Collect all references from all expressions in the form (both: assignments and conditions)
            CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
            List<VariableReference> references = collectReferencesVisitor.getVariableReferences(form);

            // Collect all questions
            CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
            List<Question> questions = collectQuestionsVisitor.getQuestions(form);

            // Collect all conditions
            CollectConditionsVisitor collectConditionsVisitor = new CollectConditionsVisitor();
            List<Expression> conditions = collectConditionsVisitor.getConditions(form);

            // Collect questions with their references for cyclic dependency validator
            HashMap<Question, List<VariableReference>> questionsMap = collectQuestionsVisitor.getQuestionsMap(form);

            // Validators
            Validator[] validators = new Validator[]{
                    new VariablesReferencesValidator(questions, references),
                    new TypesValidator(conditions, questions),
                    new QuestionsDuplicationValidator(questions),
                    new ConditionsValidator(conditions, questions),
                    new QuestionsDependencyValidator(questionsMap),
                    new QuestionLabelsValidator(questions)
            };


            for (Validator validator : validators) {
                if (!validator.validate()) {
                    if (validator.criticalErrorOccured()) {
                        ErrorMessageView.showErrorDialog(windowView, "Validation error", validator.getMessage());
                        return;
                    } else {
                        ErrorMessageView.showWarningDialog(windowView, "Validation warning", validator.getMessage());
                    }
                }
            }

            // hide the initial frame
            this.windowView.hide();

            // proceed with actual form modelling and rendering

            FormModel formModel = new FormModel(form);

            FormController formController = new FormController(formModel);

            JPanel formPanel = new FormPanel(form.getName(), formModel, formController.getFormView());

            this.windowView.setMainPanel(formPanel);
            this.windowView.formatAndShow();


        }
    }
}
