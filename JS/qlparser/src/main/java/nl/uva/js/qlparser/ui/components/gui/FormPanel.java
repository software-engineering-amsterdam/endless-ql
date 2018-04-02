package nl.uva.js.qlparser.ui.components.gui;

import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.enums.Property;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;
import nl.uva.js.qlparser.models.qls.style.WidgetStyle;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static nl.uva.js.qlparser.ui.components.form.ComponentBuilder.buildSectionHeader;

public class FormPanel extends JPanel {

    private final JPanel formContent;

    private Form form;

    private LinkedHashMap<String, Component> qlComponentsByName;

    private LinkedHashMap<String, List<Component>> pages;
    private Map<DataType, DefaultStyle> defaultStyles;

    public FormPanel(int viewHeight, int formWidth, int formHeight) {
        qlComponentsByName = new LinkedHashMap<>();
        formContent        = new JPanel();

        formContent.setPreferredSize(new Dimension(formWidth, formHeight));
        formContent.setLayout(new FlowLayout(FlowLayout.CENTER));

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

        form.getComponents().forEach(component ->  {
            qlComponentsByName.put(component.getName(), component);
            formContent.add(component);
        });
    }

    /**
     * Resets the content panel and renders the given form
     */
    public void apply(Form form) {
        formContent.removeAll();
        qlComponentsByName.clear();

        NonNullRun.consumer(form, __ -> {
            loadComponents(form);

            formContent.revalidate();
            formContent.repaint();
        });
    }

    /**
     * Reorders and styles the current components based on the given stylesheet
     */
    public void apply(Stylesheet stylesheet) {
        pages         = new LinkedHashMap<>();
        defaultStyles = getDefaultStyles();

        stylesheet.getDefaultStyles().forEach(style -> defaultStyles.replace(style.getDataType(), style));

        stylesheet.getPages().forEach(page -> pages.put(page.getName(), createPageComponents(page)));

        // Set to first page
        setPage(pages.keySet().iterator().next());
    }

    /**
     *  Preset default values for supported widget types, as fallback.
     */
    private Map<DataType, DefaultStyle> getDefaultStyles() {
        Map<DataType, DefaultStyle> defaultStyles = new HashMap<>();
        defaultStyles.put(DataType.BOOLEAN, DefaultStyle.builder().widgetType(WidgetType.CHECKBOX).build());
        defaultStyles.put(DataType.INTEGER, DefaultStyle.builder().widgetType(WidgetType.TEXT).build());

        return defaultStyles;
    }

    public void setPage(String pageName) {
        formContent.removeAll();

        pages.get(pageName).forEach(formContent::add);

        formContent.revalidate();
        formContent.repaint();
    }

    private List<Component> createPageComponents(Page page) {
        List<Component> pageComponents = new LinkedList<>();

        page.getSections().forEach((Section section) -> {
            Component sectionHeader = buildSectionHeader(section.getName());
            pageComponents.add(sectionHeader);

            section.getExpressionReferences().forEach(reference -> {
                Component component = qlComponentsByName.get(reference.getName());
                updateWidget(component, reference.getWidgetType(), reference.getWidgetStyle());
                pageComponents.add(component);
            });
        });

        return pageComponents;
    }

    /**
     *  Change widget to match style and widget type
     */
    private void updateWidget(Component component, WidgetType widgetType, WidgetStyle widgetStyle) {
        FormExpression expression = form.getExpressionsByName().get(component.getName());

        NonNullRun.consumer(expression.getVariable(), variable -> {
            Panel formComponent = (Panel) component;
            Component inputField = formComponent.getComponent(1);

            Component newWidget = updateWidgetType(widgetType, variable, formComponent, inputField, widgetStyle);
            updateWidgetStyle(widgetStyle, newWidget, variable.getDataType());

            component.revalidate();
            component.repaint();
        });
    }

    private Component updateWidgetType(WidgetType widgetType, Variable variable, Panel formComponent, Component input, WidgetStyle widgetStyle) {
        DataType dataType = variable.getDataType();
        Component newWidget = input;

        if (needToReplaceWidget(widgetType, dataType)) {
            formComponent.remove(input);

            // Replace widget by default widget type, unless the widget-level type overrides it.
            if (widgetType == null) {
                DefaultStyle defaultStyle = defaultStyles.get(dataType);
                WidgetType defaultWidgetType = defaultStyle.getWidgetType();
                newWidget = defaultWidgetType.createWidget(variable, widgetStyle);
            } else {
                newWidget = widgetType.createWidget(variable, widgetStyle);
            }

            formComponent.add(newWidget);
        }
        return newWidget;
    }

    private boolean needToReplaceWidget(WidgetType widgetType, DataType dataType) {
        WidgetType defaultWidgetType = defaultStyles.containsKey(dataType) ? defaultStyles.get(dataType).getWidgetType() : null;

        return defaultWidgetType != null || widgetType != null;
    }

    private void updateWidgetStyle(WidgetStyle widgetStyle, Component inputField, DataType dataType) {
        NonNullRun.consumer(getRules(widgetStyle, dataType), rules -> {
            if (rules.containsKey(Property.FONTCOLOR)) {
                inputField.setForeground(Color.decode(rules.get(Property.FONTCOLOR)));
            }

            if (rules.containsKey(Property.FONTSTYLE) | rules.containsKey(Property.FONTTYPE)) {
                inputField.setFont(getNewFont(rules));
            }

            if (rules.containsKey(Property.WIDGETCOLOR)) {
                inputField.setBackground(Color.decode(rules.get(Property.WIDGETCOLOR)));
            }

            inputField.revalidate();
            inputField.repaint();
        });
    }

    private Map<Property, String> getRules(WidgetStyle widgetStyle, DataType dataType) {
        if (widgetStyle != null) return widgetStyle.getStyleRules();
        else {
//            If the default style is not available, return null, if it is available, return the style rules
            return NonNullRun.function(defaultStyles.get(dataType), defaultStyle ->
                    NonNullRun.function(defaultStyle.getWidgetStyle(), WidgetStyle::getStyleRules));
        }
    }

    private Font getNewFont(Map<Property, String> rules) {
        String fontType     = rules.getOrDefault(Property.FONTTYPE, new JLabel().getFont().getName());
        int fontStyle       = Font.PLAIN;
        int defaultFontSize = 13;

        if (rules.containsKey(Property.FONTSTYLE)) {
            switch (rules.get(Property.FONTSTYLE).toLowerCase()) {
                case "bold":
                    fontStyle = Font.BOLD;
                    break;
                case "italic":
                    fontStyle = Font.ITALIC;
                    break;
                case "monospaced":
                    break;
            }
        }

        return new Font(fontType, fontStyle, defaultFontSize);
    }

    public List<String> getDuplicateLabelQuestions() {
        LinkedList<String> duplicateLabels = new LinkedList<>();
        HashSet<String> labels = new HashSet<>();

        for (FormExpression formExpression : form.getFormExpressions()) {
            String label = formExpression.getLabel();
            if(label != null && labels.contains(label)) {
                duplicateLabels.add(formExpression.getVariable().getName());
            }
            labels.add(label);
        }

        return duplicateLabels;
    }
}
