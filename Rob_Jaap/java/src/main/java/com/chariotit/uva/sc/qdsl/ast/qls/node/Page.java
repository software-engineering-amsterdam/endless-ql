package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class Page extends AstNode {

    private List<DefaultProperties> defaults;
    private List<Section> sections;
    private String label;

    public Page(List<Section> sections, String label, List<DefaultProperties> defaults, Integer
            columnNumber,
                Integer lineNumber) {
        super(lineNumber, columnNumber);
        this.defaults = defaults;
        this.sections = sections;
        this.label = label;
    }

    public Page(List<Section> sections, String label, Integer columnNumber, Integer lineNumber) {
        super(lineNumber, columnNumber);
        this.defaults = new ArrayList<>();
        this.sections = sections;
        this.label = label;
    }

    public List<DefaultProperties> getDefaults() {
        return defaults;
    }

    public void setDefaults(List<DefaultProperties> defaults) {
        this.defaults = defaults;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        for (DefaultProperties defaultProperties : defaults) {
            defaultProperties.acceptVisitor(visitor);
        }

        for (Section section : sections) {
            section.acceptVisitor(visitor);
        }

        visitor.visitPage(this);
    }
}
