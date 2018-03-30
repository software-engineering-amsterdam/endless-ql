package gui.render;

import gui.components.widgets.GUIWidget;
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
import qls.model.widget.*;
import qls.visitor.QLSVisitor;

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
        return new TextBox();
    }

    private static GUIWidget getIntegerWidget(Widget widget) {
        GUIWidget guiWidget = widget.accept(new QLSVisitor<GUIWidget>() {
            @Override
            public GUIWidget visit(SpinBoxWidget widget) {
                return new IntegerSpinner();
            }

            @Override
            public GUIWidget visit(SliderWidget widget) {
                return new IntegerSlider((int) widget.getMinValue(), (int) widget.getMaxValue());
            }

            @Override
            public GUIWidget visit(TextBoxWidget widget) {
                return new IntegerTextBox();
            }
        });

        // Fall back on default widget
        if (guiWidget == null) {
            guiWidget = new IntegerTextBox();
        }

        return guiWidget;
    }

    private static GUIWidget getDecimalWidget(Widget widget) {
        GUIWidget guiWidget = widget.accept(new QLSVisitor<GUIWidget>() {
            @Override
            public GUIWidget visit(SpinBoxWidget widget) {
                return new DecimalSpinner();
            }

            @Override
            public GUIWidget visit(SliderWidget widget) {
                return new DecimalSlider(widget.getMinValue(), widget.getMaxValue());
            }

            @Override
            public GUIWidget visit(TextBoxWidget widget) {
                return new DecimalTextBox();
            }
        });

        // Fall back on default widget
        if (guiWidget == null) {
            guiWidget = new DecimalTextBox();
        }

        return guiWidget;
    }

    private static GUIWidget getMoneyWidget(Widget widget) {
        GUIWidget guiWidget = widget.accept(new QLSVisitor<GUIWidget>() {
            @Override
            public GUIWidget visit(SpinBoxWidget widget) {
                return new MoneySpinner();
            }

            @Override
            public GUIWidget visit(SliderWidget widget) {
                return new MoneySlider(widget.getMinValue(), widget.getMaxValue());
            }

            @Override
            public GUIWidget visit(TextBoxWidget widget) {
                return new MoneyTextBox();
            }
        });

        // Fall back on default widget
        if (guiWidget == null) {
            guiWidget = new MoneyTextBox();
        }

        return guiWidget;
    }

    private static GUIWidget getDateWidget(Widget widget) {
        return new DateChooser();
    }

    private static GUIWidget getBooleanWidget(Widget widget) {
        GUIWidget guiWidget = widget.accept(new QLSVisitor<GUIWidget>() {
            @Override
            public GUIWidget visit(RadioWidget widget) {
                return new RadioButtons(widget.getFalseLabel(), widget.getTrueLabel());
            }

            @Override
            public GUIWidget visit(DropDownWidget widget) {
                return new DropDown(widget.getFalseLabel(), widget.getTrueLabel());
            }

            @Override
            public GUIWidget visit(CheckBoxWidget widget) {
                return new CheckBox();
            }
        });

        // Fall back on default widget
        if (guiWidget == null) {
            guiWidget = new CheckBox();
        }

        return guiWidget;
    }
}
