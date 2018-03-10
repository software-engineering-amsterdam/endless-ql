package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.models.expressions.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class GUIBuilder {

    private static final int FORM_VIEW_HEIGHT = 700;

    private static final int BUTTON_HEIGHT = 50;
    private static final int LOG_HEIGHT    = 100;
    private static final int FOOTER_HEIGHT = BUTTON_HEIGHT + LOG_HEIGHT;

    private static final int FORM_HEIGHT = 3000; // TODO: Dynamic height
    private static final int FORM_WIDTH  = 700;
    private static final int INPUT_WIDTH = 500;

    private static final int FULL_HEIGHT = FORM_VIEW_HEIGHT + FOOTER_HEIGHT;
    private static final int FULL_WIDTH  = FORM_WIDTH + INPUT_WIDTH;

    public static Frame getGUI(Form form) {
        Frame mainFrame = getMainFrame();

        JPanel inputPanel  = getTextPanel(INPUT_WIDTH, FORM_VIEW_HEIGHT);
        JPanel formPanel   = getFormPanel(form);
        JPanel bottomPanel = getBottomPanel();

        mainFrame.add(inputPanel, BorderLayout.LINE_START);
        mainFrame.add(formPanel, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.PAGE_END);
        bottomPanel.setVisible(true);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.dispose();
            }
        });

        mainFrame.setVisible(true);
        return mainFrame;
    }

    private static Frame getMainFrame() {
        Frame mainFrame = new Frame();

        mainFrame.setSize(new Dimension(FULL_WIDTH, FULL_HEIGHT));
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());

        return mainFrame;
    }

    private static JPanel getFormPanel(Form form) {
        JPanel contentPanel    = new JPanel();
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        JPanel formPanel       = new JPanel(null);

        int panelHeight        = FORM_VIEW_HEIGHT - 5;

        contentPanel.setPreferredSize(new Dimension(FORM_WIDTH, FORM_HEIGHT));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        List<Component> components = form.getComponents();
        components.forEach(contentPanel::add);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(2, 5, FORM_WIDTH - 15    , panelHeight);

        formPanel.setPreferredSize(new Dimension(FORM_WIDTH, panelHeight));
        formPanel.add(scrollPane);

        return formPanel;
    }

    private static JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();

        JPanel buttonPanel = getButtonPanel();
        JPanel logPanel    = getTextPanel(FULL_WIDTH, LOG_HEIGHT);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.PAGE_START);
        bottomPanel.add(logPanel, BorderLayout.PAGE_END);
        logPanel.setVisible(true);

        return bottomPanel;
    }

    private static JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(FULL_WIDTH, BUTTON_HEIGHT));

        JButton loadButton = new JButton("Load QL");
        loadButton.setPreferredSize(new Dimension(150, BUTTON_HEIGHT));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton saveButton = new JButton("Save QL");
        saveButton.setPreferredSize(new Dimension(150, BUTTON_HEIGHT));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton processButton = new JButton("Process QL");
        processButton.setPreferredSize(new Dimension(150, BUTTON_HEIGHT));
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JPanel spacingL = new JPanel();
        spacingL.setPreferredSize(new Dimension(210, BUTTON_HEIGHT));

        JPanel spacingR = new JPanel();
        spacingR.setPreferredSize(new Dimension(220, BUTTON_HEIGHT));


        JButton exportButton = new JButton("Export form");
        exportButton.setPreferredSize(new Dimension(250, BUTTON_HEIGHT));
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(processButton);
        buttonPanel.add(spacingL);
        buttonPanel.add(exportButton);
        buttonPanel.add(spacingR);



        return buttonPanel;
    }

    private static JPanel getTextPanel(int width, int height) {
        JPanel textPanel = new JPanel();

        TextArea textArea = new TextArea("",0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setPreferredSize(new Dimension(width, height));

        textPanel.add(textArea);
        textPanel.setVisible(true);

        return textPanel;
    }
}
