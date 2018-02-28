package org.uva.ql.visitor;

import org.uva.ql.ast.type.*;

public interface TypeVisitor<T, C> {

    T visit(BooleanType booleanType, C context);

    T visit(IntegerType integerType, C context);

    T visit(StringType stringType, C context);

    T visit(MoneyType moneyType, C context);
}
