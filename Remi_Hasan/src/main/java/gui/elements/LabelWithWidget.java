package gui.elements;

import gui.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import qls.IQLSVisitor;
import qls.model.*;
import qls.model.statement.DefaultStyle;
import qls.model.statement.QuestionReference;
import qls.model.statement.Section;
import qls.model.statement.Statement;
import qls.model.style.*;
import qls.model.widget.*;

// TODO: remove visitor here
public class LabelWithWidget extends VBox implements GUIQuestionElement, IQLSVisitor<Void> {

    private final GUIWidget guiWidget;

    public LabelWithWidget(Label label, GUIWidget guiWidget) {
        // This way, when hiding the widget, it will also take no space in the GUI
        this.managedProperty().bind(this.visibleProperty());

        this.guiWidget = guiWidget;
        this.getChildren().add(label);
        this.getChildren().add(guiWidget.getNode());
    }

    @Override
    public Expression getExpressionValue() {
        return guiWidget.getExpressionValue();
    }

    @Override
    public void setValue(Value value) {
        guiWidget.setValue(value);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        guiWidget.setChangeListener(invalidationListener);
    }

    @Override
    public void setVisibility(boolean visible) {
        this.setVisible(visible);
    }

    @Override
    public void setColor(String color) {
        // TODO: label as well?
        guiWidget.setColor(color);
    }

    @Override
    public void setFont(String font) {
        guiWidget.setFont(font);
    }

    @Override
    public void setFontSize(int fontSize) {
        guiWidget.setFontSize(fontSize);
    }

    @Override
    public void setWidth(int width) {
        guiWidget.setWidth(width);
    }

    public void apply(StyleAttribute styleAttribute) {
        styleAttribute.accept(this);
    }

    @Override
    public Void visit(StyleSheet styleSheet) {
        return null;
    }

    @Override
    public Void visit(Page page) {
        return null;
    }

    @Override
    public Void visit(Statement statement) {
        return null;
    }

    @Override
    public Void visit(Section page) {
        return null;
    }

    @Override
    public Void visit(QuestionReference questionReference) {
        return null;
    }

    @Override
    public Void visit(DefaultStyle defaultStyle) {
        return null;
    }

    @Override
    public Void visit(WidgetDefault widget) {
        return null;
    }

    @Override
    public Void visit(WidgetDatePicker widget) {
        return null;
    }

    @Override
    public Void visit(WidgetCheckBox widget) {
        return null;
    }

    @Override
    public Void visit(WidgetDropdown widget) {
        return null;
    }

    @Override
    public Void visit(WidgetRadio widget) {
        return null;
    }

    @Override
    public Void visit(WidgetSlider widget) {
        return null;
    }

    @Override
    public Void visit(WidgetSpinBox widget) {
        return null;
    }

    @Override
    public Void visit(WidgetTextBox widget) {
        return null;
    }

    @Override
    public Void visit(StyleAttributeColor widget) {
        setColor(widget.getColor());
        return null;
    }

    @Override
    public Void visit(StyleAttributeFont widget) {
        setFont(widget.getFontFamily());
        return null;
    }

    @Override
    public Void visit(StyleAttributeFontSize widget) {
        setFontSize(widget.getFontSize());
        return null;
    }

    @Override
    public Void visit(StyleAttributeWidth widget) {
        setWidth(widget.getWidth());
        return null;
    }
}
