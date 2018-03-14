package org.uva.sea.qls.parser.elements.specification;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.elements.style.StyleSpecification;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class DefaultStyle extends Specification {

    private String typeName;
    private List<StyleSpecification> styleSpecificationList;

    public DefaultStyle(Token token, String typeName, List<StyleSpecification> styleSpecificationList) {
        super(token);
        this.styleSpecificationList = styleSpecificationList;
        this.typeName = typeName;
    }

    public List<StyleSpecification> getStyleSpecificationList() {
        return styleSpecificationList;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) throws InterruptedException {
        return visitor.visit(this);
    }
}
