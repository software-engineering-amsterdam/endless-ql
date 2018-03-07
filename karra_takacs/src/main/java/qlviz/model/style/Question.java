package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

import java.util.Optional;

public class Question extends Node {

    private final String name;
    private final WidgetType widgetType;

    public Question(String name, WidgetType widgetType, ParserRuleContext context) {
        super(context);
        this.name = name;
        this.widgetType = widgetType;
    }

    public Question(String name, ParserRuleContext context) {
        super(context);
        this.name = name;
        this.widgetType = null;
    }

    public String getName() {
        return name;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }
}

