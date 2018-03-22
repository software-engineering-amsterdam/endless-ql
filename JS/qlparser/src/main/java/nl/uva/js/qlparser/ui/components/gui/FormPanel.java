package nl.uva.js.qlparser.ui.components.gui;

import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.QuestionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static nl.uva.js.qlparser.ui.components.form.ComponentBuilder.buildSectionHeader;

public class FormPanel extends JPanel {

    private final JPanel formContent;

    private LinkedHashMap<String, Component> qlComponentsByName;

    public LinkedHashMap<String, LinkedList<Component>> pages;
    private HashMap<DataType, DefaultStyle> defaultStyles;

    public FormPanel(Form form, int viewHeight, int formWidth, int formHeight){
        formContent = new JPanel();
        formContent.setPreferredSize(new Dimension(formWidth, formHeight));
        formContent.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Initial setup
        loadComponents(form);

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

    /**
     * Gets components for form, saves them by name and adds them to the content panel
     */
    private void loadComponents(Form form) {
        qlComponentsByName = new LinkedHashMap<>();

        for (Component component : form.getComponents()) {
            qlComponentsByName.put(component.getName(), component);
            formContent.add(component);
        }
    }

    /**
     * Resets the content panel and renders the given form
     */
    public void apply(Form form) {
        formContent.removeAll();
        qlComponentsByName.clear();

        loadComponents(form);

        formContent.revalidate();
        formContent.repaint();
    }

    /**
     * Reorders and styles the current components based on the given stylesheet
     */
    public void apply(Stylesheet stylesheet) {
        pages         = new LinkedHashMap<>();
        defaultStyles = new HashMap<>();

        LinkedList<Component> styledComponents;

        stylesheet.getDefaultStyles().forEach(style -> defaultStyles.put(style.getDataType(), style));

        for (Page page : stylesheet.getPages()) {
            pages.put(page.getName(), createPageComponents(page));
        }

        // Set to first page
        setPage(pages.keySet().iterator().next());
    }

    public void setPage(String pageName) {
        formContent.removeAll();

        pages.get(pageName).forEach(formContent::add);

        formContent.revalidate();
        formContent.repaint();
    }

    private LinkedList<Component> createPageComponents(Page page) {
        LinkedList<Component> pageComponents = new LinkedList<>();
        for(Section section : page.getSections()) {
            pageComponents.add(buildSectionHeader(section.getName()));
            for (QuestionReference reference : section.getQuestions()) {
                Component component = qlComponentsByName.get(reference.getName());
                // TODO: Invisible components due to unmet ifblock condition are null, causing all sorts of trouble
                if(null != component) {
                    pageComponents.add(component);
                }
            }
        }
        return pageComponents;
    }
}
