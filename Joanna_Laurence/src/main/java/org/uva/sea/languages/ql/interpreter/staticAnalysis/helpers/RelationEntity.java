package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import java.util.AbstractMap;
import java.util.Map;

public class RelationEntity<T> extends AbstractMap.SimpleEntry<T,T> {
    public RelationEntity(T t, T t2) {
        super(t, t2);
    }

    public RelationEntity(Map.Entry<? extends T, ? extends T> entry) {
        super(entry);
    }
}
