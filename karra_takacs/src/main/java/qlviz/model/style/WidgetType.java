package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

public abstract class WidgetType extends Node {

    public WidgetType(ParserRuleContext context) {
        super(context);
    }
}


