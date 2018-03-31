package qls.ast.model.properties;

import qls.ast.model.ASTNode;
import qls.ast.visitors.ASTNodeVisitor;

public abstract class TypeProperty extends ASTNode {
    public TypeProperty(MetaInformation metaInformation) {
        super(metaInformation);
    }
}
