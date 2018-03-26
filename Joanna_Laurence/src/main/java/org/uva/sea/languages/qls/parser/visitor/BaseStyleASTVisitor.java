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

    public final T visit(final Stylesheet node) {
        for (final Page page : node.getPages()) {
            page.accept(this);
        }
        return null;
    }

    public final T visit(final Parameter node) {
        return null;
    }

    public T visit(final Page node) {
        for (final Specification element : node.getSpecificationList()) {
            element.accept(this);
        }
        return null;
    }

    public T visit(final Color node) {
        return null;
    }

    public T visit(final Font node) {
        return null;
    }

    public T visit(final FontSize node) {
        return null;
    }

    public T visit(final Widget node) {
        for (final Parameter element : node.getParameters()) {
            element.accept(this);
        }
        return null;
    }

    public T visit(final Width node) {
        return null;
    }

    public T visit(final DefaultStyle node) {
        for (final StyleSpecification element : node.getStyleSpecificationList()) {
            element.accept(this);
        }
        return null;
    }

    public T visit(final Question node) {
        final Widget widget = node.getWidget();
        if (widget != null)
            return widget.accept(this);
        return null;
    }

    public T visit(final Section node) {
        for (final Specification element : node.getSpecifications()) {
            element.accept(this);
        }
        return null;
    }
}
