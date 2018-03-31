package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

public abstract class BlockElement extends ASTNode {

    public BlockElement(MetaInformation metaInformation) {
        super(metaInformation);
    }
}
