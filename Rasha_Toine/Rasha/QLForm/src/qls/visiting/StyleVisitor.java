package qls.visiting;

import qls.ast.style.*;

public interface StyleVisitor<T, U> {
	
	public T visit(HeightProperty property, U ctx);
	public T visit(WidthProperty property, U ctx);
	
	public T visit(FontColor property, U ctx);
	public T visit(FontSize property, U ctx);
	public T visit(FontName property, U ctx);
}
