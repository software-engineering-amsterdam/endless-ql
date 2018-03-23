package qls;

import qls.model.*;
import qls.model.style.*;
import qls.model.style.StyleAttributeColor;
import qls.model.style.StyleAttributeFont;
import qls.model.widget.*;

public interface IQLSVisitor<T> {
    T visit(StyleSheet styleSheet);

    T visit(Page page);

    T visit(Section page);

    T visit(QuestionReference questionReference);

    T visit(DefaultStyle defaultStyle);

    T visit(WidgetDefault widget);

    T visit(WidgetDatePicker widget);

    T visit(WidgetCheckBox widget);

    T visit(WidgetDropdown widget);

    T visit(WidgetRadio widget);

    T visit(WidgetSlider widget);

    T visit(WidgetSpinBox widget);

    T visit(WidgetTextBox widget);

    T visit(StyleAttributeColor widget);

    T visit(StyleAttributeFont widget);

    T visit(StyleAttributeFontSize widget);

    T visit(StyleAttributeWidth widget);
}