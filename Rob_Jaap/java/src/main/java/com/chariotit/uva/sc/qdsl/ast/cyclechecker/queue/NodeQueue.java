package com.chariotit.uva.sc.qdsl.ast.cyclechecker.queue;

import java.util.ArrayList;
import java.util.List;

public class NodeQueue {

    List<QueuedNode> nodes;

    public NodeQueue() {
        nodes = new ArrayList<>();
    }

    public void add(QueuedNode queuedNode) {
        nodes.add(queuedNode);
    }

    public QueuedNode getNext() {
        return nodes.remove(0);
    }

    public boolean isEmpty() {
        return nodes.size() == 0;
    }
}
