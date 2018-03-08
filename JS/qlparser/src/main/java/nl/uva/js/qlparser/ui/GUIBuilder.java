package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.models.expressions.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class GUIBuilder {

    private static final int FORM_VIEW_HEIGHT = 700;

    private static final int BUTTON_HEIGHT = 50;
    private static final int LOG_HEIGHT    = 100;
    private static final int FOOTER_HEIGHT = BUTTON_HEIGHT + LOG_HEIGHT;

    private static final int FULL_WIDTH  = 1200;
    private static final int FULL_HEIGHT = FORM_VIEW_HEIGHT + FOOTER_HEIGHT;

    public static Frame getGUI(Form form) {
        Frame mainFrame = getMainFrame();

        JPanel inputPane  = getInputPanel();
        JPanel formPanel  = getFormPanel(form);
        JPanel bottomPane = getBottomPanel();

        mainFrame.add(inputPane, BorderLayout.LINE_START);
        mainFrame.add(formPanel, BorderLayout.CENTER);
        mainFrame.add(bottomPane, BorderLayout.PAGE_END);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.dispose();
            }
        });

        return mainFrame;
    }

    private static Frame getMainFrame() {
        Frame mainFrame = new Frame();

        mainFrame.setSize(new Dimension(FULL_WIDTH, FULL_HEIGHT));
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setVisible(true);

        return mainFrame;
    }

    private static JPanel getInputPanel() {
        JPanel inputPanel  = new JPanel();

        TextArea inputArea = new TextArea("",0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        inputArea.setPreferredSize((new Dimension(500, 700)));

        inputPanel.add(inputArea);

        return inputPanel;
    }

    private static JPanel getFormPanel(Form form) {
        JPanel contentPanel    = new JPanel();
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        JPanel formPanel       = new JPanel(null);

        contentPanel.setPreferredSize(new Dimension(700, 3000)); // TODO; dynamic height
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        List<Component> components = form.getComponents();
        components.forEach(contentPanel::add);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(2, 5, 685    , 695);

        formPanel.setPreferredSize(new Dimension(700, 695));
        formPanel.add(scrollPane);

        return formPanel;
    }

    private static JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();

        JPanel buttonPanel = getButtonPanel();
        JPanel logPanel    = getLogPanel();

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.PAGE_START);
        bottomPanel.add(logPanel, BorderLayout.PAGE_END);
        bottomPanel.setVisible(true);

        return bottomPanel;
    }

    private static JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1200, 50));
        return buttonPanel;
    }

    private static JPanel getLogPanel() {
        JPanel logPanel = new JPanel();

        TextArea logArea = new TextArea("",0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        logArea.setPreferredSize((new Dimension(1200, 100)));

        logPanel.add(logArea);

        return logPanel;
    }
}
