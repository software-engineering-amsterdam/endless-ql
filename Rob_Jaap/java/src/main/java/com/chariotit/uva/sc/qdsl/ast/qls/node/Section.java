package com.chariotit.uva.sc.qdsl.ast.qls.node;


import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class Section extends SectionElement {

    private List<SectionElement> elements;
    private List<DefaultProperties> defaults;

    public Section(List<SectionElement> elements, List<DefaultProperties> defaults, Integer
            lineNumber,
                   Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.elements = elements;
        this.defaults = defaults;
    }

    public Section(List<SectionElement> elements, Integer lineNumber,
                   Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.elements = elements;
        this.defaults = new ArrayList<>();
    }

    public List<SectionElement> getElements() {
        return elements;
    }

    public void setElements(List<SectionElement> elements) {
        this.elements = elements;
    }

    public List<DefaultProperties> getDefaults() {
        return defaults;
    }

    public void setDefaults(List<DefaultProperties> defaults) {
        this.defaults = defaults;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        for (SectionElement element : elements) {
            element.acceptVisitor(visitor);
        }

        for (DefaultProperties defaultProperties : defaults) {
            defaultProperties.acceptVisitor(visitor);
        }

        visitor.visitSection(this);
    }
}
