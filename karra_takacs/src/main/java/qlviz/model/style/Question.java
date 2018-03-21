package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

public class Question extends Node {

    private final String name;
    private final Widget widget;

    public Question(String name, Widget widgetType, ParserRuleContext context) {
        super(context);
        this.name = name;
        this.widget = widgetType;
    }

    public Question(String name, ParserRuleContext context) {
        super(context);
        this.name = name;
        this.widget = null;
    }

    public String getName() {
        return name;
    }

    public Widget getWidget() {
        return widget;
    }
}

