package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.model.properties.parameters.BooleanParameters;
import qls.ast.visitors.ASTNodeVisitor;

public class DropdownWidget extends Widget {

    private BooleanParameters parameters;

    public DropdownWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    public DropdownWidget(MetaInformation metaInformation, BooleanParameters parameters) {
        super(metaInformation);
        this.parameters = parameters;
    }

    @Override
    public BooleanParameters getParameters() {
        return parameters;
    }

    @Override
    public String getName() {
        return "dropdown";
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
