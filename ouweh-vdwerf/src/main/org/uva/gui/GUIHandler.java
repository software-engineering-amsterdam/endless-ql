package org.uva.gui;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.ExpressionEvaluator;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.validation.ValidationResult;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.evaluator.StyleEvaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class GUIHandler {

    private JFrame frame;

    private FormEvaluator formEvaluator;
    private StyleEvaluator styleEvaluator;

    private QuestionChangeListener questionChangeListener;
    private ExpressionEvaluator expressionEvaluator;

    private Question lastChangedQuestion = null;
    private JTabbedPane tabbedPane = null;

    public GUIHandler(FormEvaluator formEvaluator, StyleEvaluator styleEvaluator, ValidationResult validationResult) {
        this.formEvaluator = formEvaluator;
        this.styleEvaluator = styleEvaluator;

        this.questionChangeListener = new QuestionChangeListener(this);
        this.expressionEvaluator = new ExpressionEvaluator();

        initializeFrame();
        checkForErrors(validationResult);

        // Initialize formEvaluator
        this.formEvaluator.evaluateAllExpressions(this.expressionEvaluator);

        generateGUI();
    }

    public void onQuestionChange(Question question, Value value) {
        formEvaluator.addOrUpdateValue(question.getId(), value);
        this.lastChangedQuestion = question;
        generateGUI();
    }

    private void generateGUI() {
        frame.getContentPane().removeAll();

        styleEvaluator.generateSections();

        WidgetFactory widgetFactory = new WidgetFactory(this.questionChangeListener, this.styleEvaluator);
        this.formEvaluator.evaluateAllExpressions(this.expressionEvaluator);

        for (Question question : formEvaluator.getQuestionsAsList()) {
            Value value = formEvaluator.getValueById(question.getId());

            QuestionWidget widget = widgetFactory.makeWidget(question, value, !formEvaluator.questionIsCalculated(question));

            this.styleEvaluator.setWidget(question, widget);

            Boolean condition = true;
            if (formEvaluator.questionHasCondition(question)) {
                condition = ((BooleanValue) this.expressionEvaluator.evaluateExpression(
                        question.getId(),
                        this.formEvaluator.getConditionById(question.toString()),
                        this.formEvaluator.getValueTable()))
                        .getValue();
            }
            if (condition) {
                this.styleEvaluator.setVisible(question);
            }
        }
        frame.add(styleEvaluator.getLayout());

        this.frame.add(initializeSaveButton());

        setFocus(this.lastChangedQuestion);
        frame.setVisible(true);
        frame.pack();
    }

    private void setFocus(Question question) {
        if (question != null) {
            this.styleEvaluator.setFocus(question);
        }
    }

    private void initializeFrame() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setSize(750, 600);
        this.frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }

    private JPanel initializeSaveButton(){
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BorderLayout());

        JButton saveButton = new JButton("Submit");
        saveButton.addActionListener(e -> {saveAndQuit();});

        savePanel.add(saveButton, BorderLayout.SOUTH);
        return savePanel;
    }

    private void saveAndQuit(){
        int dialogResult = JOptionPane.showConfirmDialog (this.frame, "Would you like to save and quit?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            this.formEvaluator.saveState();
            this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }

    }

    private void checkForErrors(ValidationResult validationResult) {
        Logger logger = Logger.getGlobal();
        logger.info("Hallo");

        if (validationResult.hasErrors() || validationResult.hasWarnings()) {
            for (String warning : validationResult.getWarnings()) {
                JOptionPane.showMessageDialog(frame, warning, "Error", JOptionPane.ERROR_MESSAGE);
            }

            for (String error : validationResult.getErrors()) {
                JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.ERROR_MESSAGE);
            }

            this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }


}
