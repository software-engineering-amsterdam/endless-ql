package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Relation<T> {

    private final Set<RelationEntity<T>> relations = new HashSet<>();

    public Set<RelationEntity<T>> getRelations() {
        return this.relations;
    }

    public boolean addRelation(T elementA, T elementB) {
        return this.relations.add(new RelationEntity<>(elementA, elementB));
    }

    public Iterable<T> getCircularRelations() {
        Relation<T> transitiveClosure = this.getTransitiveClosure();
        Collection<T> circular = new ArrayList<>();
        for (RelationEntity<T> entry : transitiveClosure.getRelations()) {
            if (entry.getKey().equals(entry.getValue())) {
                circular.add(entry.getKey());
            }
        }

        return circular;
    }

    private Relation<T> getTransitiveClosure() {
        Relation<T> transitiveClosure = new Relation<>();
        transitiveClosure.addAll(this.relations);

        boolean newElementsAdded;
        do {
            newElementsAdded = false;

            Iterable<RelationEntity<T>> relations = new HashSet<>(transitiveClosure.getRelations());
            for (RelationEntity<T> entry : relations) {
                T source = entry.getKey();
                T target = entry.getValue();
                Set<T> relationTo = transitiveClosure.getRelationTo(target);
                for (T element : relationTo) {
                    if (transitiveClosure.addRelation(source, element)) {
                        newElementsAdded = true;
                    }
                }
            }
        } while (newElementsAdded);

        return transitiveClosure;
    }

    private void addAll(Collection<RelationEntity<T>> relations) {
        this.relations.addAll(relations);
    }

    public boolean contains(T key, T value) {
        return this.relations.contains(new RelationEntity<>(key, value));
    }

    private Set<T> getRelationTo(T element) {
        Set<T> result = new HashSet<>();
        for (RelationEntity<T> entry : this.relations) {
            if (entry.getKey().equals(element)) {
                result.add(entry.getValue());
            }
        }

        return result;
    }
}
