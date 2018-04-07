package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.model.properties.parameters.IntegerParameters;
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

    @Override
    public IntegerParameters getParameters() {
        return parameters;
    }

    @Override
    public String getName() {
        return "spinbox";
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
