package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.visitors.ASTNodeVisitor;

public class SpinboxWidget extends Widget {

    private IntegerParameters parameters;

    public SpinboxWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    public SpinboxWidget(MetaInformation metaInformation, IntegerParameters parameters) {
        super(metaInformation);
        this.parameters = parameters;
    }

    public IntegerParameters getParameters() {
        return parameters;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
