package com.chariotit.uva.sc.qdsl.ast.cyclechecker;

import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.DependencyTree;
import com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree.Node;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class CycleCheckerTest {

    @Test
    public void testCycle() {
        DependencyTree tree = new DependencyTree();
        Node node1 = tree.getOrAddNode("node1");
        Node node2 = tree.getOrAddNode("node2");

        tree.addDependency(node1, node2);
        tree.addDependency(node2, node1);

        CycleChecker cycleChecker = new CycleChecker(tree);
        List<CycleError> errors = cycleChecker.checkCycles();

        assertEquals(errors.size(), 1);
    }

    @Test
    public void testSelfCycle() {
        DependencyTree tree = new DependencyTree();
        Node node = tree.getOrAddNode("node1");
        tree.addDependency(node, node);

        assertEquals(tree.getNodes().size(), 1);

        CycleChecker cycleChecker = new CycleChecker(tree);
        List<CycleError> errors = cycleChecker.checkCycles();

        assertEquals(errors.size(), 1);
    }

    @Test
    public void testThreewayCycle() {
        DependencyTree tree = new DependencyTree();
        Node node1 = tree.getOrAddNode("node1");
        Node node2 = tree.getOrAddNode("node2");
        Node node3 = tree.getOrAddNode("node3");

        tree.addDependency(node1, node2);
        tree.addDependency(node2, node3);
        tree.addDependency(node3, node1);

        CycleChecker cycleChecker = new CycleChecker(tree);
        List<CycleError> errors = cycleChecker.checkCycles();

        assertEquals(errors.size(), 3);
    }
}
