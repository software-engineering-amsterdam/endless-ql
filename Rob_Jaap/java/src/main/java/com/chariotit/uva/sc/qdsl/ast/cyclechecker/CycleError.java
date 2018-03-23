package com.chariotit.uva.sc.qdsl.ast.cyclechecker;

import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.Node;

public class CycleError {

    private Node nodeA;
    private Node nodeB;

    public CycleError(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CycleError)) {
            return false;
        }

        return ((CycleError)obj).getUniqueLabel().equals(getUniqueLabel());
    }

    @Override
    public int hashCode() {
        return getUniqueLabel().hashCode();
    }

    private String getUniqueLabel() {
        String labelA = nodeA.getLabel();
        String labelB = nodeB.getLabel();

        int compare = labelA.compareTo(labelB);

        if (compare > 0) {
            return labelB + labelA;
        }

        return labelA + labelB;
    }
}
