package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Relation<T> {

    private final Set<RelationEntity<T>> relations = new HashSet<>();

    public final Iterable<RelationEntity<T>> getRelations() {
        return this.relations;
    }

    public final boolean addRelation(final T elementA, final T elementB) {
        return this.relations.add(new RelationEntity<>(elementA, elementB));
    }

    public final Iterable<T> getCircularRelations() {
        final Relation<T> transitiveClosure = this.getTransitiveClosure();
        final Collection<T> circular = new ArrayList<>();
        for (final RelationEntity<T> entry : transitiveClosure.relations) {
            if (entry.getKey().equals(entry.getValue())) {
                circular.add(entry.getKey());
            }
        }

        return circular;
    }

    private Relation<T> getTransitiveClosure() {
        final Relation<T> transitiveClosure = new Relation<>();
        transitiveClosure.addAll(this.relations);

        boolean newElementsAdded;
        do {
            newElementsAdded = false;

            final Iterable<RelationEntity<T>> relations = new HashSet<>(transitiveClosure.relations);
            for (final RelationEntity<T> entry : relations) {
                final T source = entry.getKey();
                final T target = entry.getValue();
                final Set<T> relationTo = transitiveClosure.getRelationTo(target);
                for (final T element : relationTo) {
                    if (transitiveClosure.addRelation(source, element)) {
                        newElementsAdded = true;
                    }
                }
            }
        } while (newElementsAdded);

        return transitiveClosure;
    }

    private void addAll(final Collection<RelationEntity<T>> relations) {
        this.relations.addAll(relations);
    }

    public final boolean contains(final T key, final T value) {
        return this.relations.contains(new RelationEntity<>(key, value));
    }

    private Set<T> getRelationTo(final T element) {
        final Set<T> result = new HashSet<>();
        for (final RelationEntity<T> entry : this.relations) {
            if (entry.getKey().equals(element)) {
                result.add(entry.getValue());
            }
        }

        return result;
    }
}
