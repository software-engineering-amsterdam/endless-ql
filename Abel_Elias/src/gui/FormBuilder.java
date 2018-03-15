package gui;

import classes.Question;
import classes.expressions.Expression;
import parsing.visitors.BaseVisitor;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public FormBuilder(BaseVisitor baseVisitor, HashMap questionHashMap) {
        this.baseVisitor = baseVisitor;
        this.questionHashMap = questionHashMap;
        this.questionPanelList = new ArrayList<QuestionPanel>();

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
            buildQuestionPanel((String) pair.getKey(), (Question) pair.getValue());
            it.remove(); // avoid a ConcurrentModificationException
        }
    }

    /**
     * Build each individual question panel and add
     * these to the main panel
     * @param key
     * @param question
     */
    private void buildQuestionPanel(String key, Question question) {
        QuestionPanel qPanel = new QuestionPanel(key, question);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addQuestionToPanel(qPanel, gbc);
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


    private class QuestionPanel extends JPanel {
        private String key;
        private Question question;

        private QuestionPanel(String key, Question question) {
            this.key = key;
            this.question = question;
            this.add(new JLabel(question.getText()));
            this.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
            addQuestionPanelControls(question);
        }

        private void addQuestionPanelControls(Question question) {
            switch(question.getTypeName()) {
                case String:
                    createStringControl();
                    break;
                case Boolean:
                    createBoolControl();
                    break;
                default:
                    break;
            }
        }

        private void createBoolControl() {
            JCheckBox checkBox = new JCheckBox();
            checkBox.addActionListener(new BoolActionListener(key, checkBox));
            this.add(checkBox);
        }

        private void createStringControl() {
        }
    }

    public class BoolActionListener implements ActionListener {

        private JCheckBox checkBox;
        private String key;

        private BoolActionListener(String key, JCheckBox checkBox) {
            this.key = key;
            this.checkBox = checkBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(checkBox.isSelected()) {
                System.out.println("checkbox checked");
                updateQuestions(key, true);
            } else {
                updateQuestions(key, false);

            }
            checkBox.requestFocus();
        }
    }

    private void updateQuestions(String key, Boolean value) {
        questionHashMap.get(key).setValue(value);
        updateGUI();
    }

    private void updateGUI() {
        questionHashMap = baseVisitor.updateQuestions(questionHashMap);
        for(QuestionPanel questionPanel : questionPanelList) {
            Boolean ifExpressionSatisfied = baseVisitor.validateExpression(questionPanel.question);
            if(ifExpressionSatisfied) {
                addQuestionToPanel(questionPanel);
            } else {
                removeQuestionFromPanel(questionPanel);
            }
        }
    }

    private void addQuestionToPanel(QuestionPanel questionPanel, GridBagConstraints gbc) {
        mainListPanel.add(questionPanel, gbc);
        questionPanelList.add(questionPanel);
    }


    private void addQuestionToPanel(QuestionPanel questionPanel) {
        mainListPanel.add(questionPanel);
        questionPanelList.add(questionPanel);

    }



    private void removeQuestionFromPanel(QuestionPanel questionPanel) {
        mainListPanel.remove(questionPanel);
        questionPanelList.remove(questionPanel);

    }

}
