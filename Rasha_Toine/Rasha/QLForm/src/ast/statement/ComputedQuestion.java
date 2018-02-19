package ast.statement;

import ast.expression.Expression;
import ast.literal.Identifier;
import ast.type.Type;
import utils.CodeReference;
import visiting.StatementVisitor;

public class ComputedQuestion extends Question {

	private final Expression expression;

	public ComputedQuestion(Identifier id, String name, Type type, Expression expression, CodeReference location) {
		super(id, name, type, location);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
