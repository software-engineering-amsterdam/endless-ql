package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

import java.util.List;

public class Page extends Node implements Scope {

    private final String name;
    private final List<Section> sections;
    private final List<DefaultWidgetDeclaration> defaultWidgetDeclarations;
    private  Stylesheet parent;

    public void setParent(Stylesheet parent) {
        this.parent = parent;
    }

    public Page(String name,
                List<Section> sections,
                List<DefaultWidgetDeclaration> defaultWidgetDeclarations,
                ParserRuleContext context) {
        super(context);
        this.name = name;
        this.defaultWidgetDeclarations = defaultWidgetDeclarations;
        this.sections = sections;
    }

    public Scope getParent() {return parent;}

    public List<? extends Scope> getChildren() { return this.sections; }

    public String getName() {
        return name;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<DefaultWidgetDeclaration> getDefaultWidgetDeclarations() {
        return defaultWidgetDeclarations;
    }
}
