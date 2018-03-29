package ql.model.expression;

import ql.IQLVisitor;
import ql.model.QLNode;

public abstract class Expression extends QLNode {

    public abstract <T> T accept(IQLVisitor<T> visitor);
}