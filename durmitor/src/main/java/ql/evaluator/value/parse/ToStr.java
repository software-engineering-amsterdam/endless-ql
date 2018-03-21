package ql.evaluator.value.parse;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Str;

public class ToStr extends Convert {

	public ToStr() {
		target = new Str();
	}
	
    @Override
    public StrLiteral visit(BoolLiteral value) {
        return new StrLiteral(String.valueOf(value.getValue()));
    }

    @Override
    public StrLiteral visit(StrLiteral value) {
        return value;
    }

    @Override
    public StrLiteral visit(IntLiteral value) {
        return new StrLiteral(String.valueOf(value.getValue()));
    }

    @Override
    public StrLiteral visit(DecimalLiteral value) {
        return new StrLiteral(String.valueOf(value.getValue()));
    }

    @Override
    public StrLiteral visit(MoneyLiteral value) {
        return new StrLiteral(String.valueOf(value.getValue()));
    }

    @Override
    public StrLiteral visit(DateLiteral value) {
        return new StrLiteral(String.valueOf(value.getValue()));
    }

    @Override
    public StrLiteral visit(UndefinedLiteral value) {
        return new StrLiteral();
    }
}
