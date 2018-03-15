package org.uva.sea.languages.qls.parser.elements.specification;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.elements.style.Widget;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class Question extends Specification {

    private String name;
    private Widget widget;

    public Question(Token token, String name, Widget widget) {
        super(token);
        this.name = name;
        this.widget = widget;
    }

    public String getName() {
        return name;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
