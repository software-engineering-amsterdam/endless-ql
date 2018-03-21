package nl.uva.js.qlparser.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.uva.js.qlparser.exceptions.ParseException;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.logic.FormBuilder;
import nl.uva.js.qlparser.logic.StylesheetBuilder;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.ui.components.gui.ButtonBar;
import nl.uva.js.qlparser.ui.components.gui.FormPanel;
import nl.uva.js.qlparser.ui.components.gui.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class GUIBuilder {
    public static final int BUTTON_HEIGHT = 50;

    private static final int FORM_VIEW_HEIGHT = 700;
    private static final int LOG_HEIGHT    = 100;
    private static final int FOOTER_HEIGHT = BUTTON_HEIGHT + LOG_HEIGHT;

    private static final int FORM_HEIGHT = 3000; // TODO: Dynamic height
    public static final int FORM_WIDTH  = 700;
    public static final int INPUT_WIDTH = 350;

    public static final int FULL_HEIGHT = FORM_VIEW_HEIGHT + FOOTER_HEIGHT;
    public static final int FULL_WIDTH  = INPUT_WIDTH + FORM_WIDTH + INPUT_WIDTH;

    private static Frame mainFrame;
    private static TextPanel qlPanel;
    private static TextPanel qlsPanel;
    private static FormPanel formPanel;
    private static JPanel bottomPanel;
    private static JPanel topPanel;
    private static TextPanel console;

    private static Form globalForm;

    public static Frame getGUI(Form form) {
        globalForm = form;

        qlPanel = new TextPanel(INPUT_WIDTH, FORM_VIEW_HEIGHT, Color.darkGray, true);
        qlPanel.setText(loadDefaultFile("ql.file"));

        qlsPanel = new TextPanel(INPUT_WIDTH, FORM_VIEW_HEIGHT, Color.darkGray, true);
        qlsPanel.setText(loadDefaultFile("qls.file"));

        formPanel = new FormPanel(form, FORM_VIEW_HEIGHT, FORM_WIDTH, FORM_HEIGHT);

        topPanel = getTopPanel(null);
        bottomPanel = getBottomPanel();

        mainFrame = getMainFrame();
        mainFrame.add(topPanel, BorderLayout.PAGE_START);
        mainFrame.add(qlPanel, BorderLayout.LINE_START);
        mainFrame.add(formPanel, BorderLayout.CENTER);
        mainFrame.add(qlsPanel, BorderLayout.LINE_END);
        mainFrame.add(bottomPanel, BorderLayout.PAGE_END);

        formPanel.setVisible(true);
        bottomPanel.setVisible(true);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.dispose();
            }
        });

        mainFrame.setTitle(globalForm.getHumanizedName());

        return mainFrame;
    }

    private static String loadDefaultFile(String property) {
        try {
            return NonNullRun.function(System.getProperty(property), qlfile -> {
                try {
                    return new String(Files.readAllBytes(Paths.get(GUIBuilder.class.getClassLoader().getResource(qlfile).getFile())));
                } catch (IOException e) {
                    return "";
                }
            });
        } catch (ParseException e) {
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

        JPanel menuButtons = getBottomButtons();
        console = new TextPanel(FULL_WIDTH, LOG_HEIGHT, Color.black, false);


        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(menuButtons, BorderLayout.PAGE_START);
        bottomPanel.add(console, BorderLayout.PAGE_END);
        console.setVisible(true);

        return bottomPanel;
    }

    private static JPanel getTopPanel(LinkedList<Page> pages) {
        ButtonBar topPanel = new ButtonBar();

        if (null != pages) {
            for (Page page : pages) {
                JButton pageButton = getButton(page.getName(), 80);
                pageButton.addActionListener(e -> {
                    formPanel.setPage(page.getName());
                });

                topPanel.centerPanel.add(pageButton);
            }
        }

        return topPanel;
    }

    //TODO refactor
    private static JPanel getBottomButtons() {
        ButtonBar buttonBar = new ButtonBar();

        JButton qlLoadButton = getButton("Load", 100);
        qlLoadButton.addActionListener(e -> {});

        JButton qlSaveButton = getButton("Save", 100);
        qlSaveButton.addActionListener(e -> {});

        JButton qlProcessButton = getButton("Process", 100);
        qlProcessButton.addActionListener(e -> {
            try {
                globalForm = FormBuilder.parseFormFromString(qlPanel.getText());
                formPanel.apply(globalForm);
                setPageButtons(null);
                mainFrame.setTitle(globalForm.getHumanizedName());
            } catch (ParseException exception) {
                log(exception.getMessage());
            }
        });

        JButton qlsLoadButton = getButton("Load", 100);
        qlsLoadButton.addActionListener(e -> {});

        JButton qlsSaveButton = getButton("Save", 100);
        qlsSaveButton.addActionListener(e -> {});

        JButton qlsProcessButton = getButton("Process", 100);
        qlsProcessButton.addActionListener(e -> {
            try {
                Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromString(qlsPanel.getText());
                // CHECKER HERE //
                formPanel.apply(stylesheet);
                setPageButtons(stylesheet.getPages());
            } catch (ParseException exception) {
                log(exception.getMessage());
            }
        });

        JButton exportButton = getButton("Export form", 250);
        exportButton.addActionListener(e -> {
            try {
                ObjectMapper mapper = new ObjectMapper()
                        .registerModule(new JavaTimeModule())
                        .registerModule(new Jdk8Module());
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                System.out.println(mapper.writer().writeValueAsString(globalForm));
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        });

        buttonBar.leftPanel.add(qlLoadButton);
        buttonBar.leftPanel.add(qlSaveButton);
        buttonBar.leftPanel.add(qlProcessButton);

        buttonBar.centerPanel.add(exportButton);

        buttonBar.rightPanel.add(qlsLoadButton);
        buttonBar.rightPanel.add(qlsSaveButton);
        buttonBar.rightPanel.add(qlsProcessButton);

        return buttonBar;
    }

    private static void setPageButtons(LinkedList<Page> pages) {
        topPanel.removeAll();
        topPanel.add(getTopPanel(pages));
        topPanel.revalidate();
        topPanel.repaint();
    }

    public static JButton getButton(String text, int width) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, BUTTON_HEIGHT));
        return button;
    }

    public static JPanel getButtonPanel(int width) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, BUTTON_HEIGHT));
        panel.setBackground(Color.gray);
        return panel;
    }
}
