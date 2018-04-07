package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.model.properties.parameters.OptionalParameters;
import qls.ast.visitors.ASTNodeVisitor;

public class TextWidget extends Widget {
    public TextWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    @Override
    public String getName() {
        return "text";
    }

    @Override
    public OptionalParameters getParameters() {
        return null;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
