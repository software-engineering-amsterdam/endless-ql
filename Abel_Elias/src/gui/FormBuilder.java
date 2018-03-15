package gui;

import classes.Question;
import gui.questions.QuestionPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FormBuilder {
    private JFrame mainFrame; //The frame on which the form is located
    private JPanel mainPanel; //The panel on which the widgets are located
    private int FRAMEHEIGHT = 800; //The height of the GUI
    private int FRAMEWIDTH = 800; //The width of the GUI

    /**
     * constructor method
     * initializes the building process of the form
     */
    public FormBuilder() {;
    }

    /**
     * initComponents() method
     * initializes the building process for all widgets
     * @param memory
     */
    public void initComponents(HashMap memory) {
        //Build the frame and panel of the form
        buildFrame();
        buildPanel();
        //Add a scroll pane to the form
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        this.mainFrame.getContentPane().add(scrollPane);

        initQuestionPanels(memory);
        mainFrame.add(mainPanel, BorderLayout.NORTH);

    }

    /**
     * Initialize the creation of the panels containing
     * the question it's controls through iteration
     * @param memory
     */
    private void initQuestionPanels(HashMap memory) {
        Iterator it = memory.entrySet().iterator();
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
        mainPanel.add(qPanel, BorderLayout.NORTH);
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
}
