package visiting;

import ast.Block;
import ast.Form;

public interface QLTreeVisitor<T, U> {
	public T visit(Form form, U ctx);
	public T visit(Block block, U ctx);
}