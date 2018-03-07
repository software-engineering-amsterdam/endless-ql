package qlviz.model;

import org.antlr.v4.runtime.ParserRuleContext;

public class Node {
    private final ParserRuleContext context;

    public Node(ParserRuleContext context) {
        this.context = context;
    }

    public ParserRuleContext getContext() {
        return context;
    }
}
