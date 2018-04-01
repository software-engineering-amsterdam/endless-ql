package gui;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import QL.classes.values.StringValue;
import QL.classes.values.Value;
import QL.parsing.visitors.FormVisitor;
import gui.panels.QuestionPanel;
import gui.listeners.QuestionValueListener;
import gui.widgets.CheckBoxWidget;
import gui.widgets.DateWidget;
import gui.widgets.TextWidget;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class QLBuilder implements Observer {

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
        Value value = question.getValue();

        if(!question.isFixed()){
            value.addObserver(this);
        }

        QuestionPanel qPanel = null;

        switch (value.getType()) {
            case Value.STRING:
                qPanel = new QuestionPanel(question, new TextWidget((StringValue) value));
                break;
            case Value.BOOLEAN:
                qPanel = new QuestionPanel(question, new CheckBoxWidget((BooleanValue) value));
                break;
            case Value.DECIMAL:
                // add panel for decimals
                break;
            case Value.MONEY:
                // add panel for decimals
                break;
            case Value.DATE:
                qPanel = new QuestionPanel(question, new DateWidget((DateValue) value));
                break;
            case Value.INTEGER:
                // add panel for integers
                break;
            default:
                // default case
                break;
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
            questionPanelHashMap.get(key).refresh();

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
                    questionPanelHashMap.get(entry.getKey()).refresh();
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

    @Override
    public void update(Observable o, Object arg) {
        this.coreVisitor.update();
        this.updateGUI();
    }
}
