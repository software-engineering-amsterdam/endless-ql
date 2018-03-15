package qls.ast.widget;

import qls.visiting.WidgetVisitor;

public class TextField extends QLSWidget {
	//constructor
	public TextField() {

	}

	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}
