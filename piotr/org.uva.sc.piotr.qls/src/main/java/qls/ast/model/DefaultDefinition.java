package qls.ast.model;

import qls.ast.model.properties.TypeProperty;
import qls.ast.visitors.ASTNodeVisitor;

import java.util.List;

public class DefaultDefinition extends BlockElement {

    private final String dataType;
    private List<TypeProperty> typeProperties;

    public DefaultDefinition(MetaInformation metaInformation, String dataType, List<TypeProperty> typeProperties) {
        super(metaInformation);
        this.dataType = dataType;
        this.typeProperties = typeProperties;
    }

    public String getDataType() {
        return dataType;
    }

    public List<TypeProperty> getTypeProperties() {
        return typeProperties;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
