package org.uva.sea.gui.model;

import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.util.Collection;

public class RenderingElements {
    private final Collection<BaseWidget> widgets;
    private final Messages messages;

    RenderingElements(Collection<BaseWidget> widgets, Messages messages) {
        this.widgets = widgets;
        this.messages = messages;
    }

    public Iterable<BaseWidget> getWidgets() {
        return this.widgets;
    }

    public Iterable<String> getWarnings() {
        return this.messages.getMessage(MessageTypes.WARNING);
    }

    public Iterable<String> getErrors() {
        return this.messages.getMessage(MessageTypes.ERROR);
    }
}
