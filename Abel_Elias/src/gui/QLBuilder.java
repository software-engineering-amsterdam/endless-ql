package gui;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.StringValue;
import QL.classes.values.UndefinedValue;
import QL.classes.values.Value;
import QL.parsing.visitors.FormVisitor;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.listeners.QuestionValueListener;
import gui.questions.QuestionPanel;
import gui.questions.QuestionWidgetCheckBox;
import gui.questions.QuestionWidgetDate;
import gui.questions.text.QuestionWidgetTextInt;
import gui.questions.text.QuestionWidgetTextString;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class QLBuilder {

    private JPanel mainListPanel;

    private LinkedHashMap<String, Question> questionHashMap; //collection of questions
    private LinkedHashMap<String, QuestionPanel> questionPanelHashMap; //collection of questionpanels currently active
    private FormVisitor coreVisitor;
    private QuestionValueListener questionValueListener;

    public LinkedHashMap<String, QuestionPanel> getQuestionPanelHashMap() {
        return questionPanelHashMap;
    }

    public LinkedHashMap<String, Question> getQuestionHashMap() {
        return questionHashMap;

    }

    public QLBuilder(FormVisitor coreVisitor) {
        questionHashMap = coreVisitor.getQuestions();
        this.questionPanelHashMap = new LinkedHashMap<String, QuestionPanel>();
        this.coreVisitor = coreVisitor;
    }

    /**
     * initQuestionPanels() method
     * Initialize the creation of the panels containing
     * the question it's controls through iteration
     */
    public void initQuestionPanels() {

        //Iterate over the questions that were passed
        Iterator<Map.Entry<String, Question>> entries = questionHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Question> entry = entries.next();

            //Extract a single question
            Question question = entry.getValue();
            buildQuestionPanel(question);
        }
    }

    public JPanel createMainListPanel(QuestionValueListener questionValueListener) {
        this.mainListPanel = new JPanel(new GridBagLayout());
        this.questionValueListener = questionValueListener;
        initQuestionPanels();
        return mainListPanel;
    }

    /**
     * buildQuestionPanel() method
     * Build each individual type of question panel and add
     * these to the list panel
     *
     * @param question  the question passed
     */
    private void buildQuestionPanel(Question question) {
        QuestionPanel qPanel;
        String key = question.getText();
        Value value = question.getValue();

        switch (question.getValue().getType()) {
            case Value.STRING:
                qPanel = new QuestionWidgetTextString(key, question);
                break;
            case Value.BOOLEAN:
                qPanel = new QuestionWidgetCheckBox(key, question);
                break;
            case Value.DECIMAL:
                qPanel = new QuestionWidgetTextInt(key, question);
                break;
            case Value.MONEY:
                qPanel = new QuestionWidgetTextInt(key, question);
                break;
            case Value.DATE:
                qPanel = new QuestionWidgetDate(key, question);
                break;
            case Value.INTEGER:
                qPanel = new QuestionWidgetTextInt(key, question);
                break;
            default:
                qPanel = new QuestionWidgetTextInt(key, question);
                break;
        }

        qPanel.setQuestionChangeListener(questionValueListener);

        if(question.isVisible()) {
            qPanel.setVisible(true);
        } else {
            qPanel.setVisible(false);
        }

        //if the question is marked as fixed, make it non-alterable
        if(question.isFixed()) {
            qPanel.setWidgetFixed();
            qPanel.setValue(value);
        }


        questionPanelHashMap.put(question.getId(), qPanel);

        //add the questionpanel to a map containing active questionpanels
        addQuestionToPanel(qPanel, getQuestionConstraints());
    }

    /**
     * getQuestionContraints() method
     * Receive pre-set question panel constraints
     */
    private GridBagConstraints getQuestionConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }


    /**
     * update() method
     * builds the list panel
     *
     * @param key       key identifier of panel
     * @param value     the value passed
     */
    public void update(String key, Value value) {
        // Update the question itself
        updateQuestion(key, value);
        // Change visibilities en values of the questions in the AST
        coreVisitor.update();
        // Update the GUI
        updateGUI();
    }
    private void updateQuestion(String key, Value value) {
        // Set the value in the questionHashMap
        questionHashMap.get(key).setValue(value);
        if(value.isDefined()) {
            // Set the question on the questionPanelHashMap
            questionPanelHashMap.get(key).setValue(value);

        }
    }

    /**
     * updateGUI() method
     * Updates the GUI
     */
    private void updateGUI() {
        //Iterate over the total question hashmap
        Iterator<Map.Entry<String, Question>> entries = questionHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Question> entry = entries.next();
            Question question = entry.getValue();
            if (question.isVisible()) {
                questionPanelHashMap.get(entry.getKey()).setVisible(true);
                if (question.isFixed()) {
                    questionPanelHashMap.get(entry.getKey()).setValue(question.getValue());
                }
            } else {
                if (questionPanelHashMap.get(entry.getKey()) != null)
                questionPanelHashMap.get(entry.getKey()).setVisible(false);
            }
        }
        mainListPanel.revalidate();
        mainListPanel.repaint();
    }

    /**
     * addQuestionToPanel() method
     * adds a question to the mainlist panel
     *
     * @param questionPanel The questionpanel passed
     * @param gbc           The constraints
     */
    private void addQuestionToPanel(QuestionPanel questionPanel, GridBagConstraints gbc) {
        mainListPanel.add(questionPanel, gbc);
    }

    /**
     * removeQuestionFromPanel() method
     * removes a question to the mainlist panel
     *
     * @param questionPanel The questionpanel passed
     */
    private void removeQuestionFromPanel(QuestionPanel questionPanel) {
        mainListPanel.remove(questionPanel);
    }


    public JPanel getMainListPanel() {
        return mainListPanel;
    }
}
