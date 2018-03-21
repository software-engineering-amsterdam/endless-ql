package ql.evaluator.value.parse;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Bool;

public class ToBool extends Convert {

	public ToBool() {
		target = new Bool();
	}
	
    @Override
    public BoolLiteral visit(BoolLiteral value) {
        return value;
    }

    @Override
    public BoolLiteral visit(StrLiteral value) {
        return new BoolLiteral(value.getValue().trim().length() > 0);
    }

    @Override
    public BoolLiteral visit(IntLiteral value) {
        return new BoolLiteral(value.getValue() > 0);
    }

    @Override
    public BoolLiteral visit(DecimalLiteral value) {
        return new BoolLiteral(value.getValue() > 0);
    }

    @Override
    public BoolLiteral visit(MoneyLiteral value) {
        return new BoolLiteral(Double.valueOf(value.getValue()) > 0);
    }

    @Override
    public BoolLiteral visit(UndefinedLiteral value) {
        return new BoolLiteral(false);
    }
}
