package gui;

import QL.classes.Question;
import QL.classes.values.*;
import QL.parsing.visitors.FormVisitor;
import gui.panels.QuestionPanel;
import gui.widgets.CheckBoxWidget;
import gui.widgets.DateWidget;
import gui.widgets.TextWidget;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class QLBuilder implements Observer {
    private JPanel mainPanel;

    private LinkedHashMap<String, Question> questionHashMap; //collection of questions
    private LinkedHashMap<String, QuestionPanel> questionPanelHashMap; //collection of questionpanels currently active
    private FormVisitor coreVisitor;

    public LinkedHashMap<String, QuestionPanel> getQuestionPanelHashMap() {
        return questionPanelHashMap;
    }

    public LinkedHashMap<String, Question> getQuestionHashMap() {
        return questionHashMap;
    }

    public QLBuilder(FormVisitor coreVisitor) {
        this.coreVisitor = coreVisitor;
        this.questionHashMap = coreVisitor.getQuestions();
        this.questionPanelHashMap = new LinkedHashMap<String, QuestionPanel>();
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

    public JPanel createMainListPanel() {
        this.mainPanel = new JPanel(new GridBagLayout());
        initQuestionPanels();
        return mainPanel;
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
            case Value.DATE:
                qPanel = new QuestionPanel(question, new DateWidget((DateValue) value));
                break;
            case Value.DECIMAL:
            case Value.MONEY:
            case Value.INTEGER:
                qPanel = new QuestionPanel(question, new TextWidget((NumericValue) value));
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
     * updateGUI() method
     * Updates the GUI
     */
    private void updateGUI() {
        //Iterate over the total question hashmap
        for (QuestionPanel q : questionPanelHashMap.values()) {
            if (q.getQuestion().isVisible()) {
                q.setVisible(true);
                if (q.getQuestion().isFixed()) {
                    q.refresh();
                }
            } else {
                q.setVisible(false);
            }
        }
    }

    /**
     * addQuestionToPanel() method
     * adds a question to the mainlist panel
     *
     * @param questionPanel The questionpanel passed
     * @param gbc           The constraints
     */
    private void addQuestionToPanel(QuestionPanel questionPanel, GridBagConstraints gbc) {
        mainPanel.add(questionPanel, gbc);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.coreVisitor.update();
        this.updateGUI();

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
