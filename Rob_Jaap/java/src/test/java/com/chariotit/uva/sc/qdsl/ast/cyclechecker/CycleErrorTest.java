package com.chariotit.uva.sc.qdsl.ast.cyclechecker;

import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.Node;
import org.junit.Test;

import static org.junit.Assert.*;

public class CycleErrorTest {

    @Test
    public void testEquals() {

        Node nodeA = new Node("nodeA");
        Node nodeB = new Node("nodeB");
        Node nodeC = new Node("nodeC");

        CycleError errorA = new CycleError(nodeA, nodeB);
        CycleError errorB = new CycleError(nodeB, nodeA);
        CycleError errorC = new CycleError(nodeA, nodeC);

        assertTrue(errorA.equals(errorB));
        assertFalse(errorA.equals(errorC));

    }
}
