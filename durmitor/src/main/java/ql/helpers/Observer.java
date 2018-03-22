package ql.helpers;

import ql.ast.expression.literal.Literal;

public interface Observer {
    public void update(Observable observable, Literal<?>[] values);
}
