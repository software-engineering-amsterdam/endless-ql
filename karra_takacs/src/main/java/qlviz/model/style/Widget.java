package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

import java.util.List;

public class Widget extends Node {

    private final WidgetType type;
    private final List<Parameter> parameters;

    public Widget(ParserRuleContext context, WidgetType type, List<Parameter> parameters) {
        super(context);
        this.type = type;
        this.parameters = parameters;
    }

    public WidgetType getType() {
        return type;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

}


