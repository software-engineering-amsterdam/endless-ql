package org.uva.ql.validation;

import org.uva.ql.ast.expression.unary.Parameter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DependencyChecker {

    private Set<Dependency> dependencies;

    DependencyChecker(Map<String, List<Parameter>> expressions) {
        this.dependencies = new HashSet<>();

        for (Map.Entry<String, List<Parameter>> entry : expressions.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                dependencies.add(new Dependency(entry.getKey(), parameter.toString()));
            }
        }
    }

    public void execute() {
        for (Dependency pair : transitiveClosure(dependencies)) {
            if (pair.isReflexive()) {
                System.out.println("ERROR: " + pair.getFrom() + " depends on itself.");
            }
        }

        System.out.println(dependencies);
    }

    private Set<Dependency> transitiveClosure (Set<Dependency> dependencyGraph) {
        Set<Dependency> closure = new HashSet<>(dependencyGraph);
        Set<Dependency> reach = initializeMatrix(closure);

        while (!reach.equals(closure)) {
            reach.addAll(closure);
            closure = reach;
        }
        return reach;
    }

    private Set<Dependency> initializeMatrix (Set<Dependency> dependencyGraph) {
        Set<Dependency> matrix = new HashSet<>();

        for (Dependency i : dependencyGraph) {
            for (Dependency j : dependencyGraph) {
                if (i.isTransitive(j)) {
                    matrix.add(new Dependency(i.getFrom(), j.getTo()));
                }
            }
        }
        return matrix;
    }
}
