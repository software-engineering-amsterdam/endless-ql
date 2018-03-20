package org.uva.forcepushql.ast;

public abstract class Node {

    abstract public <T> T accept(ASTVisitor visitor);
    
}
