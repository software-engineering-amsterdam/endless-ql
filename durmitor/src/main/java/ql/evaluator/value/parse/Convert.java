package ql.evaluator.value.parse;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Type;
import ql.exceptions.Inconvertible;
import ql.visitors.interfaces.ValueVisitor;

public abstract class Convert implements ValueVisitor {

	protected Type target;
	
    @Override
    public Literal<?> visit(BoolLiteral value) {
		throw new Inconvertible(target, value.getType());
    }

    @Override
    public Literal<?> visit(StrLiteral value) {
		throw new Inconvertible(target, value.getType());
    }

    @Override
    public Literal<?> visit(IntLiteral value) {
		throw new Inconvertible(target, value.getType());
    }

    @Override
    public Literal<?> visit(DecimalLiteral value) {
		throw new Inconvertible(target, value.getType());
    }

    @Override
    public Literal<?> visit(MoneyLiteral value) {
		throw new Inconvertible(target, value.getType());
    }

    @Override
    public Literal<?> visit(DateLiteral value) {
		throw new Inconvertible(target, value.getType());
    }

    @Override
    public Literal<?> visit(UndefinedLiteral value) {
		throw new Inconvertible(target, value.getType());
    }
}
