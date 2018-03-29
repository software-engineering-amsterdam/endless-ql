package gui.widgets;

import gui.widgets.spinner.SpinnerDecimalWidget;
import gui.widgets.spinner.SpinnerIntegerWidget;
import gui.widgets.spinner.SpinnerMoneyWidget;
import gui.widgets.textbox.TextboxDecimalWidget;
import gui.widgets.textbox.TextboxIntegerWidget;
import gui.widgets.textbox.TextboxMoneyWidget;
import gui.widgets.textbox.TextboxWidget;
import ql.model.expression.ReturnType;
import qls.model.widget.WidgetType;

public class WidgetFactory {
    // TODO: implement interface that can be overridden by QLS
    public static GUIWidget getDefaultWidget(ReturnType questionType, WidgetType widgetType) {
        switch (questionType) {
            case STRING:
                return WidgetFactory.getDefaultWidgetString(widgetType);
            case INTEGER:
                return WidgetFactory.getDefaultWidgetInteger(widgetType);
            case DECIMAL:
                return WidgetFactory.getDefaultWidgetDecimal(widgetType);
            case MONEY:
                return WidgetFactory.getDefaultWidgetMoney(widgetType);
            case DATE:
                return WidgetFactory.getDefaultWidgetDate(widgetType);
            case BOOLEAN:
                return WidgetFactory.getDefaultWidgetBoolean(widgetType);
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }

    private static GUIWidget getDefaultWidgetString(WidgetType widgetType) {
        switch (widgetType) {
            case TEXTBOX:
            default:
                return new TextboxWidget();
        }
    }

    private static GUIWidget getDefaultWidgetInteger(WidgetType widgetType) {
        switch (widgetType) {
            case SPINBOX:
                return new SpinnerIntegerWidget();
            case TEXTBOX:
            default:
                return new TextboxIntegerWidget();
        }
    }

    private static GUIWidget getDefaultWidgetDecimal(WidgetType widgetType) {
        switch (widgetType) {
            case SPINBOX:
                return new SpinnerDecimalWidget();
            case TEXTBOX:
            default:
                return new TextboxDecimalWidget();
        }
    }

    private static GUIWidget getDefaultWidgetMoney(WidgetType widgetType) {
        switch (widgetType) {
            case SPINBOX:
                return new SpinnerMoneyWidget();
            case TEXTBOX:
            default:
                return new TextboxMoneyWidget();
        }
    }

    private static GUIWidget getDefaultWidgetDate(WidgetType widgetType) {
        return new DateWidget();
    }


    private static GUIWidget getDefaultWidgetBoolean(WidgetType widgetType) {
        switch(widgetType){
            case RADIO:
                // TODO
                return new RadioWidget("false", "true");
            case DROPDOWN:
                // TODO
                return new DropdownWidget("false", "true");
            case CHECKBOX:
            default:
                return new CheckboxWidget();
        }
    }
}
