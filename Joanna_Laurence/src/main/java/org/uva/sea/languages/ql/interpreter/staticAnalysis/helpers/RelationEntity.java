package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;

public class RelationEntity<T> extends SimpleEntry<T, T> {
    public RelationEntity(T t, T t2) {
        super(t, t2);
    }

    public RelationEntity(Entry<? extends T, ? extends T> entry) {
        super(entry);
    }
}
