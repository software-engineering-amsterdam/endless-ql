package org.uva.gui;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.ExpressionEvaluator;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.validation.ValidationResult;
import org.uva.qls.evaluator.StyleEvaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
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

            // Get current answer/value of this question
            Value value = formEvaluator.getValueById(question.getId());

            // make a widget
            QuestionWidget widget = widgetFactory.makeWidget(question, value, !formEvaluator.questionIsCalculated(question));

            // Register it at the style evaluator
            this.styleEvaluator.setWidget(question, widget);

            if (formEvaluator.questionIsVisible(question, this.expressionEvaluator)) {
                this.styleEvaluator.setVisible(question);
            }
        }
        frame.add(styleEvaluator.getLayout());

        this.frame.add(getSaveButton());

        setFocus(this.lastChangedQuestion);
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
        this.frame.setVisible(true);
    }

    private JPanel getSaveButton() {
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BorderLayout());

        JButton saveButton = new JButton("Submit");
        saveButton.addActionListener(e -> {
            saveAndQuit();
        });

        savePanel.add(saveButton, BorderLayout.SOUTH);
        return savePanel;
    }

    private void saveAndQuit() {
        int dialogResult = JOptionPane.showConfirmDialog(this.frame, "Would you like to save and quit?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
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
