package org.uva.sea.ql.interpreter.staticAnalysis.helpers;

import java.util.*;

public class Relation<T> {

    private Set<AbstractMap.SimpleEntry<T,T>> relations = new HashSet<>();

    public Set<AbstractMap.SimpleEntry<T, T>> getRelations() {
        return relations;
    }

    /**
     * Add relation from elementA to elementB
     * @param elementA
     * @param elementB
     * @return
     */
    public boolean addRelation(T elementA, T elementB) {
        AbstractMap.SimpleEntry<T, T> element = new AbstractMap.SimpleEntry<>(elementA, elementB);
        if(this.relations.contains(element)) {
            return false;
        }

        this.relations.add(element);
        return true;
    }

    public List<T> getCircularDependacies() {
        List<T> circular = new ArrayList<>();
        for(AbstractMap.SimpleEntry<T,T> entry : this.relations) {
            if(entry.getKey().equals(entry.getValue())) {
                circular.add(entry.getKey());
            }
        }

        return circular;
    }

    private Relation getTransativeClosure() {
        Relation<T> transitiveClosure = new Relation<>();
        transitiveClosure.addAll(this.relations);

        boolean newElementsAdded = false;
        do {
            newElementsAdded = false;
            for(AbstractMap.SimpleEntry<T,T> entry : transitiveClosure.getRelations()) {
                T source = entry.getKey();
                T target = entry.getValue();
                Set<T> relationTo = transitiveClosure.getRelationTo(target);
                for( T element : relationTo) {
                    if(transitiveClosure.addRelation(source, element)) {
                        newElementsAdded = true;
                    }
                }
            }
        } while(newElementsAdded);

        return transitiveClosure;
    }

    private void addAll(Set<AbstractMap.SimpleEntry<T, T>> relations) {
        this.relations.addAll(relations);
    }

    public boolean contains(T key, T value) {
        return relations.contains(new AbstractMap.SimpleEntry<>(key, value));
    }

    public Set<T> getRelationTo(T element) {
        Set<T> result = new HashSet<>();
        for( AbstractMap.SimpleEntry<T,T> entry : this.relations) {
            if(entry.getKey().equals(element)) {
                result.add(entry.getValue());
            }
        }

        return result;
    }
}
