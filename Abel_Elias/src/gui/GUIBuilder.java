package gui;

import QL.classes.values.Value;
import QLS.parsing.visitors.StylesheetVisitor;
import QL.parsing.visitors.FormVisitor;
import gui.listeners.QuestionValueListener;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GUIBuilder{
    private JFrame mainFrame; //The frame on which the form is located
    private JPanel mainPanel; //The panel on which houses the list of question panels

    private QLBuilder qlBuilder;
    private QLSBuilder qlsBuilder;
    private QuestionValueListener questionValueListener;

    private int frameHeight = 800; //The height of the GUI
    private int frameWidth = 800; //The width of the GUI

    /**
     * constructor method
     * initializes the building process of the form
     *
     * @param coreVisitor       The main ql visitor
     */
    public GUIBuilder(FormVisitor coreVisitor) {
        this.qlBuilder = new QLBuilder(coreVisitor);
        this.questionValueListener = new QuestionValueListener(this);
        //this.coreVisitor = coreVisitor;

        initObservers();
        initFrame();
        initComponents();
    }

    public GUIBuilder(FormVisitor coreVisitor, StylesheetVisitor stylesheetVisitor) {
        this.qlBuilder = new QLBuilder(coreVisitor);
        this.questionValueListener = new QuestionValueListener(this);
        this.qlsBuilder = new QLSBuilder(stylesheetVisitor);
        initObservers();
        initFrame();
        initComponents(true);

        //mainFrame.add(qlsBuilder.getLayout());
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void initObservers(){

    }

    /**
     * initComponents() method
     * initializes the building process for the frame
     */
    public void initFrame() {
        //Build the frame and panels of the form (the base)
        buildFrame();
        buildMainPanel();
    }

    public void initComponents() {
        //Add a scroll pane to the form
        mainPanel.add(new JScrollPane(qlBuilder.createMainListPanel(questionValueListener)));

        //Add the panel to the frame, and set some properties
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void onQuestionChange(String key, Value value) {
        qlBuilder.update(key, value);
        qlsBuilder.createStyledForm(qlBuilder.getQuestionPanelHashMap());
    }

    public void initComponents(boolean a)  {
        //initStyleSheet(stylesheetVisitor);
        qlBuilder.createMainListPanel(questionValueListener);
        qlsBuilder.createStyledForm(qlBuilder.getQuestionPanelHashMap());
        mainPanel.add(qlsBuilder.getStyleSheetPanel());
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
}