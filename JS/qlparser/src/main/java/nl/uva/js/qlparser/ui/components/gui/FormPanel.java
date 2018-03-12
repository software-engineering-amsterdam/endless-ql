package nl.uva.js.qlparser.ui.components.gui;

import nl.uva.js.qlparser.models.expressions.Form;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormPanel extends JPanel {

    private JPanel formContent;

    public FormPanel(Form form, int viewHeight, int formWidth, int formHeight){
        formContent = new JPanel();
        formContent.setPreferredSize(new Dimension(formWidth, formHeight));
        formContent.setLayout(new FlowLayout(FlowLayout.CENTER));

        List<Component> components = form.getComponents();
        components.forEach(formContent::add);

        int panelHeight = viewHeight - 5;

        JScrollPane scrollPane = new JScrollPane(formContent);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(2, 5, formWidth - 15, panelHeight);

        this.setLayout(null);
        this.setPreferredSize(new Dimension(formWidth, panelHeight));
        this.setBackground(Color.gray);
        this.add(scrollPane);
    }

    public void reload(Form form) {
        formContent.removeAll();

        List<Component> components = form.getComponents();
        components.forEach(formContent::add);

        formContent.revalidate();
        formContent.repaint();
    }
}
