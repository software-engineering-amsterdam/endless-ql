package nl.uva.js.qlparser.ui.components.gui;

import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.ExpressionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;
import nl.uva.js.qlparser.models.qls.style.WidgetStyle;
import nl.uva.js.qlparser.ui.components.form.ComponentBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static nl.uva.js.qlparser.ui.components.form.ComponentBuilder.buildSectionHeader;

public class FormPanel extends JPanel {

    private final JPanel formContent;

    private Form form;

    private LinkedHashMap<String, Component> qlComponentsByName;

    private LinkedHashMap<String, LinkedList<Component>> pages;
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
        this.form = form;
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
            for (ExpressionReference reference : section.getExpressionReferences()) {
                Component component = qlComponentsByName.get(reference.getName());
                updateWidget(component, reference.getWidgetType(), reference.getWidgetStyle());
                pageComponents.add(component);
            }
        }
        return pageComponents;
    }

    //TODO: WIP
    private void updateWidget(Component component, WidgetType widgetType, WidgetStyle widgetStyle) {
        FormExpression expression = form.getExpressionsByName().get(component.getName());
        Variable variable = expression.getVariable();
        if (variable == null) {
            return;
        }

        Panel formComponent = (Panel) component;
        Component input  = formComponent.getComponent(1);

        DataType dataType = expression.getType();

        if (needToReplaceWidget(widgetType, dataType)) {
            formComponent.remove(input);

            if (defaultStyles.containsKey(dataType)) {
                DefaultStyle defaultStyle = defaultStyles.get(dataType);

                switch (defaultStyle.getWidgetType()) {
                    case CHECKBOX:
                        formComponent.add(ComponentBuilder.buildCheckBox(variable));
                        break;
                    case DROPDOWN:
                        formComponent.add(ComponentBuilder.buildDropdown(variable));
                        break;
                    case RADIO:
                        formComponent.add(ComponentBuilder.buildRadioButtons(variable));
                }
            }
        }

        component.revalidate();
        component.repaint();
    }

    private boolean needToReplaceWidget(WidgetType widgetType, DataType dataType) {
        return defaultStyles.containsKey(dataType) || widgetType != null;
    }
}
