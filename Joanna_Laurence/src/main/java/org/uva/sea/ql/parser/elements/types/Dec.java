package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.Traverse;

public class Dec extends ASTNode {
    private double value;

    public Dec(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doDec(this);
    }

    public Type getType() {
        return new Type("decimal");
    }
}
