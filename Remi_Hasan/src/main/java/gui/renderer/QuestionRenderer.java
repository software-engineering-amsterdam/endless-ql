package gui.renderer;

import gui.widgets.*;
import gui.widgets.CheckboxWidget;
import gui.widgets.DropdownWidget;
import gui.widgets.RadioWidget;
import ql.model.Question;
import qls.QLSVisitor;
import qls.model.widget.*;

import java.util.List;

public class QuestionRenderer extends QLSVisitor<GUIWidget> {

//    private final Question question;
//
//    public QuestionRenderer(Question question) {
//        this.question = question;
//    }
//
//    public GUIWidget getDefaultWidget() {
//        switch (question.type) {
//            case STRING:
//                return new StringWidget(question);
//            case INTEGER:
//                return new IntegerWidget(question);
//            case DECIMAL:
//                return new DecimalWidget(question);
//            case MONEY:
//                return new MoneyWidget(question);
//            case DATE:
//                return new DateWidget(question);
//            case BOOLEAN:
//                return new CheckboxWidget(question);
//            default:
//                throw new UnsupportedOperationException("Widget type not implemented");
//        }
//    }
//
//    @Override
//    public GUIWidget visit(WidgetDatePicker widget) {
//        return new DateWidget(question);
//    }
//
//    @Override
//    public GUIWidget visit(WidgetDropdown widget) {
//        return new DropdownWidget(question, List.of(widget.getFalseLabel(), widget.getTrueLabel()));
//    }
//
//    @Override
//    public GUIWidget visit(WidgetTextBox widget) {
//        switch (question.type) {
//            case STRING:
//                return new StringWidget(question);
//            case INTEGER:
//                return new IntegerWidget(question);
//            case DECIMAL:
//                return new DecimalWidget(question);
//            case MONEY:
//                return new MoneyWidget(question);
//            default:
//                throw new UnsupportedOperationException("Invalid type for textbox widget");
//        }
//    }
//
//    @Override
//    public GUIWidget visit(WidgetSpinBox widget) {
//        // TODO: factory?
//        switch (question.type) {
//            case INTEGER:
//                return new SpinnerIntegerWidget(question);
//            case MONEY:
//                return new SpinnerMoneyWidget(question);
//            case DECIMAL:
//                return new SpinnerDecimalWidget(question);
//            default:
//                throw new UnsupportedOperationException("Invalid type for spinbox widget");
//        }
//    }
//
//    @Override
//    public GUIWidget visit(WidgetSlider widget) {
//        // TODO: factory?
//        switch (question.type) {
//            case INTEGER:
//                return new SliderIntegerWidget(question, (int) widget.min, (int) widget.max, (int) widget.step);
//            case MONEY:
//                return new SliderMoneyWidget(question, widget.min, widget.max, widget.step);
//            case DECIMAL:
//                return new SliderDecimalWidget(question, widget.min, widget.max, widget.step);
//            default:
//                throw new UnsupportedOperationException("Invalid type for slider widget");
//        }
//    }
//
//    @Override
//    public GUIWidget visit(WidgetRadio widget) {
//        return new RadioWidget(this.question, widget.getFalseLabel(), widget.getTrueLabel());
//    }
//
//    @Override
//    public GUIWidget visit(WidgetCheckBox widget) {
//        return new CheckboxWidget(this.question);
//    }
}
