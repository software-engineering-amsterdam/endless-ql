//package gui;
//
//import classes.Question;
//import classes.expressions.Expression;
//import classes.values.BooleanValue;
//import classes.values.IntegerValue;
//import classes.values.StringValue;
//import classes.values.Value;
//import gui.questions.QuestionPanel;
//import gui.questions.QuestionPanelCheckBox;
//import gui.questions.QuestionPanelDate;
//import gui.questions.QuestionPanelText;
//import gui.questions.QuestionPanelTextInt;
//import gui.questions.QuestionPanelTextString;
//import org.jdatepicker.JDatePicker;
//import parsing.visitors.BaseVisitor;
//
//import javax.swing.*;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//public class FormBuilder {
//    private JFrame mainFrame; //The frame on which the form is located
//    private JPanel mainPanel; //The panel on which the widgets are located
//    private JPanel mainListPanel;
//    private HashMap<String, Question> questionHashMap = new HashMap<String, Question>();
//    private ArrayList<QuestionPanel> questionPanelList;
//    private BaseVisitor baseVisitor;
//
//    private int FRAMEHEIGHT = 800; //The height of the GUI
//    private int FRAMEWIDTH = 800; //The width of the GUI
//
//    /**
//     * constructor method
//     * initializes the building process of the form
//     *
//     * @param baseVisitor
//     */
//    public FormBuilder(BaseVisitor baseVisitor, HashMap<String, Question> questionHashMap) {
//        this.baseVisitor = baseVisitor;
//        this.questionHashMap = questionHashMap;
//        this.questionPanelList = new ArrayList<>();
//
//    }
//
//    /**
//     * initComponents() method
//     * initializes the building process for all widgets
//     */
//    public void initComponents() {
//        //Build the frame and panel of the form
//        buildFrame();
//        buildPanel();
//        buildList();
//        //Add a scroll pane to the form
//        mainPanel.add(new JScrollPane(mainListPanel));
//        initQuestionPanels();
//
//        mainFrame.add(mainPanel);
//        mainFrame.setVisible(true);
//        mainFrame.setLocationRelativeTo(null);
//        mainFrame.setVisible(true);
//
//    }
//
//    /**
//     * Initialize the creation of the panels containing
//     * the question it's controls through iteration
//     */
//    private void initQuestionPanels() {
//        Iterator it = questionHashMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            Question question = (Question) pair.getValue();
//            if(question.getVisibility()) {
//                buildQuestionPanel((String) pair.getKey(), question, question.getValue());
//            }
//            it.remove(); // avoid ConcurrentModificationException
//        }
//    }
//
//    /**
//     * Build each individual question panel and add
//     * these to the main panel
//     *
//     * @param key      identifier
//     * @param question question
//     * @param value type
//     */
//    private void buildQuestionPanel(String key, Question question, Value value) {
//        QuestionPanel qPanel;
//        switch (value.getType()) {
//            case Value.STRING:
//                qPanel = new QuestionPanelTextString(key, question);
//                qPanel.setListener(new StringDocumentListener(key, (JTextField) qPanel.getComponent()));
//                break;
//            case Value.BOOLEAN:
//                qPanel = new QuestionPanelCheckBox(key, question);
//                qPanel.setListener(new BoolActionListener(key, (JCheckBox) qPanel.getComponent()));
//                break;
//            case Value.DECIMAL:
//                qPanel = new QuestionPanelTextInt(key, question);
//                qPanel.setListener(new IntegerDocumentListener(key, (JTextField) qPanel.getComponent()));
//                break;
//            case Value.MONEY:
//                qPanel = new QuestionPanelTextInt(key, question);
//                qPanel.setListener(new IntegerDocumentListener(key, (JTextField) qPanel.getComponent()));
//                break;
//            case Value.DATE:
//                qPanel = new QuestionPanelDate(key, question);
//                qPanel.setListener(new DateActionListener(key, (JDatePicker) qPanel.getComponent()));
//                break;
//            case Value.INTEGER:
//                qPanel = new QuestionPanelTextInt(key, question);
//                qPanel.setListener(new IntegerDocumentListener(key, (JTextField) qPanel.getComponent()));
//                break;
//            default:
//                qPanel = new QuestionPanelCheckBox(key, question);
//                qPanel.setListener(new StringDocumentListener(key, (JTextField) qPanel.getComponent()));
//                break;
//        }
//        addQuestionToPanel(qPanel, getQuestionConstraints());
//    }
//
//
//    private GridBagConstraints getQuestionConstraints() {
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.weightx = 1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        return gbc;
//    }
//
//    /**
//     * buildFrame() method
//     * builds the frame
//     */
//    private void buildFrame() {
//        this.mainFrame = new JFrame("Questionnaire (QL)");
//        this.mainFrame.setVisible(true);
//        this.mainFrame.setBounds(0, 0, FRAMEHEIGHT, FRAMEWIDTH);
//        this.mainFrame.setLayout(new BorderLayout());
//    }
//
//    /**
//     * buildFrame() method
//     * builds the panel
//     */
//    private void buildPanel() {
//        mainPanel = new JPanel(new BorderLayout());
//    }
//
//    private void buildList() {
//        mainListPanel = new JPanel(new GridBagLayout());
//    }
//
//
//    private void updateQuestion(String key, Boolean value) {
//        Question question = questionHashMap.get(key);
//        question.setValue(new BooleanValue(value));
//        questionHashMap.remove(key);
//        questionHashMap.put(key, question);
//        updateGUI();
//    }
//    /**
//     * updateGUI() method
//     * builds the frame
//     */
//    private void updateGUI() {
//        questionHashMap = baseVisitor.updateQuestions(questionHashMap);
//        for (Component questionPanel : mainListPanel.getComponents()) {
//            if (questionPanel instanceof QuestionPanel) {
//                if (((QuestionPanel) questionPanel).getState()) {
//                    addQuestionToPanel((QuestionPanel) questionPanel);
//                } else {
//                    removeQuestionFromPanel((QuestionPanel) questionPanel);
//                }
//            }
//        }
//    }
//
//    private void addQuestionToPanel(QuestionPanel questionPanel, GridBagConstraints gbc) {
//        mainListPanel.add(questionPanel, gbc);
//    }
//
//
//    private void addQuestionToPanel(QuestionPanel questionPanel) {
//        mainListPanel.add(questionPanel);
//    }
//
//
//    private void removeQuestionFromPanel(QuestionPanel questionPanel) {
//        mainListPanel.remove(questionPanel);
//    }
//
//
//    /**
//     * ActionListener methods
//     ***********************************/
//
//    public class DateActionListener implements ActionListener {
//
//        private JDatePicker picker;
//        private String key;
//
//        private DateActionListener(String key, JDatePicker picker) {
//            this.key = key;
//            this.picker = picker;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            Date selectedDate = (Date) picker.getModel().getValue();
//            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//            String reportDate = df.format(selectedDate);
//            JOptionPane.showMessageDialog(null,reportDate);
//        }
//    }
//
//    //Bool ActionListener
//    public class BoolActionListener implements ActionListener {
//
//        private JCheckBox checkBox;
//        private String key;
//
//        private BoolActionListener(String key, JCheckBox checkBox) {
//            this.key = key;
//            this.checkBox = checkBox;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (checkBox.isSelected()) {
//                System.out.println("checkbox checked");
//                update(key, true);
//            } else {
//                update(key, false);
//
//            }
//            checkBox.requestFocus();
//        }
//    }
//
//    private void update(String key, boolean value) {
//        updateQuestion(key, value);
//        updateGUI(key, value);
//
//    }
//
//    private void updateGUI(String key, Object value) {
//
//        //baseVisitor.updateQuestions(questionHashMap);
////        for (QuestionPanel panel : questionPanelList) {
////            if (questionHashMap.get(panel.getQuestion()) != null) {
////                boolean ifExpressionSatisfied = true;
////                if (ifExpressionSatisfied) {
////                    addQuestionToPanel(panel);
////                } else {
////                    removeQuestionFromPanel(panel);
////                }
////            }
////        }
//        //Update and validate the components
//        mainListPanel.revalidate();
//        mainListPanel.repaint();
//    }
//
//    //String ActionListener
//    public class StringDocumentListener implements DocumentListener {
//
//        private JTextField textField;
//        private String key;
//
//        public StringDocumentListener(String key, JTextField textField) {
//            this.key = key;
//            this.textField = textField;
//        }
//
//        @Override
//        public void removeUpdate(DocumentEvent e) {
//
//
//            textField.requestFocus();
//            SwingUtilities.invokeLater(new Runnable() {
//
//                @Override
//                public void run() {
//                    String textString = textField.getText();
//                    updateGUI(key, new StringValue(textString));
//                    textField.requestFocus();
//                }
//            });
//        }
//
//        @Override
//        public void changedUpdate(DocumentEvent e) {
//        }
//
//        @Override
//        public void insertUpdate(DocumentEvent e) {
//            textField.requestFocus();
//            SwingUtilities.invokeLater(new Runnable() {
//
//                @Override
//                public void run() {
//                    String textString = textField.getText();
//                    updateGUI(key, new StringValue(textString));
//                    textField.requestFocus();
//
//                }
//            });
//        }
//    }
//
//    public class IntegerDocumentListener implements DocumentListener {
//
//        private JTextField textField;
//        private String key;
//
//        public IntegerDocumentListener(String key, JTextField textField2) {
//            this.key = key;
//            this.textField = textField2;
//        }
//
//        @Override
//        public void insertUpdate(DocumentEvent e) {
//            textField.requestFocus();
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    boolean correctInput = true;
//                    int inputNumber = 0;
//
//                    try {
//                        String textString = textField.getText();
//                        inputNumber = Integer.parseInt(textString);
//                    } catch (Exception exception) {                    ///
//                        System.out.println("Insert a number of max 9 digits!");
//                        correctInput = false;
//                    }
//
//                    if (correctInput) {
//                        updateGUI(key, new IntegerValue(inputNumber));
//                    } else {
//                        updateGUI(key, new IntegerValue());
//                    }
//                    textField.requestFocus();
//                }
//            });
//        }
//
//        @Override
//        public void removeUpdate(DocumentEvent e) {
//
//            textField.requestFocus();
//            SwingUtilities.invokeLater(new Runnable() {
//
//                @Override
//                public void run() {
//                    boolean correctInput = true;
//                    int input = 0;
//                    try {
//                        String textString = textField.getText();
//                        input = Integer.parseInt(textString);
//                    } catch (Exception exception) {
//                        correctInput = false;
//                    }
//                    if (correctInput) {
//                        updateGUI(key, new IntegerValue(input));
//                    } else {
//                        updateGUI(key, new IntegerValue());
//                    }
//                    textField.requestFocus();
//                }
//            });
//        }
//
//        @Override
//        public void changedUpdate(DocumentEvent e) {
//
//        }
//    }
//}