package org.uva.sea.languages.qls.parser.elements.specification;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.elements.style.StyleSpecification;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class DefaultStyle extends Specification {

    private final String typeName;
    private final List<StyleSpecification> styleSpecificationList;

    public DefaultStyle(final Token token, final String typeName, final List<StyleSpecification> styleSpecificationList) {
        super(token);
        this.styleSpecificationList = styleSpecificationList;
        this.typeName = typeName;
    }

    public final Iterable<StyleSpecification> getStyleSpecificationList() {
        return this.styleSpecificationList;
    }

    public final String getTypeName() {
        return this.typeName;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
