package qls.ast.model;

import qls.ast.model.properties.Widget;
import qls.ast.visitors.ASTNodeVisitor;

public class QuestionDefinition extends BlockElement {

    private final String name;
    private Widget widget;

    public QuestionDefinition(MetaInformation metaInformation, String name, Widget widget) {
        super(metaInformation);
        this.name = name;
        this.widget = widget;
    }

    public String getName() {
        return name;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
