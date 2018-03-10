package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.logic.FormBuilder;
import nl.uva.js.qlparser.models.expressions.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private static Frame mainFrame;
    private static JPanel inputPanel;
    private static JPanel formPanel;
    private static JPanel bottomPanel;
    private static JPanel console;
    private static JPanel contentPanel;

    public static Frame getGUI(Form form) {
        mainFrame   = getMainFrame();
        inputPanel  = getTextPanel(INPUT_WIDTH, FORM_VIEW_HEIGHT, Color.darkGray);
        formPanel   = getFormPanel(form);
        bottomPanel = getBottomPanel();

        setInput(loadDefaultFileContent());

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

    private static void setInput(String ql) {
        TextArea input = ((TextArea) inputPanel.getComponent(0));
        input.setText(ql);
    }

    private static String loadDefaultFileContent() {
        try {
            String file = GUIBuilder.class.getClassLoader().getResource(System.getProperty("ql.file")).getFile();
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            log(e.getMessage());
        }
        return "";
    }

    private static void log(String message) {
        System.out.println("HAI");
        TextArea log = ((TextArea) console.getComponent(0));
        log.setText(log.getText() + "\n" + message);
        log.revalidate();
        log.repaint();
    }

    private static Frame getMainFrame() {
        Frame mainFrame = new Frame();

        mainFrame.setSize(new Dimension(FULL_WIDTH, FULL_HEIGHT));
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());

        return mainFrame;
    }

    private static JPanel getFormPanel(Form form) {
        contentPanel = new JPanel();
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
        formPanel.setBackground(Color.gray);
        formPanel.add(scrollPane);

        return formPanel;
    }

    private static JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();

        JPanel menuButtons = getMenuButtons();
        console = getTextPanel(FULL_WIDTH, LOG_HEIGHT, Color.black);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(menuButtons, BorderLayout.PAGE_START);
        bottomPanel.add(console, BorderLayout.PAGE_END);
        console.setVisible(true);

        return bottomPanel;
    }

    private static JPanel getMenuButtons() {
        JPanel menuBar = getButtonPanel(FULL_WIDTH);

        JButton loadButton = getButton("Load QL", 150);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton saveButton = getButton("Save QL", 150);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton processButton = getButton("Process QL", 150);
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadForm();
            }
        });

        JButton exportButton = getButton("Load QL", 250);
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuBar.add(loadButton);
        menuBar.add(saveButton);
        menuBar.add(processButton);
        menuBar.add(getButtonPanel(210));
        menuBar.add(exportButton);
        menuBar.add(getButtonPanel(220));
        return menuBar;
    }

    private static void reloadForm() {
        contentPanel.removeAll();

        Form newForm = FormBuilder.parseFormFromString(getInput());

        List<Component> components = newForm.getComponents();
        components.forEach(contentPanel::add);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private static String getInput() {
        TextArea input = ((TextArea) inputPanel.getComponent(0));
        return input.getText();

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

    private static JPanel getTextPanel(int width, int height, Color color) {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.gray);

        TextArea textArea = new TextArea("",0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setPreferredSize(new Dimension(width, height));
        textArea.setBackground(color);
        textArea.setForeground(Color.white);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        textPanel.add(textArea);
        textPanel.setVisible(true);

        return textPanel;
    }
}
