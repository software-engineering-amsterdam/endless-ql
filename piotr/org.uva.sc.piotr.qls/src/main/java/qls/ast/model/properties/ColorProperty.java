package qls.ast.model.properties;

import qls.ast.visitors.ASTNodeVisitor;

public class ColorProperty extends TypeProperty {

    final private String value;

    public ColorProperty(MetaInformation metaInformation, String value) {
        super(metaInformation);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
