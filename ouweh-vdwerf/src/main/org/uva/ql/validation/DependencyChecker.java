package org.uva.ql.validation;

import org.uva.ql.ast.expression.unary.Parameter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DependencyChecker extends Checker {

    private Set<Dependency> dependencies;

    DependencyChecker(Map<String, List<Parameter>> expressions) {
        this.dependencies = new HashSet<>();

        for (Map.Entry<String, List<Parameter>> entry : expressions.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                dependencies.add(new Dependency(entry.getKey(), parameter.toString()));
            }
        }
    }

    @Override
    public void runCheck() {
        for (Dependency relation : transitiveClosure(dependencies)) {
            if (relation.isReflexive()) {
                logger.severe(String.format("Circular dependency detected at: %s", relation.getFrom()));
            }
        }
    }

    private Set<Dependency> transitiveClosure(Set<Dependency> dependencyGraph) {
        Set<Dependency> closure = new HashSet<>(dependencyGraph);
        Set<Dependency> newClosure = new HashSet<>(closure);

        do {
            closure.addAll(newClosure);

            for (Dependency i : closure) {
                for (Dependency j : closure) {
                    if (i.getTo().equals(j.getFrom())) {
                        newClosure.add(new Dependency(i.getFrom(), j.getTo()));
                    }
                }
            }
        } while (!closure.containsAll(newClosure));

        return newClosure;
    }
}
