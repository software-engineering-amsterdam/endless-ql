package org.uva.sea.qls.parser.elements.specification;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.elements.style.StyleSpecification;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class DefaultStyle extends Specification {

    private List<StyleSpecification> styleSpecificationList;

    public DefaultStyle(Token token, List<StyleSpecification> styleSpecificationList) {
        super(token);
        this.styleSpecificationList = styleSpecificationList;
    }

    public List<StyleSpecification> getStyleSpecificationList() {
        return styleSpecificationList;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
