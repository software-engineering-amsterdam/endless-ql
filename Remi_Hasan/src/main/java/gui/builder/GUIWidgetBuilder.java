package gui.builder;

import qls.model.statement.DefaultStyle;
import qls.model.widget.*;
import qls.visitor.QLSVisitor;

public class GUIWidgetBuilder extends QLSVisitor<WidgetType> {

    @Override
    public WidgetType visit(DefaultStyle defaultStyle) {
        return super.visit(defaultStyle);
    }

    @Override
    public WidgetType visit(DefaultWidget widget) {
        return WidgetType.DEFAULT;
    }

    @Override
    public WidgetType visit(DatePickerWidget widget) {
        return WidgetType.DATE;
    }

    @Override
    public WidgetType visit(DropDownWidget widget) {
        return WidgetType.DROPDOWN;
    }

    @Override
    public WidgetType visit(RadioWidget widget) {
        return WidgetType.RADIO;
    }

    @Override
    public WidgetType visit(SliderWidget widget) {
        return WidgetType.SLIDER;
    }

    @Override
    public WidgetType visit(SpinBoxWidget widget) {
        return WidgetType.SPINBOX;
    }

    @Override
    public WidgetType visit(TextBoxWidget widget) {
        return WidgetType.TEXTBOX;
    }
}
