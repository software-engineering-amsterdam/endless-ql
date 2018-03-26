package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import java.util.AbstractMap.SimpleEntry;

public class RelationEntity<T> extends SimpleEntry<T, T> {
    public RelationEntity(final T t, final T t2) {
        super(t, t2);
    }
}
