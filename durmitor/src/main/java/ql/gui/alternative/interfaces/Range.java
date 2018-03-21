package ql.gui.alternative.interfaces;

import ql.ast.expression.literal.Literal;

public interface Range {

	Literal<?> getMinimum();
	Literal<?> getMaximum();
	Literal<?> getStepSize();
}
