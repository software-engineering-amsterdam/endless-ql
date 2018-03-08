package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.models.expressions.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class GUIBuilder {
    public static Frame getGUI(Form form) {
        Frame mainFrame = new Frame();
        mainFrame.setSize(new Dimension(1200, 730));
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setLayout(new BorderLayout());

        JPanel inputPane = new JPanel();
        TextArea inputArea = new TextArea("",0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        inputArea.setPreferredSize((new Dimension(500, 700)));

        inputPane.add(inputArea);

        JPanel formPanel = getFormPanel(form);

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BorderLayout());

        JPanel buttonPane = new JPanel();
        buttonPane.setPreferredSize(new Dimension(1200, 50));

        JPanel logPane = new JPanel();
        TextArea logArea = new TextArea("",0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        logArea.setPreferredSize((new Dimension(1200, 100)));
        logPane.add(logArea);

        bottomPane.add(buttonPane, BorderLayout.PAGE_START);
        bottomPane.add(logPane, BorderLayout.PAGE_END);

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

    private static JPanel getFormPanel(Form form) {
        JPanel panel = new JPanel();

        List<Component> components = form.getComponents();
        components.forEach(panel::add);

        panel.setMinimumSize(new Dimension(700, 700));
        panel.setMaximumSize(new Dimension(700, 2000));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return panel;
    }


}
