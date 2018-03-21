package ql.evaluator.value.parse;

import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Decimal;

public class ToDecimal extends Convert {

	public ToDecimal() {
		target = new Decimal();
	}
	
    @Override
    public DecimalLiteral visit(IntLiteral value) {
        return new DecimalLiteral(value.getValue().doubleValue());
    }

    @Override
    public DecimalLiteral visit(DecimalLiteral value) {
        return value;
    }

    @Override
    public DecimalLiteral visit(MoneyLiteral value) {
        return new DecimalLiteral(value.getValue());
    }

    @Override
    public DecimalLiteral visit(UndefinedLiteral value) {
        return new DecimalLiteral();
    }
}
