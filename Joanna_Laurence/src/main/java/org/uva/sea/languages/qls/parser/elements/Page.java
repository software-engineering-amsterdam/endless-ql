package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.elements.specification.Specification;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Page extends QLSNode {

    private final String name;
    private final List<Specification> specificationList;

    public Page(final Token token, final String name, final List<Specification> specificationList) {
        super(token);
        this.name = name;
        this.specificationList = specificationList;
    }

    public final Iterable<Specification> getSpecificationList() {
        return this.specificationList;
    }

    public final String getName() {
        return this.name;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
