package org.uva.sea.languages.qls.parser.visitor;

import org.uva.sea.languages.qls.parser.elements.Page;
import org.uva.sea.languages.qls.parser.elements.Parameter;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.DefaultStyle;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.elements.specification.Section;
import org.uva.sea.languages.qls.parser.elements.specification.Specification;
import org.uva.sea.languages.qls.parser.elements.style.*;

public class BaseStyleASTVisitor<T> implements IStyleASTVisitor<T> {

    public T visit(Stylesheet node) throws InterruptedException {
        for (Page page : node.getPages()) {
            page.accept(this);
        }
        return null;
    }

    public T visit(Parameter node) {
        return null;
    }

    public T visit(Page node) throws InterruptedException {
        for (Specification element : node.getSpecificationList()) {
            element.accept(this);
        }
        return null;
    }

    public T visit(Color node) {
        return null;
    }

    public T visit(Font node) {
        return null;
    }

    public T visit(FontSize node) {
        return null;
    }

    public T visit(Widget node) {
        for (Parameter element : node.getParameters()) {
            element.accept(this);
        }
        return null;
    }

    public T visit(Width node) {
        return null;
    }

    public T visit(DefaultStyle node) throws InterruptedException {
        for (StyleSpecification element : node.getStyleSpecificationList()) {
            element.accept(this);
        }
        return null;
    }

    public T visit(Question node) throws InterruptedException {
        return node.getWidget().accept(this);
    }

    public T visit(Section node) throws InterruptedException {
        for (Specification element : node.getSpecifications()) {
            element.accept(this);
        }
        return null;
    }
}
