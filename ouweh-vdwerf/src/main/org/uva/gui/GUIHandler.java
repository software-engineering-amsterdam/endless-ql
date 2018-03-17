package org.uva.gui;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.ExpressionEvaluator;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.gui.widgets.QuestionWidget;
import org.uva.app.LogHandler;
import org.uva.qls.evaluator.StyleEvaluator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
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

    public void onQuestionChange(String id, Value value) {
        formEvaluator.addOrUpdateValue(id, value);
        generateGUI();
    }

    private void generateGUI() {
        frame.getContentPane().removeAll();

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Title", panel1);
        frame.add(tabbedPane);
        // TODO build pages and sections

        WidgetFactory widgetFactory = new WidgetFactory(this.questionChangeListener, this.styleEvaluator);
        this.formEvaluator.evaluateAllExpressions(this.expressionEvaluator);

        for (Question question : formEvaluator.getQuestionsAsList()) {
            Value value = formEvaluator.getValueById(question.getId());
            QuestionWidget widget = widgetFactory.makeWidget(question, value, !formEvaluator.questionIsCalculated(question));
            // TODO apply styling to widget

            if (formEvaluator.questionHasCondition(question)) {
                BooleanValue expressionValue = (BooleanValue) this.expressionEvaluator.evaluateExpression(
                        question.getId(),
                        this.formEvaluator.getConditionById(question.toString()),
                        this.formEvaluator.getValueTable()
                );
                widget.setVisible(expressionValue.getValue());
            }
            //TODO add to correct section
            panel1.add(widget);
        }
        frame.setVisible(true);
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

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        TitledBorder border = BorderFactory.createTitledBorder("Title border");
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
        border.setBorder(lineBorder);
        panel.setBorder(border);
        return panel;
    }

}
