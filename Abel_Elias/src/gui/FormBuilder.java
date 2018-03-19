package gui;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.StringValue;
import QL.classes.values.UndefinedValue;
import QL.classes.values.Value;
import gui.questions.QuestionPanel;
import gui.questions.QuestionPanelCheckBox;
import gui.questions.QuestionPanelDate;
import gui.questions.text.QuestionPanelTextInt;
import gui.questions.text.QuestionPanelTextString;
import org.jdatepicker.JDatePicker;
import QL.parsing.visitors.FormVisitor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormBuilder {
    private JFrame mainFrame; //The frame on which the form is located
    private JPanel mainPanel; //The panel on which houses the list of question panels
    private JPanel mainListPanel; // The panel that lists the questions
    private LinkedHashMap<String, Question> questionHashMap; //collection of questions
    private LinkedHashMap<String, QuestionPanel> questionPanelHashMap; //collection of questionpanels currently active
    private FormVisitor coreVisitor; // The visitor
    private int frameHeight = 800; //The height of the GUI
    private int frameWidth = 800; //The width of the GUI

    /**
     * constructor method
     * initializes the building process of the form
     *
     * @param coreVisitor       The main ql visitor
     */
    public FormBuilder(FormVisitor coreVisitor) {
        this.coreVisitor = coreVisitor;
        this.questionHashMap = coreVisitor.getQuestions();
        this.questionPanelHashMap = new LinkedHashMap<String, QuestionPanel>();
    }

    /**
     * initComponents() method
     * initializes the building process for all widgets
     */
    public void initComponents() {
        //Build the frame and panels of the form (the base)
        buildFrame();
        buildMainPanel();
        buildListPanel();

        //Add a scroll pane to the form
        mainPanel.add(new JScrollPane(mainListPanel));

        //Initiate the construction process of the questionpanels by information extracted from the
        //passed questions
        initQuestionPanels();

        //Add the panel to the frame, and set some properties
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * initQuestionPanels() method
     * Initialize the creation of the panels containing
     * the question it's controls through iteration
     */
    private void initQuestionPanels() {
        //Iterate over the questions that were passed
        Iterator<Map.Entry<String, Question>> entries = questionHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Question> entry = entries.next();
            //Extract a single question
            Question question = entry.getValue();
            //If the question is marked as visible, we build a panel
            if(question.isVisible()) {
                buildQuestionPanel(entry.getKey(), question, question.getValue());
            }
        }
    }

    /**
     * buildQuestionPanel() method
     * Build each individual type of question panel and add
     * these to the list panel
     *
     * @param key       question identifier
     * @param question  the question passed
     * @param value     the type of the question passed
     */
    private void buildQuestionPanel(String key, Question question, Value value) {
        QuestionPanel qPanel;
        switch (value.getType()) {
            case Value.STRING:
                qPanel = new QuestionPanelTextString(key, question);
                qPanel.setListener(new StringDocumentListener(key, (JTextField) qPanel.getComponent()));
                break;
            case Value.BOOLEAN:
                qPanel = new QuestionPanelCheckBox(key, question);
                qPanel.setListener(new BoolActionListener(key, (JCheckBox) qPanel.getComponent()));
                break;
            case Value.DECIMAL:
                qPanel = new QuestionPanelTextInt(key, question);
                qPanel.setListener(new IntegerDocumentListener(key, (JFormattedTextField) qPanel.getComponent()));
                break;
            case Value.MONEY:
                qPanel = new QuestionPanelTextInt(key, question);
                qPanel.setListener(new IntegerDocumentListener(key, (JTextField) qPanel.getComponent()));
                break;
            case Value.DATE:
                qPanel = new QuestionPanelDate(key, question);
                qPanel.setListener(new DateActionListener(key, (JDatePicker) qPanel.getComponent()));
                break;
            case Value.INTEGER:
                qPanel = new QuestionPanelTextInt(key, question);
                qPanel.setListener(new IntegerDocumentListener(key, (JTextField) qPanel.getComponent()));
                break;
            default:
                qPanel = new QuestionPanelCheckBox(key, question);
                qPanel.setListener(new BoolActionListener(key, (JCheckBox) qPanel.getComponent()));
                break;
        }
        //if the question is marked as fixed, make it non-alterable
        if(question.isFixed()) {
            qPanel.setWidgetFixed();
            //qPanel.setValue(question.getValue());
        }
        //add the questionpanel to a map containing active questionpanels
        questionPanelHashMap.put(key, qPanel);
        //add question to list panel
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
     * buildFrame() method
     * builds the frame of the application
     */
    private void buildFrame() {
        this.mainFrame = new JFrame("Questionnaire (QL)");
        this.mainFrame.setVisible(true);
        this.mainFrame.setBounds(0, 0, frameHeight, frameWidth);
        this.mainFrame.setLayout(new BorderLayout());
    }

    /**
     * buildMainPanel() method
     * builds the container panel
     */
    private void buildMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
    }

    /**
     * buildListPanel() method
     * builds the list panel
     */
    private void buildListPanel() {
        mainListPanel = new JPanel(new GridBagLayout());
    }

    /**
     * update() method
     * builds the list panel
     *
     * @param key       key identifier of panel
     * @param value     the value passed
     */
    private void update(String key, Value value) {
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
        //Iterate over the total question hashma[
        Iterator<Map.Entry<String, Question>> entries = questionHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Question> entry = entries.next();
            // Get concerning question
            Question question = entry.getValue();
            if(question.isVisible()) {
                //If the panelhashmap has not yet created a panel for this question
                if (questionPanelHashMap.get(entry.getKey()) == null) {
                    //build a questionpanel
                    buildQuestionPanel(entry.getKey(), question, question.getValue());
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
        //reinitiate the listpanel
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

    /**
     ******************************************************************************************
     *ActionListener methods
     ******************************************************************************************
    */

    /**
     * DateActionListener
     * Called when a jDatePicker control is changed
     *
     */
    public class DateActionListener implements ActionListener {

        private JDatePicker picker;
        private String key;

        private DateActionListener(String key, JDatePicker picker) {
            this.key = key;
            this.picker = picker;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Date selectedDate = (Date) picker.getModel().getValue();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String reportDate = df.format(selectedDate);
            JOptionPane.showMessageDialog(null,reportDate);
        }
    }

    /**
     * BoolActionListener
     * Called when a jCheckbox control is changed
     *
     */
    public class BoolActionListener implements ActionListener {

        private JCheckBox checkBox;
        private String key;

        private BoolActionListener(String key, JCheckBox checkBox) {
            this.key = key;
            this.checkBox = checkBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkBox.isSelected()) {
                update(key, new BooleanValue(true));
            } else {
                update(key, new BooleanValue(false));

            }
            checkBox.requestFocus();
        }
    }

    /**
     * StringActionListener
     * Called when a jTextField control is changed
     * in the case of a String questionPanel
     */
    public class StringDocumentListener implements DocumentListener {
        private boolean modified = false;
        private JTextField textField;
        private String key;

        public StringDocumentListener(String key, JTextField textField) {
            this.key = key;
            this.textField = textField;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            actionCalled();
        }

        private void actionCalled() {
            textField.requestFocus();
            if (!modified) {
                modified = true;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String textString = textField.getText();
                        update(key, new StringValue(textString));
                        modified = false;
                        textField.requestFocus();
                    }
                }
                );
            }
        }
    }

    /**
     * StringActionListener
     * Called when a jTextField control is changed
     * in the case of a Integer questionPanel
     */
    public class IntegerDocumentListener implements DocumentListener {
        private boolean modified = false;
        private JTextField textField;
        private String key;

        public IntegerDocumentListener(String key, JTextField textField2) {
            this.key = key;
            this.textField = textField2;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actionCalled();
        }

        private void actionCalled() {
            textField.requestFocus();
            if (!modified) {
                modified = true;
                SwingUtilities.invokeLater(
                        new Runnable() {
                            @Override
                            public void run() {
                                 boolean correctInput = true;
                                    int input = 0;
                                    try {
                                        String textString = textField.getText();
                                        input = Integer.parseInt(textString);
                                    } catch (NumberFormatException exception) {
                                        correctInput = false;
                                    }
                                    if (correctInput) {
                                        update(key, new IntegerValue(input));
                                    } else {
                                        update(key, new UndefinedValue());
                                    }
                                    modified = false;
                                    textField.requestFocus();
                                }
                        }
                );
            }
        }

    }
}