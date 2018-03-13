package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.exceptions.ParseException;
import nl.uva.js.qlparser.logic.FormBuilder;
import nl.uva.js.qlparser.models.expressions.Form;
import nl.uva.js.qlparser.ui.components.gui.FormPanel;
import nl.uva.js.qlparser.ui.components.gui.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    private static Frame mainFrame;
    private static TextPanel inputPanel;
    private static FormPanel formPanel;
    private static JPanel bottomPanel;
    private static TextPanel console;

    public static Frame getGUI(Form form) {
        inputPanel = new TextPanel(INPUT_WIDTH, FORM_VIEW_HEIGHT, Color.darkGray, true);
        inputPanel.setText(loadDefaultFileContent());

        formPanel = new FormPanel(form, FORM_VIEW_HEIGHT, FORM_WIDTH, FORM_HEIGHT);

        bottomPanel = getBottomPanel();

        mainFrame = getMainFrame();
        mainFrame.add(inputPanel, BorderLayout.LINE_START);
        mainFrame.add(formPanel, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.PAGE_END);

        formPanel.setVisible(true);
        bottomPanel.setVisible(true);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.dispose();
            }
        });

        mainFrame.setTitle(form.getHumanizedName());

        return mainFrame;
    }

    private static String loadDefaultFileContent() {
        try {
            String file = GUIBuilder.class.getClassLoader().getResource(System.getProperty("ql.file")).getFile();
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException | ParseException e) {
            log(e.getMessage());
        }
        return "";
    }

    private static void log(String message) {
        console.setText(console.getText() + "\n" + message);
        console.revalidate();
        console.repaint();
    }

    private static Frame getMainFrame() {
        Frame mainFrame = new Frame();

        mainFrame.setSize(new Dimension(FULL_WIDTH, FULL_HEIGHT));
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());

        return mainFrame;
    }

    private static JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();

        JPanel menuButtons = getMenuButtons();
        console = new TextPanel(FULL_WIDTH, LOG_HEIGHT, Color.black, false);


        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(menuButtons, BorderLayout.PAGE_START);
        bottomPanel.add(console, BorderLayout.PAGE_END);
        console.setVisible(true);

        return bottomPanel;
    }

    private static JPanel getMenuButtons() {
        JPanel menuBar = getButtonPanel(FULL_WIDTH);

        JButton loadButton = getButton("Load QL", 150);
        loadButton.addActionListener(e -> {});

        JButton saveButton = getButton("Save QL", 150);
        saveButton.addActionListener(e -> {});

        JButton processButton = getButton("Process QL", 150);
        processButton.addActionListener(e -> {
            try {
                Form form = FormBuilder.parseFormFromString(inputPanel.getText());
                formPanel.reload(form);
                mainFrame.setTitle(form.getHumanizedName());
            } catch (ParseException exception) {
                log(exception.getMessage());
            }
        });

        JButton exportButton = getButton("Load QL", 250);
        exportButton.addActionListener(e -> {});

        menuBar.add(loadButton);
        menuBar.add(saveButton);
        menuBar.add(processButton);
        menuBar.add(getButtonPanel(210));
        menuBar.add(exportButton);
        menuBar.add(getButtonPanel(220));
        return menuBar;
    }

    private static JButton getButton(String text, int width) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, BUTTON_HEIGHT));
        return button;
    }

    private static JPanel getButtonPanel(int i) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(i, BUTTON_HEIGHT));
        panel.setBackground(Color.gray);
        return panel;
    }
}
