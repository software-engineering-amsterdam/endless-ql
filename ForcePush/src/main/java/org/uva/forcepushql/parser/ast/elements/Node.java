package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;

public abstract class Node {

    abstract public <T> T accept(ASTVisitor visitor);

}
