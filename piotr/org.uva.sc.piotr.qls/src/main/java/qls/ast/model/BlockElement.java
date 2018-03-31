package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

public class BlockElement extends ASTNode {

    public BlockElement(MetaInformation metaInformation) {
        super(metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
