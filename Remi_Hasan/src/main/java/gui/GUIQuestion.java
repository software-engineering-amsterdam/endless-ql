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
import qls.model.widget.WidgetDefault;
import qls.model.widget.WidgetType;

import java.util.List;

public class GUIQuestion extends VBox implements WidgetVisitor<Node> {

    GUIQuestion(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {


        // Get the widget type for this question if there is any
        Node widget;
        Widget qlsWidget = qlsQuestion.getWidget();
        if(qlsWidget != null){
            widget = qlsWidget.createWidget(this, symbolTable, question, qlsQuestion, defaultStyles);
        } else{
            // If no default widget is specified in QLS, use the default widget for question type
            Widget defaultWidget = getWidgetForQuestion(defaultStyles, question);
            widget = defaultWidget.createWidget(this, symbolTable, question, qlsQuestion, defaultStyles);
        }

        Label label = new Label(question.label);
        label.managedProperty().bind(widget.managedProperty());
        label.visibleProperty().bind(widget.visibleProperty());
        this.getChildren().add(label);
        this.getChildren().add(widget);
        this.setPadding(new Insets(20, 20, 20, 20));
    }

    public IntegerWidget visitWidgetTypeInteger(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        IntegerWidget integerWidget = new IntegerWidget(question);
        integerWidget.addListeners(symbolTable, question, integerWidget);
        setDefaultStyles(defaultStyles, question, integerWidget);
        return integerWidget;
    }

    public Node visitWidgetTypeString(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        StringWidget stringWidget = new StringWidget(question);
        stringWidget.addListeners(symbolTable, question, stringWidget);
        setDefaultStyles(defaultStyles, question, stringWidget);
        return stringWidget;
    }

    public Node visitWidgetTypeDate(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        DateWidget dateWidget = new DateWidget(question);
        dateWidget.addListeners(symbolTable, question, dateWidget);
        setDefaultStyles(defaultStyles, question, dateWidget);
        return dateWidget;
    }

    @Override
    public Node visitWidgetTypeIntegerSpinbox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        SpinnerIntegerWidget spinnerWidget = new SpinnerIntegerWidget(question);
        spinnerWidget.addListeners(symbolTable, question, spinnerWidget);
        setDefaultStyles(defaultStyles, question, spinnerWidget);
        return spinnerWidget;
    }

    @Override
    public Node visitWidgetTypeDecimalSpinbox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        SpinnerDecimalWidget spinnerWidget = new SpinnerDecimalWidget(question);
        spinnerWidget.addListeners(symbolTable, question, spinnerWidget);
        setDefaultStyles(defaultStyles, question, spinnerWidget);
        return spinnerWidget;
    }

    @Override
    public Node visitWidgetTypeMoneySpinbox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        SpinnerMoneyWidget spinnerWidget = new SpinnerMoneyWidget(question);
        spinnerWidget.addListeners(symbolTable, question, spinnerWidget);
        setDefaultStyles(defaultStyles, question, spinnerWidget);
        return spinnerWidget;
    }

    @Override
    public Node visitWidgetTypeIntegerSlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, int min, int max, int step) {
        SliderIntegerWidget sliderIntegerWidget = new SliderIntegerWidget(question, min, max, step);
        sliderIntegerWidget.addListeners(symbolTable, question, sliderIntegerWidget);
        setDefaultStyles(defaultStyles, question, sliderIntegerWidget);
        return sliderIntegerWidget;
    }

    @Override
    public Node visitWidgetTypeDecimalSlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, double min, double max, double step) {
        SliderDecimalWidget sliderDecimalWidget = new SliderDecimalWidget(question, min, max, step);
        sliderDecimalWidget.addListeners(symbolTable, question, sliderDecimalWidget);
        setDefaultStyles(defaultStyles, question, sliderDecimalWidget);
        return sliderDecimalWidget;
    }

    @Override
    public Node visitWidgetTypeMoneySlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, double min, double max, double step) {
        SliderMoneyWidget sliderMoneyWidget = new SliderMoneyWidget(question, min, max, step);
        sliderMoneyWidget.addListeners(symbolTable, question, sliderMoneyWidget);
        setDefaultStyles(defaultStyles, question, sliderMoneyWidget);
        return sliderMoneyWidget;
    }

    @Override
    public Node visitWidgetTypeBooleanRadio(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel) {
        RadioWidget radioWidget = new RadioWidget(question, falseLabel, trueLabel);
        radioWidget.addListeners(symbolTable, question, radioWidget);
        setDefaultStyles(defaultStyles, question, radioWidget);
        return radioWidget;
    }

    @Override
    public Node visitWidgetTypeBooleanCheckbox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return visitWidgetTypeBoolean(symbolTable, question, qlsQuestion, defaultStyles);
    }

    @Override
    public Node visitWidgetTypeBooleanDropdown(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel) {
        DropdownWidget dropdownWidget = new DropdownWidget(question, List.of(falseLabel, trueLabel));
        dropdownWidget.addListeners(symbolTable, question, dropdownWidget);
        setDefaultStyles(defaultStyles, question, dropdownWidget);
        return dropdownWidget;
    }

    public Node visitWidgetTypeDecimal(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        DecimalWidget decimalWidget = new DecimalWidget(question);
        decimalWidget.addListeners(symbolTable, question, decimalWidget);
        setDefaultStyles(defaultStyles, question, decimalWidget);
        return decimalWidget;
    }

    public Node visitWidgetTypeMoney(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        MoneyWidget moneyWidget = new MoneyWidget(question);
        moneyWidget.addListeners(symbolTable, question, moneyWidget);
        setDefaultStyles(defaultStyles, question, moneyWidget);
        return moneyWidget;
    }

    public Node visitWidgetTypeBoolean(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        CheckboxWidget checkboxWidget = new CheckboxWidget(question);
        checkboxWidget.addListeners(symbolTable, question, checkboxWidget);
        setDefaultStyles(defaultStyles, question, checkboxWidget);
        return checkboxWidget;
    }

    private Widget getWidgetForQuestion(List<DefaultStyle> defaultStyles, Question question) {
        WidgetType widgetType = WidgetType.valueOf(question.type.toString());
        Widget widget = new WidgetDefault(null, widgetType);
        for (DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.type.equals(question.type) && defaultStyle.getWidget() != null) {
                // Get the last one because that is the most local one
                widget = defaultStyle.getWidget();
            }
        }
        return widget;
    }

    private void setDefaultStyles(List<DefaultStyle> defaultStyles, Question question, WidgetInterface widget) {
        for (DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.type.equals(question.type)) {
                for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
                    styleAttribute.apply(widget);
                }
            }
        }
    }
}
