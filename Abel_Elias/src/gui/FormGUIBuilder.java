package gui;

import classes.expressions.BooleanExpression;
import classes.expressions.Expression;
import classes.form.Form;
import classes.statements.Question;
import parsing.AST_Visitor;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class FormGUIBuilder {
    private JFrame mainFrame; //The frame on which the form is located
    private JPanel mainPanel; //The panel on which the widgets are located
    private JPanel mainList; // The list of widgets
    private static int FRAMEHEIGHT = 800; //The height of the GUI
    private static int FRAMEWIDTH = 800; //The width of the GUI
    private AST_Visitor astBuilder;
    private HashMap<Question, BooleanExpression> statementConditionsMap;
    private ArrayList<QuestionPanel> questionPanelList = new ArrayList<>();
    private FormGUIVisitor visitor;


    /**
     * constructor method
     * initializes the building process of the form
     * @param astBuilder ast builder clkass
     */
    public FormGUIBuilder(AST_Visitor astBuilder) {
        this.astBuilder = astBuilder;
    }

    /**
     * initComponents() method
     * initializes the building process for all widgets
     * @param form a
     */
    public void initComponents(Form form) {

        //Build the frame and panel of the form
        buildFrame();
        buildPanel();
        buildList();
        //Add a scroll pane to the form
        mainPanel.add(new JScrollPane(mainList));

        visitor = new FormGUIVisitor(this);
        visitor.visitForm(form);
        this.statementConditionsMap = visitor.getStatementConditionsMap();
        initQuestionPanels();

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    /**
     * Initialize the creation of the panels containing
     * the question it's controls through iteration
     */
    private void initQuestionPanels() {
        for(Question question : statementConditionsMap.keySet()) {
            buildQuestionPanel(question);
        }
    }

    /**
     * Build each individual question panel and add
     * these to the main panel
     * @param question a
     */
    private void buildQuestionPanel(Question question) {
        QuestionPanel qPanel = new QuestionPanel(question);
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
        mainList = new JPanel(new GridBagLayout());
    }


    private class QuestionPanel extends JPanel {
        private Question question;

        private QuestionPanel(Question question) {
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
            checkBox.addActionListener(new BoolActionListener(question.getIdentifier(), checkBox));
            this.add(checkBox);
        }

        private void createStringControl() {
        }

        public Question getQuestion() {
            return question;
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
                updateGUI(key, true);
            } else {
                updateGUI(key, false);

            }
            checkBox.requestFocus();
        }
    }

    private void updateGUI(String key, Object value) {
        astBuilder.update(key, value);
        for(QuestionPanel panel : questionPanelList) {
            if(statementConditionsMap.get(panel.getQuestion()) != null) {
                Boolean ifExpressionSatisfied = visitor.validateExpression(statementConditionsMap.get(panel.getQuestion()));
                if(ifExpressionSatisfied != null) {
//                    String testTempString = ifExpressionSatisfied.toString();
//                }
                    if (ifExpressionSatisfied) {
                        addQuestionToPanel(panel);
                    } else {
                        removeQuestionFromPanel(panel);
                    }
                }
            }
        }
        //Update and validate the components
        mainList.revalidate();
        mainList.repaint();
    }

    private void addQuestionToPanel(QuestionPanel questionPanel, GridBagConstraints gbc) {
        mainList.add(questionPanel, gbc);
        questionPanelList.add(questionPanel);
    }

    private void addQuestionToPanel(QuestionPanel questionPanel) {
        mainList.add(questionPanel);
        questionPanelList.add(questionPanel);
    }

    private void removeQuestionFromPanel(QuestionPanel questionPanel) {
        mainList.remove(questionPanel);
        questionPanelList.remove(questionPanel);
    }

}
