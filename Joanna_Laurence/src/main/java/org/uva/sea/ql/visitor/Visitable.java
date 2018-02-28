package org.uva.sea.ql.visitor;

public interface Visitable<T> {
    <V extends T> V accept(Visitor visitor);
}
