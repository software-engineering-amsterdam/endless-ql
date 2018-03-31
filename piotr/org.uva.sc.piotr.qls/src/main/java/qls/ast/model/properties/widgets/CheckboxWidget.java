package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.visitors.ASTNodeVisitor;

public class CheckboxWidget extends Widget {

    private BooleanParameters parameters;

    public CheckboxWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    public CheckboxWidget(MetaInformation metaInformation, BooleanParameters parameters) {
        super(metaInformation);
        this.parameters = parameters;
    }

    public BooleanParameters getParameters() {
        return parameters;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
