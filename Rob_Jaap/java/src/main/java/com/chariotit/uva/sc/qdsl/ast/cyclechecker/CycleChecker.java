package com.chariotit.uva.sc.qdsl.ast.cyclechecker;

import java.util.*;

public class CycleChecker {

    private DependencyTree dependencyTree;

    public CycleChecker(DependencyTree dependencyTree) {
        this.dependencyTree = dependencyTree;
    }

    private void checkCyclesForNode(Node node) {
        NodeQueue queue = new NodeQueue();

        queue.add(new QueuedNode(node, new ArrayList<>()));

        while (!queue.isEmpty()) {
            QueuedNode currentQueued = queue.getNext();
            Node current = currentQueued.getNode();

            for (Node child : current.getDependencies()) {
                if (currentQueued.hasVisited(child)) {
                    // Cycle between current and child
                    // TODO do something here
                    System.out.println("CYCLE!! " + current.getLabel() + " " + child.getLabel());
                    continue;
                }

                queue.add(currentQueued.createExtended(child));
            }
        }
    }

    public void checkCycles() {
        for (Map.Entry<String, Node> nodeEntrySet : dependencyTree.getNodes().entrySet()) {
            checkCyclesForNode(nodeEntrySet.getValue());
        }
    }
}
