package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.qls.parser.elements.Parameter;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class Widget extends StyleSpecification {

    private final WidgetType widgetType;
    private final List<Parameter> parameters;

    public Widget(final Token token, final String widgetType, final List<Parameter> parameters) {
        super(token);
        this.parameters = parameters;
        this.widgetType = WidgetType.valueOf(widgetType.toUpperCase());
    }

    public final WidgetType getWidgetType() {
        return this.widgetType;
    }

    public final Iterable<Parameter> getParameters() {
        return this.parameters;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public final List<String> getParametersAsStrings() {
        final List<String> parameters = new ArrayList<>();
        for (final Parameter parameter : this.getParameters())
            parameters.add(parameter.getParameter());
        return parameters;
    }
}
