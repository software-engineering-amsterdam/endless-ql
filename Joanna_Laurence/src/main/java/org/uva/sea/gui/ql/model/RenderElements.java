package org.uva.sea.gui.ql.model;

import org.uva.sea.gui.ql.widget.Renderable;
import org.uva.sea.gui.ql.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.util.Collection;

public class RenderElements {
    private Collection<Widget> widgets;
    private Messages message;

    public RenderElements(Collection<Widget> widgets, Messages message) {
        this.widgets = widgets;
        this.message = message;
    }

    public Collection<Widget> getWidgets() {
        return this.widgets;
    }

    public Iterable<String> getWarnings() {
        return this.message.getMessage(MessageTypes.WARNING);
    }

    public Iterable<String> getErrors() {
        return this.message.getMessage(MessageTypes.ERROR);
    }
}
