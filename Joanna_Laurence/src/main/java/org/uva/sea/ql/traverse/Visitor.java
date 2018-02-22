package org.uva.sea.ql.traverse;

import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;

public interface Visitor<T> {
    T visit(Addition node);

    T visit(And node);

    T visit(Division node);

    T visit(Equal node);

    T visit(GreaterOrEqual node);

    T visit(GreaterThan node);

    T visit(LessOrEqual node);

    T visit(LessThan node);

    T visit(Multiplication node);

    T visit(Negative node);

    T visit(NotEqual node);

    T visit(Not node);

    T visit(Or node);

    T visit(Positive node);

    T visit(Subtraction node);

    T visit(Bool node);

    T visit(DateExpr node);

    T visit(Dec node);

    T visit(Money node);

    T visit(Int node);

    T visit(Str node);

    T visit(Type node);

    T visit(Var node);

    T visit(Condition node);

    T visit(Form node);

    T visit(Question node);

    T visit(Statement node);

    T visit(Statements node);

    T visit(DualNode node);

    T visit(SingleNode node);
}
