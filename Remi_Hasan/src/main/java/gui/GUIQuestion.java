package gui;

import gui.widgets.*;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.model.DefaultStyle;
import qls.model.style.StyleAttribute;
import qls.model.widget.WidgetType;

import java.util.List;

public class GUIQuestion extends VBox {

    GUIQuestion(SymbolTable symbolTable, List<DefaultStyle> defaultStyles, Question question) {
        // Get the widget type for this question if there is any
        WidgetType widgetType = getWidgetTypeForQuestion(defaultStyles, question);

        Control widget;
        switch (question.type) {
            case INTEGER:
                widget = createIntegerWidget(widgetType, symbolTable, question, defaultStyles);
                break;
            case STRING:
                widget = createStringWidget(widgetType, symbolTable, question, defaultStyles);
                break;
            case DATE:
                widget = createDateWidget(widgetType, symbolTable, question, defaultStyles);
                break;
            case DECIMAL:
                widget = createDecimalWidget(widgetType, symbolTable, question, defaultStyles);
                break;
            case MONEY:
                widget = createMoneyWidget(widgetType, symbolTable, question, defaultStyles);
                break;
            case BOOLEAN:
                widget = createBooleanWidget(widgetType, symbolTable, question, defaultStyles);
                break;
            default:
                return;
        }

        Label label = new Label(question.text);
        label.managedProperty().bind(widget.managedProperty());
        label.visibleProperty().bind(widget.visibleProperty());
        this.getChildren().add(label);
        this.getChildren().add(widget);
        this.setPadding(new Insets(20, 20, 20, 20));
    }

    private Control createIntegerWidget(WidgetType widgetType, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles){
        Control widget;
        switch (widgetType){
            case SLIDER:
                // TODO
                throw new UnsupportedOperationException("Not implemented");
            case SPINBOX:
                SpinnerIntegerWidget spinnerWidget = new SpinnerIntegerWidget(question);
                spinnerWidget.addListeners(symbolTable, question, spinnerWidget);
                setDefaultStyles(defaultStyles, question, spinnerWidget);
                widget = spinnerWidget;
                break;
            case TEXTBOX: // textbox => default
            default:
                IntegerWidget integerWidget = new IntegerWidget(question);
                integerWidget.addListeners(symbolTable, question, integerWidget);
                setDefaultStyles(defaultStyles, question, integerWidget);
                widget = integerWidget;
        }
        return widget;
    }

    private Control createStringWidget(WidgetType widgetType, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles){
        StringWidget stringWidget = new StringWidget(question);
        stringWidget.addListeners(symbolTable, question, stringWidget);
        setDefaultStyles(defaultStyles, question, stringWidget);
        return stringWidget;
    }

    private Control createDateWidget(WidgetType widgetType, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles){
        DateWidget dateWidget = new DateWidget(question);
        dateWidget.addListeners(symbolTable, question, dateWidget);
        setDefaultStyles(defaultStyles, question, dateWidget);
        return dateWidget;
    }

    private Control createDecimalWidget(WidgetType widgetType, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles){
        Control widget;
        switch(widgetType){
            case SPINBOX:
                SpinnerDecimalWidget spinnerWidget = new SpinnerDecimalWidget(question);
                spinnerWidget.addListeners(symbolTable, question, spinnerWidget);
                setDefaultStyles(defaultStyles, question, spinnerWidget);
                widget = spinnerWidget;
                break;
            case TEXTBOX: // textbox => default
            default:
                DecimalWidget decimalWidget = new DecimalWidget(question);
                decimalWidget.addListeners(symbolTable, question, decimalWidget);
                setDefaultStyles(defaultStyles, question, decimalWidget);
                widget = decimalWidget;
        }
        return widget;
    }

    private Control createMoneyWidget(WidgetType widgetType, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles){
        Control widget;
        switch(widgetType){
            case SPINBOX:
                SpinnerMoneyWidget spinnerWidget = new SpinnerMoneyWidget(question);
                spinnerWidget.addListeners(symbolTable, question, spinnerWidget);
                setDefaultStyles(defaultStyles, question, spinnerWidget);
                widget = spinnerWidget;
                break;
            case TEXTBOX: // textbox => default
            default:
                MoneyWidget moneyWidget = new MoneyWidget(question);
                moneyWidget.addListeners(symbolTable, question, moneyWidget);
                setDefaultStyles(defaultStyles, question, moneyWidget);
                widget = moneyWidget;
        }
        return widget;
    }

    private Control createBooleanWidget(WidgetType widgetType, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles){
        Control widget;
        switch(widgetType){
            case TEXTBOX:
                // TODO
                throw new UnsupportedOperationException("Not implemented");
            case DROPDOWN:
                DropdownWidget dropdownWidget = new DropdownWidget(question, List.of("false", "true"));
                dropdownWidget.addListeners(symbolTable, question, dropdownWidget);
                setDefaultStyles(defaultStyles, question, dropdownWidget);
                widget = dropdownWidget;
                break;
            case CHECKBOX: // Checkbox => default
            default:
                CheckboxWidget checkboxWidget = new CheckboxWidget(question);
                checkboxWidget.addListeners(symbolTable, question, checkboxWidget);
                setDefaultStyles(defaultStyles, question, checkboxWidget);
                widget = checkboxWidget;
        }
        return widget;
    }

    private WidgetType getWidgetTypeForQuestion(List<DefaultStyle> defaultStyles, Question question){
        WidgetType widgetType = WidgetType.DEFAULT;
        for(DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.type.equals(question.type) && defaultStyle.getWidget() != null) {
                // Get the last one because that is the most local one
                widgetType = defaultStyle.getWidget().type;
            }
        }
        return widgetType;
    }

    private void setDefaultStyles(List<DefaultStyle> defaultStyles, Question question, WidgetInterface widget){
        for(DefaultStyle defaultStyle : defaultStyles){
            if(defaultStyle.type.equals(question.type)){
                for(StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()){
                    styleAttribute.apply(widget);
                }
            }
        }
    }
}
