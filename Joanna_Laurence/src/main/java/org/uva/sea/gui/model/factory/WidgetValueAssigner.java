package org.uva.sea.gui.model.factory;

import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class WidgetValueAssigner extends BaseValueVisitor<Void> {

    private final BaseWidget widget;

    public WidgetValueAssigner(final BaseWidget widget) {
        this.widget = widget;
    }

    public final BaseWidget updateWidget(final Value value) {
        value.accept(this);
        return this.widget;
    }

    @Override
    public final Void visit(final BooleanValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public final Void visit(final DateValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public final Void visit(final DecimalValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public final Void visit(final IntValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public final Void visit(final MoneyValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public final Void visit(final StringValue value) {
        this.widget.updateValue(value);
        return null;
    }
}
