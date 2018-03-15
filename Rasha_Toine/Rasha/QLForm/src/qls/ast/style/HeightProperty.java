package qls.ast.style;

import ql.ast.literal.IntegerLiteral;
import qls.visiting.StyleVisitor;

public class HeightProperty extends StyleProperty {
	private IntegerLiteral height;

	//constructor
	public HeightProperty(IntegerLiteral height) {
		this.height = height;
	}

	public int getHeight() {
		return height.getValue().getValue();
	}

	@Override
	public <T, U> T accept(StyleVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this,  ctx);
	}
}