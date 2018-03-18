package org.uva.gui;

import org.uva.app.LogHandler;
import org.uva.gui.widgets.QuestionWidget;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.ExpressionEvaluator;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.evaluator.StyleEvaluator;

import javax.swing.*;
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

    public GUIHandler(FormEvaluator formEvaluator, StyleEvaluator styleEvaluator) {
        this.formEvaluator = formEvaluator;
        this.styleEvaluator = styleEvaluator;

        this.questionChangeListener = new QuestionChangeListener(this);
        this.expressionEvaluator = new ExpressionEvaluator();

        initializeFrame();
        checkForErrors();

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
            QuestionReference reference = styleEvaluator.getQuestionReference(question);
            Value value = formEvaluator.getValueById(question.getId());

            // TODO apply styling to widget
            QuestionWidget widget = widgetFactory.makeWidget(question, value, !formEvaluator.questionIsCalculated(question));

            this.styleEvaluator.setWidget(reference, widget);

            Boolean condition = true;
            if (formEvaluator.questionHasCondition(question)) {
                condition = ((BooleanValue) this.expressionEvaluator.evaluateExpression(
                        question.getId(),
                        this.formEvaluator.getConditionById(question.toString()),
                        this.formEvaluator.getValueTable()))
                        .getValue();
            }
            if (condition) {
                this.styleEvaluator.setVisible(reference);
            }
        }
        this.tabbedPane = new JTabbedPane();
        frame.add(styleEvaluator.getLayout(this.tabbedPane));

        setFocus(this.lastChangedQuestion);
        frame.setVisible(true);
    }

    private void setFocus(Question question) {
        if (question != null) {
            this.tabbedPane.setSelectedComponent(this.styleEvaluator.getPage(question));
        }
    }

    private void initializeFrame() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setSize(500, 300);
        this.frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }

    private void checkForErrors() {
        Logger logger = Logger.getGlobal();
        logger.info("Hallo");
        LogHandler handler = (LogHandler) logger.getHandlers()[0];
        List<LogRecord> logs = handler.getLogs(Level.WARNING);
        if (logs.size() > 0) {
            for (LogRecord logRecord : logs) {
                JOptionPane.showMessageDialog(frame, logRecord.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }


}
