package ql.gui.alternative.widget;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Undefined;
import ql.visitors.interfaces.TypeVisitor;

public class DefaultWidget implements TypeVisitor<Widget<?>> {

	@Override
	public Widget<?> visit(Bool type) {
		return new CheckboxWidget(type);
	}

	@Override
	public Widget<?> visit(Str type) {
		return new TextWidget(type);
	}

	@Override
	public Widget<?> visit(Int type) {
		return new TextWidget(type);
	}

	@Override
	public Widget<?> visit(Decimal type) {
		return new TextWidget(type);
	}

	@Override
	public Widget<?> visit(Money type) {
		return new TextWidget(type);
	}

	@Override
	public Widget<?> visit(Date type) {
		return new DateSpinnerWidget(type);
	}

	@Override
	public Widget<?> visit(Undefined type) {
		return null;
	}
}
