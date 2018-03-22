package org.uva.sea.gui.ql.model.factory;

import org.uva.sea.gui.ql.widget.Widget;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class WidgetValueUpdate extends BaseValueVisitor<Void> {

    private final Widget widget;

    public WidgetValueUpdate(Widget widget) {
        this.widget = widget;
    }

    public Widget updateWidget(Value value) {
        value.accept(this);
        return this.widget;
    }

    @Override
    public Void visit(BooleanValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(DateValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(DecimalValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(IntValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(MoneyValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(StringValue value) {
        this.widget.updateValue(value);
        return null;
    }
}
