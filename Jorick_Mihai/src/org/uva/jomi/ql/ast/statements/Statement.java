package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.AstNode;

public abstract class Statement extends AstNode {
	public interface Visitor<T> {
		T visit(FormStatement stmt);
		T visit(BlockStatement stmt);
		T visit(QuestionStatement stmt);
		T visit(ComputedQuestionStatement stmt);
		T visit(IfStatement stmt);
		T visit(IfElseStatement stmt);
	}
	
	public abstract <T> T accept(Visitor<T> visitor);
}