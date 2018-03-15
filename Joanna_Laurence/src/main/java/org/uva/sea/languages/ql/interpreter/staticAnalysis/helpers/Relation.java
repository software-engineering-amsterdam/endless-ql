package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Relation<T> {

    private Set<RelationEntity<T>> relations = new HashSet<>();

    public Set<RelationEntity<T>> getRelations() {
        return relations;
    }

    /**
     * Add relation from elementA to elementB
     *
     * @param elementA
     * @param elementB
     * @return
     */
    public boolean addRelation(T elementA, T elementB) {
        return this.relations.add(new RelationEntity<>(elementA, elementB));
    }

    /**
     * Checks if there are circular relations
     *
     * @return List of relations that are circular
     */
    public List<T> getCircularRelations() {
        Relation<T> transitiveClosure = this.getTransitiveClosure();
        List<T> circular = new ArrayList<>();
        for (RelationEntity<T> entry : transitiveClosure.getRelations()) {
            if (entry.getKey().equals(entry.getValue())) {
                circular.add(entry.getKey());
            }
        }

        return circular;
    }

    /**
     * Get transitive closure of the current relation
     *
     * @return
     */
    private Relation<T> getTransitiveClosure() {
        Relation<T> transitiveClosure = new Relation<>();
        transitiveClosure.addAll(this.relations);

        boolean newElementsAdded;
        do {
            newElementsAdded = false;

            Set<RelationEntity<T>> relations = new HashSet<>(transitiveClosure.getRelations());
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

    /**
     * Add a set of relations
     *
     * @param relations
     */
    private void addAll(Set<RelationEntity<T>> relations) {
        this.relations.addAll(relations);
    }

    /**
     * Check if key is related to value
     *
     * @param key
     * @param value
     * @return If it this relation
     */
    public boolean contains(T key, T value) {
        return relations.contains(new RelationEntity<>(key, value));
    }

    /**
     * Find what elements have a relation with element
     *
     * @param element The element
     * @return Set of items that have a relation to element
     */
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
