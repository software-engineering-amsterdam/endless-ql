package org.uva.sea.ql.traverse;

public interface Visitable {
    void accept(Visitor visitor);
}
