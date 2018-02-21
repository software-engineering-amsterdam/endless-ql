package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

import java.util.ArrayList;
import java.util.List;

public class Statements extends ASTNode {

    private List<ASTNode> statementList;

    public Statements() {
        statementList = new ArrayList<ASTNode>();
    }

    public List<ASTNode> getStatementList() {
        return statementList;
    }

    public void addStatement(ASTNode item) {
        this.statementList.add(item);
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doStatements(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        for (ASTNode node: this.statementList) {
            node.doTraversal(traverse,traverseType);
        }
    }

    public Type getType() {
        return new Type("undefined");
    }
}
