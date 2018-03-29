package com.chariotit.uva.sc.qdsl.ast.cyclechecker;

import com.chariotit.uva.sc.qdsl.ast.cyclechecker.queue.QueuedNode;
import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.Node;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueuedNodeTest {

    @Test
    public void testCreateExtended() {
        Node nodeA = new Node("nodeA");

        Node stackA = new Node("stackA");
        Node stackB = new Node("stackB");
        Node stackC = new Node("stackC");

        List<Node> recStack = new ArrayList<>(Arrays.asList(
                stackA,
                stackB,
                stackC
        ));

        QueuedNode queuedNode = new QueuedNode(nodeA, recStack);

        Node stackD = new Node("stackD");

        assertTrue(queuedNode.hasVisited(stackA));
        assertTrue(queuedNode.hasVisited(stackB));
        assertTrue(queuedNode.hasVisited(stackC));
        assertFalse(queuedNode.hasVisited(stackD));

        QueuedNode extendedNode = QueuedNode.createExtended(queuedNode, stackD);

        assertTrue(extendedNode.hasVisited(stackA));
        assertTrue(extendedNode.hasVisited(stackB));
        assertTrue(extendedNode.hasVisited(stackC));
        assertTrue(extendedNode.hasVisited(stackD));

        // Check to make sure only extendedNode has Node stackD, not the old QueuedNode.
        assertFalse(queuedNode.hasVisited(stackD));

    }
}
