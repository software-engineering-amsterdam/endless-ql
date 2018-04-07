package ql.gui.controller;

import ql.ast.model.Form;
import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.values.Literal;
import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.gui.model.FormModel;
import ql.gui.model.ValidatorsHandler;
import ql.gui.view.ErrorMessageView;
import ql.gui.view.WindowView;
import ql.gui.view.panels.FormPanel;
import ql.logic.collectors.CollectConditionsVisitor;
import ql.logic.collectors.CollectDateLiteralsVisitor;
import ql.logic.collectors.CollectQuestionsVisitor;
import ql.logic.collectors.CollectReferencesVisitor;
import ql.logic.validators.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class OpenFileController implements ActionListener {

    private final WindowView windowView;

    public OpenFileController(WindowView windowView) {
        this.windowView = windowView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            // file path
            File selectedFile = fileChooser.getSelectedFile();
            String selectedFilePath = selectedFile.getAbsolutePath();

            // build form
            Form form = BuildFormController.buildForm(selectedFilePath, windowView);

            // if no form -> stop
            if (form == null)
                return;

            // collectors
            CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
            CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
            CollectConditionsVisitor collectConditionsVisitor = new CollectConditionsVisitor();
            CollectDateLiteralsVisitor collectDateLiteralsVisitor = new CollectDateLiteralsVisitor();

            // collecting data
            List<VariableReference> references = collectReferencesVisitor.getVariableReferences(form);
            List<Question> questions = collectQuestionsVisitor.getQuestions(form);
            List<Expression> conditions = collectConditionsVisitor.getConditions(form);
            HashMap<Question, List<VariableReference>> questionsMap = collectQuestionsVisitor.getQuestionsMap(form);
            List<Literal> dateLiterals = collectDateLiteralsVisitor.getLiterals(form);

            // validators
            Validator[] validators = new Validator[]{
                    new VariablesReferencesValidator(questions, references),
                    new TypesValidator(conditions, questions),
                    new QuestionsDuplicationValidator(questions),
                    new ConditionsValidator(conditions, questions),
                    new QuestionsDependencyValidator(questionsMap),
                    new QuestionLabelsValidator(questions),
                    new DateFormatValidator(dateLiterals)
            };

            if (!ValidatorsHandler.execute(windowView, validators))
                return;

            // all good

            // hide the initial frame
            this.windowView.hide();

            // prepare form MVC
            FormModel formModel = new FormModel(form);
            FormController formController = new FormController(formModel);
            JPanel formPanel = new FormPanel(form.getName(), formModel, formController.getFormView());

            // show
            this.windowView.setMainPanel(formPanel);
            this.windowView.formatAndShow();
        }
    }


}
