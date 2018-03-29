package gui.widgets;

import gui.widgets.chooser.CheckboxWidget;
import gui.widgets.chooser.DropdownWidget;
import gui.widgets.chooser.RadioWidget;
import gui.widgets.slider.SliderDecimalWidget;
import gui.widgets.slider.SliderIntegerWidget;
import gui.widgets.slider.SliderMoneyWidget;
import gui.widgets.spinner.SpinnerDecimalWidget;
import gui.widgets.spinner.SpinnerIntegerWidget;
import gui.widgets.spinner.SpinnerMoneyWidget;
import gui.widgets.textbox.TextboxDecimalWidget;
import gui.widgets.textbox.TextboxIntegerWidget;
import gui.widgets.textbox.TextboxMoneyWidget;
import gui.widgets.textbox.TextboxWidget;
import ql.model.expression.ReturnType;
import qls.model.widget.*;

public class WidgetFactory {

    public static GUIWidget getDefaultWidget(ReturnType questionType) {
        switch (questionType) {
            case STRING:
                return new TextboxWidget();
            case INTEGER:
                return new TextboxIntegerWidget();
            case DECIMAL:
                return new TextboxDecimalWidget();
            case MONEY:
                return new TextboxMoneyWidget();
            case DATE:
                return new DateWidget();
            case BOOLEAN:
                return new CheckboxWidget();
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }

    public static GUIWidget getWidget(ReturnType questionType, Widget widget) {
        switch (questionType) {
            case STRING:
                return WidgetFactory.getWidgetString(widget);
            case INTEGER:
                return WidgetFactory.getWidgetInteger(widget);
            case DECIMAL:
                return WidgetFactory.getWidgetDecimal(widget);
            case MONEY:
                return WidgetFactory.getWidgetMoney(widget);
            case DATE:
                return WidgetFactory.getWidgetDate(widget);
            case BOOLEAN:
                return WidgetFactory.getWidgetBoolean(widget);
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }

    private static GUIWidget getWidgetString(Widget widget) {
        switch (widget.getType()) {
            case TEXTBOX:
            default:
                return new TextboxWidget();
        }
    }

    private static GUIWidget getWidgetInteger(Widget widget) {
        switch (widget.getType()) {
            case SPINBOX:
                return new SpinnerIntegerWidget();
            case SLIDER:
                WidgetSlider widgetSlider = (WidgetSlider) widget;
                return new SliderIntegerWidget((int) widgetSlider.getMinValue(), (int) widgetSlider.getMaxValue());
            case TEXTBOX:
            default:
                return new TextboxIntegerWidget();
        }
    }

    private static GUIWidget getWidgetDecimal(Widget widget) {
        switch (widget.getType()) {
            case SPINBOX:
                return new SpinnerDecimalWidget();
            case SLIDER:
                WidgetSlider widgetSlider = (WidgetSlider) widget;
                return new SliderDecimalWidget(widgetSlider.getMinValue(), widgetSlider.getMaxValue());
            case TEXTBOX:
            default:
                return new TextboxDecimalWidget();
        }
    }

    private static GUIWidget getWidgetMoney(Widget widget) {
        switch (widget.getType()) {
            case SPINBOX:
                return new SpinnerMoneyWidget();
            case SLIDER:
                WidgetSlider widgetSlider = (WidgetSlider) widget;
                return new SliderMoneyWidget(widgetSlider.getMinValue(), widgetSlider.getMaxValue());
            case TEXTBOX:
            default:
                return new TextboxMoneyWidget();
        }
    }

    private static GUIWidget getWidgetDate(Widget widget) {
        return new DateWidget();
    }

    private static GUIWidget getWidgetBoolean(Widget widget) {
        switch (widget.getType()) {
            case RADIO:
                WidgetRadio widgetRadio = (WidgetRadio) widget;
                return new RadioWidget(widgetRadio.getFalseLabel(), widgetRadio.getTrueLabel());
            case DROPDOWN:
                WidgetDropdown widgetDropdown = (WidgetDropdown) widget;
                return new DropdownWidget(widgetDropdown.getFalseLabel(), widgetDropdown.getTrueLabel());
            case CHECKBOX:
            default:
                return new CheckboxWidget();
        }
    }
}
