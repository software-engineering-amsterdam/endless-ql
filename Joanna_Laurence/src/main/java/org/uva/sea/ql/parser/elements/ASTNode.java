package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

public interface ASTNode {
    /**
     * Traverse over the node
     * @param traverse How to traverse
     */
    void traverse(Traverse traverse);
    /**
     * Get the node type
     * @return The type
     */
    Type getType();
}
