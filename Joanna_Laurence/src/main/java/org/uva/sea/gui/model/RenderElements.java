package org.uva.sea.gui.model;

import org.uva.sea.gui.widget.Renderable;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.util.Collection;

public class RenderElements {
    private Collection<Renderable> renderables;
    private Messages message;

    public RenderElements(Collection<Renderable> renderables, Messages message) {
        this.renderables = renderables;
        this.message = message;
    }

    public Collection<Renderable> getRenderables() {
        return this.renderables;
    }

    public Iterable<String> getWarnings() {
        return this.message.getMessage(MessageTypes.WARNING);
    }

    public Iterable<String> getErrors() {
        return this.message.getMessage(MessageTypes.ERROR);
    }
}
