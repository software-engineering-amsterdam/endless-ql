package org.uva.sea.ql.interpreter.staticAnalysis.helpers;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class Relation<T> {

    private List<AbstractMap.SimpleEntry<T,T>> relations = new ArrayList<>();

    public List<List<T>> getCircularDependacies() {
        throw new NotImplementedException();
    }

    private Relation getTransativeClosure() {
        throw new NotImplementedException();
    }
}
