package qls.ast.model.properties.widgets;

import qls.ast.model.properties.Widget;
import qls.ast.visitors.ASTNodeVisitor;

public class SliderWidget extends Widget {

    private IntegerParameters parameters;

    public SliderWidget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    public SliderWidget(MetaInformation metaInformation, IntegerParameters parameters) {
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
