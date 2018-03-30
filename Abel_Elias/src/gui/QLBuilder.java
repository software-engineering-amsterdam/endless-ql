package gui;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.StringValue;
import QL.classes.values.UndefinedValue;
import QL.classes.values.Value;
import QL.parsing.visitors.FormVisitor;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.questions.QuestionPanel;
import gui.questions.QuestionPanelCheckBox;
import gui.questions.QuestionPanelDate;
import gui.questions.text.QuestionPanelTextInt;
import gui.questions.text.QuestionPanelTextString;
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
    private QuestionChangeListener questionChangeListener;

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
            //If the question is marked as visible, we build a panel
            if(question.isVisible()) {
                buildQuestionPanel(question);
            }
        }
    }

    public JPanel createMainListPanel(QuestionChangeListener questionChangeListener) {
        this.mainListPanel = new JPanel(new GridBagLayout());
        this.questionChangeListener = questionChangeListener;
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
                qPanel = new QuestionPanelTextString(key, question);
                break;
            case Value.BOOLEAN:
                qPanel = new QuestionPanelCheckBox(key, question);
                break;
            case Value.DECIMAL:
                qPanel = new QuestionPanelTextInt(key, question);
                break;
            case Value.MONEY:
                qPanel = new QuestionPanelTextInt(key, question);
                break;
            case Value.DATE:
                qPanel = new QuestionPanelDate(key, question);
                break;
            case Value.INTEGER:
                qPanel = new QuestionPanelTextInt(key, question);
                break;
            default:
                qPanel = new QuestionPanelTextInt(key, question);
                break;
        }

        qPanel.setQuestionChangeListener(questionChangeListener);

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
            // Get concerning question
            Question question = entry.getValue();
            if(question.isVisible()) {
                //If the panelhashmap has not yet created a panel for this question
                if (questionPanelHashMap.get(entry.getKey()) == null) {
                    //build a questionpanel
                    buildQuestionPanel(question);
                }
                if(question.isFixed()) {
                    questionPanelHashMap.get(entry.getKey()).setValue(question.getValue());
                }
            } else {
                // If the question already is placed in a currently visible panel
                if (questionPanelHashMap.get(entry.getKey()) != null) {
                    // remove questionpanel
                    removeQuestionFromPanel(questionPanelHashMap.get(entry.getKey()));
                    questionPanelHashMap.remove(entry.getKey());
                }
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
