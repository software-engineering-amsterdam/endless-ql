package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.AstNode;

public abstract class Statement extends AstNode {
	public interface Visitor<T> {
		T visit(FormStatement statement);
		T visit(BlockStatement statement);
		T visit(QuestionStatement statement);
		T visit(ComputedQuestionStatement statement);
		T visit(IfStatement statement);
		T visit(IfElseStatement statement);
	}

	public abstract <T> T accept(Visitor<T> visitor);
}