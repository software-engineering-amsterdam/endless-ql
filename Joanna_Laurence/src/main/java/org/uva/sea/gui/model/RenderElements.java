package org.uva.sea.gui.model;

import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.util.Collection;

public class RenderElements {
    private Collection<BaseWidget> widgets;
    private Messages message;

    public RenderElements(Collection<BaseWidget> widgets, Messages message) {
        this.widgets = widgets;
        this.message = message;
    }

    public Collection<BaseWidget> getWidgets() {
        return this.widgets;
    }

    public Iterable<String> getWarnings() {
        return this.message.getMessage(MessageTypes.WARNING);
    }

    public Iterable<String> getErrors() {
        return this.message.getMessage(MessageTypes.ERROR);
    }
}
