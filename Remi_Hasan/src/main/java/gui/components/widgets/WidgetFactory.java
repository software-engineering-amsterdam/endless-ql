package gui.components.widgets;

import gui.components.widgets.chooser.CheckBox;
import gui.components.widgets.chooser.DropDown;
import gui.components.widgets.chooser.RadioButtons;
import gui.components.widgets.date.DateChooser;
import gui.components.widgets.slider.DecimalSlider;
import gui.components.widgets.slider.IntegerSlider;
import gui.components.widgets.slider.MoneySlider;
import gui.components.widgets.spinner.DecimalSpinner;
import gui.components.widgets.spinner.IntegerSpinner;
import gui.components.widgets.spinner.MoneySpinner;
import gui.components.widgets.textbox.DecimalTextBox;
import gui.components.widgets.textbox.IntegerTextBox;
import gui.components.widgets.textbox.MoneyTextBox;
import gui.components.widgets.textbox.TextBox;
import ql.model.expression.ReturnType;
import qls.model.widget.DropDownWidget;
import qls.model.widget.RadioWidget;
import qls.model.widget.SliderWidget;
import qls.model.widget.Widget;

public class WidgetFactory {

    public static GUIWidget getDefaultWidget(ReturnType questionType) {
        switch (questionType) {
            case STRING:
                return new TextBox();
            case INTEGER:
                return new IntegerTextBox();
            case DECIMAL:
                return new DecimalTextBox();
            case MONEY:
                return new MoneyTextBox();
            case DATE:
                return new DateChooser();
            case BOOLEAN:
                return new CheckBox();
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }

    public static GUIWidget getWidget(ReturnType questionType, Widget widget) {
        switch (questionType) {
            case STRING:
                return WidgetFactory.getStringWidget(widget);
            case INTEGER:
                return WidgetFactory.getIntegerWidget(widget);
            case DECIMAL:
                return WidgetFactory.getDecimalWidget(widget);
            case MONEY:
                return WidgetFactory.getMoneyWidget(widget);
            case DATE:
                return WidgetFactory.getDateWidget(widget);
            case BOOLEAN:
                return WidgetFactory.getBooleanWidget(widget);
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }

    private static GUIWidget getStringWidget(Widget widget) {
        switch (widget.getType()) {
            case TEXTBOX:
            default:
                return new TextBox();
        }
    }

    private static GUIWidget getIntegerWidget(Widget widget) {
        switch (widget.getType()) {
            case SPINBOX:
                return new IntegerSpinner();
            case SLIDER:
                SliderWidget sliderWidget = (SliderWidget) widget;
                return new IntegerSlider((int) sliderWidget.getMinValue(), (int) sliderWidget.getMaxValue());
            case TEXTBOX:
            default:
                return new IntegerTextBox();
        }
    }

    private static GUIWidget getDecimalWidget(Widget widget) {
        switch (widget.getType()) {
            case SPINBOX:
                return new DecimalSpinner();
            case SLIDER:
                SliderWidget sliderWidget = (SliderWidget) widget;
                return new DecimalSlider(sliderWidget.getMinValue(), sliderWidget.getMaxValue());
            case TEXTBOX:
            default:
                return new DecimalTextBox();
        }
    }

    private static GUIWidget getMoneyWidget(Widget widget) {
        switch (widget.getType()) {
            case SPINBOX:
                return new MoneySpinner();
            case SLIDER:
                SliderWidget sliderWidget = (SliderWidget) widget;
                return new MoneySlider(sliderWidget.getMinValue(), sliderWidget.getMaxValue());
            case TEXTBOX:
            default:
                return new MoneyTextBox();
        }
    }

    private static GUIWidget getDateWidget(Widget widget) {
        return new DateChooser();
    }

    private static GUIWidget getBooleanWidget(Widget widget) {
        switch (widget.getType()) {
            case RADIO:
                RadioWidget radioWidget = (RadioWidget) widget;
                return new RadioButtons(radioWidget.getFalseLabel(), radioWidget.getTrueLabel());
            case DROPDOWN:
                DropDownWidget dropDownWidget = (DropDownWidget) widget;
                return new DropDown(dropDownWidget.getFalseLabel(), dropDownWidget.getTrueLabel());
            case CHECKBOX:
            default:
                return new CheckBox();
        }
    }
}
