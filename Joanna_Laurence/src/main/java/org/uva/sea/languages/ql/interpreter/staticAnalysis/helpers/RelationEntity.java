package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import java.util.AbstractMap.SimpleEntry;

public class RelationEntity<T> extends SimpleEntry<T, T> {
    public RelationEntity(T t, T t2) {
        super(t, t2);
    }
}
