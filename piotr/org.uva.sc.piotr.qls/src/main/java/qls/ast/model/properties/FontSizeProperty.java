package qls.ast.model.properties;

import qls.ast.visitors.ASTNodeVisitor;

public class FontSizeProperty extends TypeProperty {

    final private Integer value;

    public FontSizeProperty(MetaInformation metaInformation, Integer value) {
        super(metaInformation);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
