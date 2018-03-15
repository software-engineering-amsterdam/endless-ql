package qls.ast.style;

import ql.ast.literal.IntegerLiteral;
import qls.ast.StyleProperty;
import qls.visiting.StyleVisitor;

public class WidthProperty extends StyleProperty {
	private IntegerLiteral width;

	//constructor
	public WidthProperty(IntegerLiteral width) {
		this.width = width;
	}

	public int getWidth() {
		return width.getValue().getValue();
	}

	@Override
	public <T, U> T accept(StyleVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}