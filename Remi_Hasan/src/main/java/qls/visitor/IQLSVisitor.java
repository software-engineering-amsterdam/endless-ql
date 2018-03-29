package qls.visitor;

import qls.model.Page;
import qls.model.StyleSheet;
import qls.model.statement.DefaultStyle;
import qls.model.statement.QuestionReference;
import qls.model.statement.Section;
import qls.model.statement.Statement;
import qls.model.style.ColorAttribute;
import qls.model.style.FontSizeAttribute;
import qls.model.style.FontStyleAttribute;
import qls.model.style.WidthAttribute;
import qls.model.widget.*;

public interface IQLSVisitor<T> {
    T visit(StyleSheet styleSheet);

    T visit(Page page);

    T visit(Statement statement);

    T visit(Section section);

    T visit(QuestionReference questionReference);

    T visit(DefaultStyle defaultStyle);

    T visit(DefaultWidget widget);

    T visit(DatePickerWidget widget);

    T visit(CheckBoxWidget widget);

    T visit(DropDownWidget widget);

    T visit(RadioWidget widget);

    T visit(SliderWidget widget);

    T visit(SpinBoxWidget widget);

    T visit(TextBoxWidget widget);

    T visit(ColorAttribute widget);

    T visit(FontStyleAttribute widget);

    T visit(FontSizeAttribute widget);

    T visit(WidthAttribute widget);
}