package qls;

import qls.model.*;
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
        for(Statement statement : section.getStatements()) {
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
    public T visit(WidgetDefault widget) {
        return null;
    }

    @Override
    public T visit(WidgetDatePicker widget) {
        return null;
    }

    @Override
    public T visit(WidgetCheckBox widget) {
        return null;
    }

    @Override
    public T visit(WidgetDropdown widget) {
        return null;
    }

    @Override
    public T visit(WidgetRadio widget) {
        return null;
    }

    @Override
    public T visit(WidgetSlider widget) {
        return null;
    }

    @Override
    public T visit(WidgetSpinBox widget) {
        return null;
    }

    @Override
    public T visit(WidgetTextBox widget) {
        return null;
    }

    @Override
    public T visit(StyleAttributeColor widget) {
        return null;
    }

    @Override
    public T visit(StyleAttributeFont widget) {
        return null;
    }

    @Override
    public T visit(StyleAttributeFontSize widget) {
        return null;
    }

    @Override
    public T visit(StyleAttributeWidth widget) {
        return null;
    }
}
