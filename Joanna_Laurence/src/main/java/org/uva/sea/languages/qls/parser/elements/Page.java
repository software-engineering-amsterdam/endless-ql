package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.elements.specification.Specification;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Page extends QLSNode {

    private final String name;
    private final List<Specification> specificationList;

    public Page(Token token, String name, List<Specification> specificationList) {
        super(token);
        this.name = name;
        this.specificationList = specificationList;
    }

    public List<Specification> getSpecificationList() {
        return this.specificationList;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
