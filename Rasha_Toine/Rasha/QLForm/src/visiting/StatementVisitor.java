package visiting;

import ast.Block;
import ast.statement.*;

public interface StatementVisitor<T, U> {
	public T visit(Block node, U ctx);
	public T visit(IfThenStatement node, U ctx);
	public T visit(NormalQuestion node, U ctx);
	public T visit(ComputedQuestion node, U ctx);
}
