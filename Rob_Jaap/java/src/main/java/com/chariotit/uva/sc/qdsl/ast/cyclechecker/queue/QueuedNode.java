package com.chariotit.uva.sc.qdsl.ast.cyclechecker.queue;

import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class QueuedNode {
    private Node node;
    private List<Node> recursiveStack;

    public QueuedNode(Node node, List<Node> recursiveStack) {
        this.node = node;
        this.recursiveStack = recursiveStack;
    }

    public Node getNode() {
        return node;
    }

    public static QueuedNode createExtended(QueuedNode queuedNode, Node node) {
        List<Node> extendedStack = new ArrayList<>(queuedNode.recursiveStack);
        extendedStack.add(node);
        return new QueuedNode(node, extendedStack);
    }

    public boolean hasVisited(Node node) {
        return recursiveStack.contains(node);
    }
}
