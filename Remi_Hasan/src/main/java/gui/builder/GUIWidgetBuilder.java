package gui.builder;

import qls.QLSVisitor;
import qls.model.statement.DefaultStyle;
import qls.model.widget.*;

public class GUIWidgetBuilder extends QLSVisitor<WidgetType> {

    @Override
    public WidgetType visit(DefaultStyle defaultStyle) {
        return super.visit(defaultStyle);
    }

    @Override
    public WidgetType visit(WidgetRadio widget) {
        return WidgetType.RADIO;
    }

    @Override
    public WidgetType visit(WidgetSlider widget) {
        return WidgetType.SLIDER;
    }

    @Override
    public WidgetType visit(WidgetSpinBox widget) {
        return WidgetType.SPINBOX;
    }

    @Override
    public WidgetType visit(WidgetDefault widget) {
        return WidgetType.DEFAULT;
    }

    @Override
    public WidgetType visit(WidgetTextBox widget) {
        return WidgetType.TEXTBOX;
    }

    @Override
    public WidgetType visit(WidgetDropdown widget) {
        return WidgetType.DROPDOWN;
    }

    @Override
    public WidgetType visit(WidgetDatePicker widget) {
        return WidgetType.DATE;
    }
}
