package gui;

import classes.Question;
import classes.values.BooleanValue;
import classes.values.IntegerValue;
import classes.values.StringValue;
import classes.values.UndefinedValue;
import classes.values.Value;
import gui.questions.QuestionPanel;
import gui.questions.QuestionPanelCheckBox;
import gui.questions.QuestionPanelDate;
import gui.questions.QuestionPanelTextInt;
import gui.questions.QuestionPanelTextString;
import org.jdatepicker.JDatePicker;
import parsing.visitors.FormVisitor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FormBuilder {
    private JFrame mainFrame; //The frame on which the form is located
    private JPanel mainPanel; //The panel on which the widgets are located
    private JPanel mainListPanel;
    private LinkedHashMap<String, Question> questionHashMap;
    private LinkedHashMap<String, QuestionPanel> questionPanelHashMap;
    private FormVisitor coreVisitor;

    private int FRAMEHEIGHT = 800; //The height of the GUI
    private int FRAMEWIDTH = 800; //The width of the GUI

    /**
     * constructor method
     * initializes the building process of the form
     *
     * @param coreVisitor
     */
    public FormBuilder(FormVisitor coreVisitor, LinkedHashMap<String, Question> questionHashMap) {
        this.coreVisitor = coreVisitor;
        this.questionHashMap = questionHashMap;
        this.questionPanelHashMap = new LinkedHashMap<String, QuestionPanel>();
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
     */
    private void initQuestionPanels() {
        Iterator<Map.Entry<String, Question>> entries = questionHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Question> entry = entries.next();
            Question question = entry.getValue();
            if(question.isVisible()) {
                buildQuestionPanel(entry.getKey(), question, question.getValue());
            } else {

            }
        }
    }

    /**
     * Build each individual question panel and add
     * these to the main panel
     *
     * @param key      identifier
     * @param question question
     * @param value type
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
        if(question.isFixed()) {
            qPanel.setWidgetFixed();
            //qPanel.setValue(question.getValue());
        }
        questionPanelHashMap.put(key, qPanel);
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
        this.mainFrame.setBounds(0, 0, FRAMEHEIGHT, FRAMEWIDTH);
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

    private void update(String key, Value value) {
            updateQuestion(key, value);
            coreVisitor.update();
            updateGUI();
    }
    private void updateQuestion(String key, Value value) {
        questionHashMap.get(key).setValue(value);
        if(value.isDefined()) {
            questionPanelHashMap.get(key).setValue(value);

        }
    }
    /**
     * updateGUI() method
     * builds the frame
     */
    private void updateGUI() {
        Iterator<Map.Entry<String, Question>> entries = questionHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Question> entry = entries.next();
            Question question = entry.getValue();
            if(question.isVisible()) {
                if (questionPanelHashMap.get(entry.getKey()) == null) {
                    buildQuestionPanel(entry.getKey(), question, question.getValue());
                }
            } else {
                if (questionPanelHashMap.get(entry.getKey()) != null) {
                    removeQuestionFromPanel(questionPanelHashMap.get(entry.getKey()));
                    questionPanelHashMap.remove(entry.getKey());
                }
            }
        }
        mainListPanel.revalidate();
        mainListPanel.repaint();
    }

    private void addQuestionToPanel(QuestionPanel questionPanel, GridBagConstraints gbc) {
        mainListPanel.add(questionPanel, gbc);
    }

    private void removeQuestionFromPanel(QuestionPanel questionPanel) {
        mainListPanel.remove(questionPanel);
    }


    /**
     * ActionListener methods
     ***********************************/

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

    //Bool ActionListener
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

    //String ActionListener
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