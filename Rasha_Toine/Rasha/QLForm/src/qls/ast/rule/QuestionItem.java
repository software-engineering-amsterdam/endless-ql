package qls.ast.rule;

import java.util.List;

import qls.ast.style.StyleProperty;
import qls.ast.widget.QLSWidget;
import qls.visiting.ItemVisitor;

public class QuestionItem extends Item {
	private String name;

	//constructor
	public QuestionItem(String name, QLSWidget widget, List<StyleProperty> properties) {
		super(widget, properties);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public <T, U> T accept(ItemVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}