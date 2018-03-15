package org.uva.sea.languages.qls.parser.elements.specification;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Section extends Specification {

    private final String name;
    private final List<Specification> specifications;

    public Section(Token token, String name, List<Specification> specifications) {
        super(token);
        this.name = name;
        this.specifications = specifications;
    }

    public String getName() {
        return this.name;
    }

    public List<Specification> getSpecifications() {
        return this.specifications;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
