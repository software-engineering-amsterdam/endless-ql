package ql.evaluator.value.parse;

import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Int;

public class ToInt extends Convert {

	public ToInt() {
		target = new Int();
	}
	
    @Override
    public IntLiteral visit(IntLiteral value) {
        return value;
    }

    @Override
    public IntLiteral visit(DecimalLiteral value) {
        return new IntLiteral(value.getValue().intValue());
    }

    @Override
    public IntLiteral visit(MoneyLiteral value) {
        return new IntLiteral(value.getValue().intValue());
    }

    @Override
    public IntLiteral visit(UndefinedLiteral value) {
        return new IntLiteral();
    }
}
