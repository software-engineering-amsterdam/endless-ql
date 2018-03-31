package ql.model.expression;

import ql.model.QLNode;
import ql.visitor.IQLVisitor;

public abstract class Expression extends QLNode {

    public abstract <T> T accept(IQLVisitor<T> visitor);
}