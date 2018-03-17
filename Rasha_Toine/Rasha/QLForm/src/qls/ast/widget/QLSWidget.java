package qls.ast.widget;

import ql.ast.AstNode;
import qls.visiting.WidgetVisitor;

public abstract class QLSWidget extends AstNode {

	public abstract <T, U> T accept(WidgetVisitor<T, U> visitor, U ctx);
}

