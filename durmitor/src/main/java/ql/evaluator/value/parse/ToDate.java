package ql.evaluator.value.parse;

import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Date;

public class ToDate extends Convert {

	public ToDate() {
		target = new Date();
	}
	
    @Override
    public DateLiteral visit(DateLiteral value) {
        return value;
    }

    @Override
    public DateLiteral visit(UndefinedLiteral value) {
        return new DateLiteral();
    }
}
