package gui;

import gui.widgets.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.model.DefaultStyle;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;
import qls.model.widget.WidgetType;

import java.util.List;

public class GUIQuestion extends VBox implements WidgetVisitor<Node> {

    GUIQuestion(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        // Get the widget type for this question if there is any
        WidgetInterface widget;
        Widget qlsWidget = qlsQuestion.getWidget();
        if (qlsWidget != null) {
            widget = qlsWidget.createWidget(this, symbolTable, question, qlsQuestion, defaultStyles);
        } else {
            // If no default widget is specified in QLS, use the default widget for question type
            WidgetType widgetType = getWidgetTypeForQuestion(defaultStyles, question);
            widget = widgetType.createWidget(this, symbolTable, question, qlsQuestion, defaultStyles);
        }

        Node widgetNode = widget.getNode();

        // Add listeners
        widget.addListeners(symbolTable, question, widget);

        // Add default styles
        setDefaultStyles(defaultStyles, question, qlsQuestion, widget);

        Label label = new Label(question.label);
        label.managedProperty().bind(widgetNode.managedProperty());
        label.visibleProperty().bind(widgetNode.visibleProperty());
        this.getChildren().add(label);
        this.getChildren().add(widgetNode);
        this.setPadding(new Insets(20, 20, 20, 20));
    }

    @Override
    public WidgetInterface visitWidgetTypeInteger(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new IntegerWidget(question);
    }

    @Override
    public WidgetInterface visitWidgetTypeString(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new StringWidget(question);
    }

    @Override
    public WidgetInterface visitWidgetTypeDate(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new DateWidget(question);
    }

    @Override
    public WidgetInterface visitWidgetTypeIntegerSpinBox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new SpinnerIntegerWidget(question);
    }

    @Override
    public WidgetInterface visitWidgetTypeDecimalSpinBox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new SpinnerDecimalWidget(question);
    }

    @Override
    public WidgetInterface visitWidgetTypeMoneySpinBox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new SpinnerMoneyWidget(question);
    }

    @Override
    public WidgetInterface visitWidgetTypeIntegerSlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, int min, int max, int step) {
        return new SliderIntegerWidget(question, min, max, step);
    }

    @Override
    public WidgetInterface visitWidgetTypeDecimalSlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, double min, double max, double step) {
        return new SliderDecimalWidget(question, min, max, step);
    }

    @Override
    public WidgetInterface visitWidgetTypeMoneySlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, double min, double max, double step) {
        return new SliderMoneyWidget(question, min, max, step);
    }

    @Override
    public WidgetInterface visitWidgetTypeBooleanRadio(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel) {
        return new RadioWidget(question, falseLabel, trueLabel);
    }

    @Override
    public WidgetInterface visitWidgetTypeBooleanCheckbox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return visitWidgetTypeBoolean(symbolTable, question, qlsQuestion, defaultStyles);
    }

    @Override
    public WidgetInterface visitWidgetTypeBooleanDropdown(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel) {
        return  new DropdownWidget(question, List.of(falseLabel, trueLabel));
    }

    public WidgetInterface visitWidgetTypeDecimal(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new DecimalWidget(question);
    }

    public WidgetInterface visitWidgetTypeMoney(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new MoneyWidget(question);
    }

    public WidgetInterface visitWidgetTypeBoolean(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return new CheckboxWidget(question);
    }

    private WidgetType getWidgetTypeForQuestion(List<DefaultStyle> defaultStyles, Question question) {
        WidgetType widgetType = WidgetType.valueOf(question.type.toString());
        for (DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.type.equals(question.type) && defaultStyle.getWidget() != null) {
                // Get the last one because that is the most local one
                widgetType = defaultStyle.getWidget().type;
            }
        }
        return widgetType;
    }

    private void setDefaultStyles(List<DefaultStyle> defaultStyles, Question question, qls.model.Question qlsQuestion, WidgetInterface widget) {
        // Set defaults for questions of a particular type
        for (DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.type.equals(question.type)) {
                for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
                    styleAttribute.apply(widget);
                }
            }
        }

        // Set defaults for this question specifically
        for (StyleAttribute styleAttribute : qlsQuestion.styleAttributes) {
            styleAttribute.apply(widget);
        }
    }
}
