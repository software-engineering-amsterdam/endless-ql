package gui;

import classes.Question;
import classes.expressions.Expression;
import classes.values.BooleanValue;
import classes.values.Value;
import gui.questions.QuestionPanel;
import gui.questions.QuestionPanelCheckBox;
import gui.questions.QuestionPanelText;
import parsing.visitors.BaseVisitor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FormBuilder {
    private JFrame mainFrame; //The frame on which the form is located
    private JPanel mainPanel; //The panel on which the widgets are located
    private JPanel mainListPanel;
    private HashMap<String, Question> questionHashMap = new HashMap<String, Question>();
    private ArrayList<QuestionPanel> questionPanelList;
    private BaseVisitor baseVisitor;

    private int FRAMEHEIGHT = 800; //The height of the GUI
    private int FRAMEWIDTH = 800; //The width of the GUI

    /**
     * constructor method
     * initializes the building process of the form
     * @param baseVisitor
     */
    public FormBuilder(BaseVisitor baseVisitor, HashMap<String,Question> questionHashMap) {
        this.baseVisitor = baseVisitor;
        this.questionHashMap = questionHashMap;
        this.questionPanelList = new ArrayList<>();

    }

    /**
     * initComponents() method
     * initializes the building process for all widgets
     */
    public void initComponents() {
        //Build the frame and panel of the form
        buildFrame();
        buildPanel();
        buildList();
        //Add a scroll pane to the form
        mainPanel.add(new JScrollPane(mainListPanel));
        initQuestionPanels();

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    /**
     * Initialize the creation of the panels containing
     * the question it's controls through iteration
\     */
    private void initQuestionPanels() {
        Iterator it = questionHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Question question = (Question) pair.getValue();
            buildQuestionPanel((String) pair.getKey(), question, question.getValue());
            it.remove(); // avoid a ConcurrentModificationException
        }
    }

    /**
     * Build each individual question panel and add
     * these to the main panel
     * @param key identifier
     * @param question question
     */
    private void buildQuestionPanel(String key, Question question, Value value) {

        QuestionPanel qPanel;

        switch (value.getType()) {
            case Value.STRING:
                qPanel = new QuestionPanelText(key, question);
                break;
            case Value.BOOLEAN:
                qPanel = new QuestionPanelCheckBox(key, question);
                break;
            case Value.DECIMAL:
                qPanel = new QuestionPanelText(key, question);
                break;
            case Value.MONEY:
                qPanel = new QuestionPanelText(key, question);
                break;
            case Value.DATE:
                qPanel = new QuestionPanelText(key, question);
                break;
            case Value.INTEGER:
                qPanel = new QuestionPanelText(key, question);
                break;
            default:
                qPanel = new QuestionPanelCheckBox(key, question);
                break;
        }
        addQuestionToPanel(qPanel, getQuestionConstraints());
    }


    private GridBagConstraints getQuestionConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }
    /**
     * buildFrame() method
     * builds the frame
     */
    private void buildFrame() {
        this.mainFrame = new JFrame("Questionnaire (QL)");
        this.mainFrame.setVisible(true);
        this.mainFrame.setBounds(0,0,FRAMEHEIGHT, FRAMEWIDTH);
        this.mainFrame.setLayout(new BorderLayout());
    }

    /**
     * buildFrame() method
     * builds the panel
     */
    private void buildPanel() {
        mainPanel = new JPanel(new BorderLayout());
    }

    private void buildList() {
        mainListPanel = new JPanel(new GridBagLayout());
    }


    private void updateQuestions(String key, Boolean value) {
        Question question = questionHashMap.get(key);
        question.setValue(new BooleanValue(value));
        questionHashMap.remove(key);
        questionHashMap.put(key, question);
        updateGUI();
    }

    private void updateGUI() {
        questionHashMap = baseVisitor.updateQuestions(questionHashMap);
        for(Component questionPanel : mainListPanel.getComponents()) {
            if (questionPanel instanceof QuestionPanel) {
                if (((QuestionPanel) questionPanel).getState()) {
                    addQuestionToPanel((QuestionPanel) questionPanel);
                } else {
                    removeQuestionFromPanel((QuestionPanel) questionPanel);
                }
            }
        }
    }

    private void addQuestionToPanel(QuestionPanel questionPanel, GridBagConstraints gbc) {
        mainListPanel.add(questionPanel, gbc);
    }


    private void addQuestionToPanel(QuestionPanel questionPanel) {
        mainListPanel.add(questionPanel);
    }



    private void removeQuestionFromPanel(QuestionPanel questionPanel) {
        mainListPanel.remove(questionPanel);
    }

}
