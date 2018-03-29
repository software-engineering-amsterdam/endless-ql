package qls.visitor;

import qls.model.Page;
import qls.model.StyleSheet;
import qls.model.statement.DefaultStyle;
import qls.model.statement.QuestionReference;
import qls.model.statement.Section;
import qls.model.statement.Statement;
import qls.model.style.*;
import qls.model.widget.*;

public class QLSVisitor<T> implements IQLSVisitor<T> {

    @Override
    public T visit(StyleSheet styleSheet) {
        for (Page page : styleSheet.getPages()) {
            page.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Page page) {
        for (Statement statement : page.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Statement statement) {
        return statement.accept(this);
    }

    @Override
    public T visit(Section section) {
        for (Statement statement : section.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(QuestionReference questionReference) {
        if (questionReference.getWidget() != null) {
            questionReference.getWidget().accept(this);
        }
        return null;
    }

    @Override
    public T visit(DefaultStyle defaultStyle) {
        for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
            styleAttribute.accept(this);
        }

        if (defaultStyle.getWidget() != null) {
            defaultStyle.getWidget().accept(this);
        }
        return null;
    }

    @Override
    public T visit(DefaultWidget widget) {
        return null;
    }

    @Override
    public T visit(DatePickerWidget widget) {
        return null;
    }

    @Override
    public T visit(CheckBoxWidget widget) {
        return null;
    }

    @Override
    public T visit(DropDownWidget widget) {
        return null;
    }

    @Override
    public T visit(RadioWidget widget) {
        return null;
    }

    @Override
    public T visit(SliderWidget widget) {
        return null;
    }

    @Override
    public T visit(SpinBoxWidget widget) {
        return null;
    }

    @Override
    public T visit(TextBoxWidget widget) {
        return null;
    }

    @Override
    public T visit(ColorAttribute widget) {
        return null;
    }

    @Override
    public T visit(FontStyleAttribute widget) {
        return null;
    }

    @Override
    public T visit(FontSizeAttribute widget) {
        return null;
    }

    @Override
    public T visit(WidthAttribute widget) {
        return null;
    }
}