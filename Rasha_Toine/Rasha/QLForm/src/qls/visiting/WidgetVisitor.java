package qls.visiting;

import qls.ast.widget.*;

public interface WidgetVisitor<T, U> {
	public T visit(TextField widget, U ctx);
	public T visit(CheckBox widget, U ctx);
	public T visit(DropDown widget, U ctx);
	public T visit(RadioBtn widget, U ctx);
	public T visit(Spinbox widget, U ctx);
	public T visit(Slider widget, U ctx);
}
