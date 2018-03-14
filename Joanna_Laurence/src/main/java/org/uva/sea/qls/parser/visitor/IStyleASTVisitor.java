package org.uva.sea.qls.parser.visitor;

import org.uva.sea.qls.parser.elements.Page;
import org.uva.sea.qls.parser.elements.Parameter;
import org.uva.sea.qls.parser.elements.Stylesheet;
import org.uva.sea.qls.parser.elements.specification.DefaultStyle;
import org.uva.sea.qls.parser.elements.specification.Question;
import org.uva.sea.qls.parser.elements.specification.Section;
import org.uva.sea.qls.parser.elements.style.*;

public interface IStyleASTVisitor<T> {

    T visit(Stylesheet node) throws InterruptedException;

    T visit(Parameter node);

    T visit(Page node) throws InterruptedException;

    T visit(Color node);

    T visit(Font node);

    T visit(FontSize node);

    T visit(Widget node);

    T visit(Width node);

    T visit(DefaultStyle node) throws InterruptedException;

    T visit(Question node) throws InterruptedException;

    T visit(Section node) throws InterruptedException;

}
