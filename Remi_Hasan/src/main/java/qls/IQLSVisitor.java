package qls;

import qls.model.*;
import qls.model.widgets.*;

public interface IQLSVisitor<T> {
    T visit(StyleSheet styleSheet);

    T visit(Page page);

    T visit(Section page);

    T visit(Question question);

    T visit(DefaultStyle defaultStyle);

    T visit(WidgetCheckBox widget);

    T visit(WidgetColor widget);

    T visit(WidgetFont widget);

    T visit(WidgetFontSize widget);

    T visit(WidgetRadio widget);

    T visit(WidgetSpinBox widget);

    T visit(WidgetWidth widget);
}