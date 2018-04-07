package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.model.properties.parameters.BooleanParameters;
import qls.ast.visitors.ASTNodeVisitor;

public class CheckboxWidget extends Widget {

    public CheckboxWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    @Override
    public BooleanParameters getParameters() {
        return null;
    }

    @Override
    public String getName() {
        return "checkbox";
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
