package qls.logic.builders;

import ql.ast.model.Form;
import ql.gui.model.QuestionModel;
import ql.gui.view.QuestionView;
import ql.gui.view.widgets.*;
import ql.logic.collectors.CollectQuestionModelsVisitor;
import qls.ast.model.*;
import qls.ast.model.properties.*;
import qls.ast.model.properties.widgets.*;
import qls.ast.visitors.AbstractASTTraverse;
import qls.gui.view.PagePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GuiBuilder extends AbstractASTTraverse<JComponent> {

    private Stylesheet stylesheet;
    private List<JComponent> pages;

    private List<QuestionModel> questionModels;
    private LinkedHashMap<String, QuestionModel> questionModelsMap = new LinkedHashMap<>();

    // styles stack per data type
    static class TypeDefinitionLayer {
        WidthProperty width;
        FontProperty font;
        FontSizeProperty fontSize;
        ColorProperty color;
        Widget widget;
    }

    private HashMap<String, Stack<TypeDefinitionLayer>> typeDefinitions;

    // returns a linked list of panels (pages)
    public GuiBuilder(Form form, Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
        this.typeDefinitions = new HashMap<>();

        // stack setup
        this.typeDefinitions.put("boolean", new Stack<>());
        this.typeDefinitions.put("string", new Stack<>());
        this.typeDefinitions.put("integer", new Stack<>());
        this.typeDefinitions.put("decimal", new Stack<>());
        this.typeDefinitions.put("money", new Stack<>());
        this.typeDefinitions.put("date", new Stack<>());

        // questions setup
        CollectQuestionModelsVisitor qlCollectQuestionModelsVisitor = new CollectQuestionModelsVisitor();
        this.questionModels = qlCollectQuestionModelsVisitor.getQuestionModels(form);

        // hashmap - easier to lookup
        for (QuestionModel model : this.questionModels) {
            this.questionModelsMap.put(model.getVariableName(), model);
        }
    }

    public List<JComponent> getPages() {
        this.visit(this.stylesheet);
        return this.pages;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    @Override
    public JComponent visit(Stylesheet stylesheet) {

        this.pages = new ArrayList<>();

        for (Page page : stylesheet.getPages()) {
            this.pages.add(visit(page));
        }

        JPanel panel = new JPanel();
        for (JComponent component : this.pages) {
            panel.add(component);
        }

        return panel;
    }

    @Override
    public JComponent visit(Page page) {
        List<JComponent> components = new ArrayList<>();

        this.definitionsStacksPush(page.getDefaultDefinitions());

        // visit children
        List<BlockElement> elements = page.getElements();
        for (BlockElement element : elements) {
            if (element instanceof QuestionDefinition) {
                components.add(visit((QuestionDefinition) element));
            }
            if (element instanceof Section) {
                components.add(visit((Section) element));
            }
        }

        this.definitionsStacksPop(page.getDefaultDefinitions());

        return new PagePanel(page.getName(), components);
    }

    @Override
    public JComponent visit(Section section) {

        JPanel sectionPanel = new JPanel();

        JLabel labelComponent = new JLabel(section.getName());

        sectionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        sectionPanel.add(labelComponent, gridBagConstraints);

        int i = 1;

        // visit children
        List<BlockElement> elements = section.getElements();
        for (BlockElement element : elements) {
            gridBagConstraints.gridy = i;
            if (element instanceof QuestionDefinition) {
                sectionPanel.add(visit((QuestionDefinition) element));
            }
            if (element instanceof Section) {
                sectionPanel.add(visit((Section) element));
            }
            i++;
        }

        sectionPanel.setVisible(true);

        return sectionPanel;
    }

    @Override
    public JComponent visit(QuestionDefinition questionDefinition) {
        QuestionModel questionModel = this.questionModelsMap.get(questionDefinition.getName());

        ql.gui.view.Widget qlWidget = null;

        // get type and determine the right widget
        String type = questionModel.getOriginalDataTypeDeclaration().getIdentifier();

        // figure out the widget

        // if explicitly declared widget
        if (questionDefinition.getWidget() != null) {
            // use this widget
            qlWidget = this.getQlWidgetForQLS(questionDefinition.getWidget().getName(), type, questionModel);
            if (qlWidget == null) {
                throw new RuntimeException("Widget mismatch");
            }
            // if a default widget
        } else if (!this.typeDefinitions.get(type).isEmpty()) {
            TypeDefinitionLayer layer = this.typeDefinitions.get(type).peek();
            if (layer.widget != null) {
                // use this
                qlWidget = this.getQlWidgetForQLS(layer.widget.getName(), type, questionModel);
                if (qlWidget == null) {
                    throw new RuntimeException("Widget mismatch");
                }
            }

        } else {
            // default widget
            qlWidget = this.getQlWidgetForQLS(null, type, questionModel);
            if (qlWidget == null) {
                throw new RuntimeException("Widget mismatch");
            }
        }

        // determine the other properties (they are only in default declarations) - widget configuration

        if (!this.typeDefinitions.get(type).isEmpty()) {
            TypeDefinitionLayer layer = this.typeDefinitions.get(type).peek();
            if (layer.width != null) {
                qlWidget.setWidth(layer.width.getValue());
            }
            if (layer.font != null) {
                qlWidget.setFont(layer.font.getValue());
            }
            if (layer.fontSize != null) {
                qlWidget.setFontSize(layer.fontSize.getValue());
            }
            if (layer.color != null) {
                qlWidget.setColor(layer.color.getValue());
            }
        }

        if (qlWidget instanceof IntegerSpinnerWidget) {
            SpinboxWidget actualWidget = (SpinboxWidget) questionDefinition.getWidget();
            if (actualWidget != null && actualWidget.getParameters() != null) {
                ((IntegerSpinnerWidget) qlWidget).setMin(actualWidget.getParameters().getMin());
                ((IntegerSpinnerWidget) qlWidget).setMax(actualWidget.getParameters().getMax());
                ((IntegerSpinnerWidget) qlWidget).setStep(actualWidget.getParameters().getStep());
            }
        }

        if (qlWidget instanceof BooleanRadioWidget) {
            RadioWidget actualWidget = (RadioWidget) questionDefinition.getWidget();
            if (actualWidget != null && actualWidget.getParameters() != null) {
                ((BooleanRadioWidget) qlWidget).setYesText(actualWidget.getParameters().getValueTrue());
                ((BooleanRadioWidget) qlWidget).setNoText(actualWidget.getParameters().getValueFalse());
            }
        }

        return new QuestionView(questionModel, qlWidget);
    }

    private ql.gui.view.Widget getQlWidgetForQLS(String widgetName, String questionDataType, QuestionModel questionModel) {
        if (questionDataType.equals("boolean")) {
            if (widgetName.equals("checkbox"))
                return new BooleanCheckboxWidget(questionModel);
            if (widgetName.equals("dropdown"))
                return new BooleanCheckboxWidget(questionModel);    // TODO: implement widget
            if (widgetName.equals("radio"))
                return new BooleanRadioWidget(questionModel);
            // default - radio
            return new BooleanRadioWidget(questionModel);
        }
        if (questionDataType.equals("integer")) {
            if (widgetName.equals("slider"))
                return new IntegerSpinnerWidget(questionModel);     // TODO: implement widget
            if (widgetName.equals("spinbox"))
                return new IntegerSpinnerWidget(questionModel);
            if (widgetName.equals("text"))
                return new IntegerFieldWidget(questionModel);
            // default
            return new IntegerFieldWidget(questionModel);
        }
        if (questionDataType.equals("string")) {
            if (widgetName.equals("text"))
                return new TextFieldWidget(questionModel);
            // default
            return new TextFieldWidget(questionModel);
        }
        if (questionDataType.equals("money")) {
            return new MoneyFieldWidget(questionModel);
        }
        if (questionDataType.equals("date")) {
            return new DatePickerWidget(questionModel);
        }
        if (questionDataType.equals("decimal")) {
            return new DecimalFieldWidget(questionModel);
        }
        return null;
    }


    private void definitionsStacksPush(List<DefaultDefinition> defaultDefinitions) {
        if (!defaultDefinitions.isEmpty()) {
            for (DefaultDefinition definition : defaultDefinitions) {

                Stack<TypeDefinitionLayer> typeStack = this.typeDefinitions.get(definition.getDataType());
                TypeDefinitionLayer layer;

                if (!typeStack.isEmpty()) {
                    layer = typeStack.peek();
                } else {
                    layer = new TypeDefinitionLayer();
                }

                for (TypeProperty property : definition.getTypeProperties()) {
                    if (property instanceof WidthProperty)
                        layer.width = (WidthProperty) property;
                    if (property instanceof FontProperty)
                        layer.font = (FontProperty) property;
                    if (property instanceof FontSizeProperty)
                        layer.fontSize = (FontSizeProperty) property;
                    if (property instanceof ColorProperty)
                        layer.color = (ColorProperty) property;
                    if (property instanceof Widget) {
                        if ((definition.getDataType().equals("boolean")
                                && (property instanceof CheckboxWidget
                                || property instanceof DropdownWidget
                                || property instanceof RadioWidget))
                                || (definition.getDataType().equals("integer")
                                && (property instanceof SliderWidget
                                || property instanceof SpinboxWidget
                                || property instanceof TextWidget))
                                || (definition.getDataType().equals("integer") && (property instanceof TextWidget))
                                ) {
                            layer.widget = (Widget) property;
                        } else {
                            throw new RuntimeException("Illegal type to widget assignment");
                        }
                    }
                }

                typeStack.push(layer);
            }
        }
    }

    private void definitionsStacksPop(List<DefaultDefinition> defaultDefinitions) {
        if (!defaultDefinitions.isEmpty()) {
            for (DefaultDefinition definition : defaultDefinitions) {
                Stack<TypeDefinitionLayer> typeStack = this.typeDefinitions.get(definition.getDataType());
                typeStack.pop();
            }
        }
    }

}
