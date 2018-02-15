package ast.statement;

import ast.expression.Expression;
import ast.expression.Identifier;
import utils.CodeReference;
import utils.TypeEnum;

public class ComputedQuestion extends Question {

	private final Expression expression;

	public ComputedQuestion(Identifier id, String name, TypeEnum type, Expression expression, CodeReference location) {
		super(id, name, type, location);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}
}
