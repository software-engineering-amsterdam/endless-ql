package gui;

import QLS.parsing.visitors.StylesheetVisitor;
import QL.parsing.visitors.FormVisitor;

import javax.swing.*;
import java.awt.*;

public class GUIBuilder{
    private JFrame mainFrame; //The frame on which the form is located
    private JPanel mainPanel; //The panel on which houses the list of question panels

    private QLBuilder qlBuilder;
    private QLSBuilder qlsBuilder;
    private boolean styling;

    private static final int FRAME_HEIGHT = 800; //The height of the GUI
    private static final int FRAME_WIDTH = 800; //The width of the GUI

    /**
     * constructor method
     * initializes the building process of the form
     *
     * @param coreVisitor       The main ql visitor
     */
    public GUIBuilder(FormVisitor coreVisitor) {
        this.qlBuilder = new QLBuilder(coreVisitor);
        this.styling = false;

        initFrame();
        initComponents();
    }

    public GUIBuilder(FormVisitor coreVisitor, StylesheetVisitor stylesheetVisitor) {
        this.qlsBuilder = new QLSBuilder(stylesheetVisitor, coreVisitor);
        this.styling = true;

        initFrame();
        initComponents();
    }

    /**
     * initComponents() method
     * initializes the building process for the frame
     */
    private void initFrame() {
        //Build the frame and panels of the form (the base)
        buildFrame();
        buildMainPanel();
    }

    private void initComponents() {
        //Add the panel to the frame, and set some properties
        if(styling){
            mainPanel.add(qlsBuilder.getMainPanel());
        }else{
            mainFrame.add(qlBuilder.createMainListPanel());
        }

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    /**
     * buildFrame() method
     * builds the frame of the application
     */
    private void buildFrame() {
        this.mainFrame = new JFrame("Questionnaire (QL)");
        this.mainFrame.setVisible(true);
        this.mainFrame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
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