package qls.ast.rule;

import java.util.List;

import ql.ast.type.Type;
import qls.ast.style.StyleProperty;
import qls.ast.widget.QLSWidget;
import qls.visiting.ItemVisitor;

public class TypeItem extends Item {
	private Type type;

	//constructor
	public TypeItem(QLSWidget widget, Type type, List<StyleProperty> properties) {
		super(widget, properties);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public <T, U> T accept(ItemVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
	
}