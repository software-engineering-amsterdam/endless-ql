package qls;

import qls.model.*;
import qls.model.widgets.*;

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
        for (DefaultStyle defaultStyle : page.getDefaultStyles()) {
            defaultStyle.accept(this);
        }

        for (Section section : page.getSections()) {
            section.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Section section) {
        for (Question question : section.getQuestions()) {
            question.accept(this);
        }

        for (Section subSection : section.getSections()) {
            subSection.accept(this);
        }
        for (DefaultStyle defaultStyle : section.getDefaultStyles()) {
            defaultStyle.accept(this);
        }

        return null;
    }

    @Override
    public T visit(Question question) {
        if (question.getWidget() != null) {
            question.getWidget().accept(this);
        }
        return null;
    }

    @Override
    public T visit(DefaultStyle defaultStyle) {
        for (Widget widget : defaultStyle.getWidgets()) {
            widget.accept(this);
        }
        return null;
    }

    @Override
    public T visit(WidgetCheckBox widget) {
        return null;
    }

    @Override
    public T visit(WidgetColor widget) {
        return null;
    }

    @Override
    public T visit(WidgetFont widget) {
        return null;
    }

    @Override
    public T visit(WidgetFontSize widget) {
        return null;
    }

    @Override
    public T visit(WidgetRadio widget) {
        return null;
    }

    @Override
    public T visit(WidgetSpinBox widget) {
        return null;
    }

    @Override
    public T visit(WidgetWidth widget) {
        return null;
    }
}
