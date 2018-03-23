package com.chariotit.uva.sc.qdsl.ast.cyclechecker.tree;

import java.util.HashMap;
import java.util.Map;

public class DependencyTree {

    private HashMap<String, Node> nodes;

    public DependencyTree() {
        this.nodes = new HashMap<>();
    }

    public Node getOrAddNode(String label) {
        Node node = nodes.get(label);

        if (node == null) {
            node = new Node(label);
            nodes.put(label, node);
        }

        return node;
    }

    public void addDependency(Node node, Node dependsOn) {
        node.addDependency(dependsOn);
    }

    // TODO remove when done
    public void print() {
        for (Map.Entry<String, Node> node : nodes.entrySet()) {

            System.out.println(node.getValue());

            for (Node n : node.getValue().getDependencies()) {
                System.out.println("- " + n);
            }
        }
    }

    public HashMap<String,Node> getNodes() {
        return nodes;
    }
}
