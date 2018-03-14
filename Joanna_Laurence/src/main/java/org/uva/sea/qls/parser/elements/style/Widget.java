package org.uva.sea.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.qls.parser.elements.Parameter;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Widget extends StyleSpecification {

    private WidgetType widgetType;
    private List<Parameter> parameters;

    public Widget(Token token, String widgetType, List<Parameter> parameters) {
        super(token);
        this.parameters = parameters;
        this.widgetType = WidgetType.valueOf(widgetType.toUpperCase());
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
