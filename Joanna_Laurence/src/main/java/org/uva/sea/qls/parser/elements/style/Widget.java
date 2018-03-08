package org.uva.sea.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.elements.Parameter;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Widget extends StyleSpecification {

    private String widgetName;
    private List<Parameter> parameters;

    public Widget(Token token, String widgetName, List<Parameter> parameters) {
        super(token);
        this.parameters = parameters;
        this.widgetName = widgetName;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
