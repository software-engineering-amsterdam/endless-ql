package com.chariotit.uva.sc.qdsl.ast.cyclechecker;

import com.chariotit.uva.sc.qdsl.ast.cyclechecker.queue.NodeQueue;
import com.chariotit.uva.sc.qdsl.ast.cyclechecker.queue.QueuedNode;
import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.DependencyTree;
import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.Node;

import java.util.*;

public class CycleChecker {

    private DependencyTree dependencyTree;

    public CycleChecker(DependencyTree dependencyTree) {
        this.dependencyTree = dependencyTree;
    }

    private Set<CycleError> checkCyclesForNode(Node node) {
        Set<CycleError> errors = new HashSet<>();
        NodeQueue queue = new NodeQueue();

        queue.add(new QueuedNode(node, new ArrayList<>()));

        while (!queue.isEmpty()) {
            QueuedNode currentQueued = queue.getNext();
            Node current = currentQueued.getNode();

            for (Node child : current.getDependencies()) {
                if (currentQueued.hasVisited(child)) {

                    // Found cycle
                    errors.add(new CycleError(current, child));
                    continue;
                }

                queue.add(QueuedNode.createExtended(currentQueued, child));
            }
        }

        return errors;
    }

    public List<CycleError> checkCycles() {
        Set<CycleError> errors = new HashSet<>();

        for (Map.Entry<String, Node> nodeEntrySet : dependencyTree.getNodes().entrySet()) {
            errors.addAll(checkCyclesForNode(nodeEntrySet.getValue()));
        }

        return new ArrayList<>(errors);
    }
}
