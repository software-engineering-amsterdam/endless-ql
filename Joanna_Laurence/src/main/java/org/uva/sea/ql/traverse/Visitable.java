package org.uva.sea.ql.traverse;

public interface Visitable<T> {
    <V extends T> V accept(Visitor visitor);
}
