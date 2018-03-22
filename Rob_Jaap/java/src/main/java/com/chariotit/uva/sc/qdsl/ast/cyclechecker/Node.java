package com.chariotit.uva.sc.qdsl.ast.cyclechecker;

import java.util.HashSet;
import java.util.Set;

public class Node {

    private String label;
    private Set<Node> dependencies;

    public Node(String label) {
        this.label = label;
        this.dependencies = new HashSet<>();
    }

    public String getLabel() {
        return label;
    }

    public void addDependency(Node node) {
        dependencies.add(node);
    }

    public Set<Node> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Node)obj).getLabel().equals(label);
    }
}
