package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.visitors.ASTNodeVisitor;

public class TextWidget extends Widget {
    public TextWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
