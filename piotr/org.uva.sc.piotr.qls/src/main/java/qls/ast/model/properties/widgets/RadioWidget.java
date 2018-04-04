package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.visitors.ASTNodeVisitor;

public class RadioWidget extends Widget {

    private BooleanParameters parameters;

    public RadioWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    public RadioWidget(MetaInformation metaInformation, BooleanParameters parameters) {
        super(metaInformation);
        this.parameters = parameters;
    }

    @Override
    public String getName() {
        return "radio";
    }

    public BooleanParameters getParameters() {
        return parameters;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
