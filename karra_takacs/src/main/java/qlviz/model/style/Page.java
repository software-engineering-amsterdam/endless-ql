package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

import java.util.List;

public class Page extends Node {

    private final String name;
    private final List<Section> sections;

    public Page(String name, List<Section> sections, ParserRuleContext context) {
        super(context);
        this.name = name;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public List<Section> getSections() {
        return sections;
    }
}
