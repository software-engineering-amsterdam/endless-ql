package nl.uva.js.qlparser.ui.components.gui;

import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class FormPanel extends JPanel {

    private final JPanel formContent;
    private LinkedList<Component> components;

    public FormPanel(Form form, int viewHeight, int formWidth, int formHeight){
        formContent = new JPanel();
        formContent.setPreferredSize(new Dimension(formWidth, formHeight));
        formContent.setLayout(new FlowLayout(FlowLayout.CENTER));

        form.getComponents().forEach(formContent::add);

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

    public void apply(Form form) {
        formContent.removeAll();
        components.clear();

        components = form.getComponents();
        components.forEach(formContent::add);

        formContent.revalidate();
        formContent.repaint();
    }

    public void apply(Stylesheet stylesheet) {
//        formContent.removeAll();
//
//        LinkedList<Component> styledComponents = components.clone();
//        components.forEach(formContent::add);

        formContent.revalidate();
        formContent.repaint();
    }
}
