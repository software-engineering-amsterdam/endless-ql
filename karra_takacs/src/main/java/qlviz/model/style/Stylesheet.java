package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

import java.util.List;

public class Stylesheet extends Node implements Scope {

    private final List<Page> pages;
    private final String name;

    public Stylesheet(List<Page> pages, String name, ParserRuleContext context) {
        super(context);
        this.pages = pages;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Page> getPages() {
        return pages;
    }

    @Override
    public List<DefaultWidgetDeclaration> getDefaultWidgetDeclarations() {
        return List.of();
    }

    @Override
    public Scope getParent() {
        return this;
    }

    @Override
    public List<? extends Scope> getChildren() {
        return this.pages;
    }
}
