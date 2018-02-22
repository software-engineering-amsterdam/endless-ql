package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.AstNode;

public abstract class Stmt extends AstNode {
	public interface Visitor<T> {
		T visit(FormStmt stmt);
		T visit(BlockStmt stmt);
		T visit(QuestionStmt stmt);
		T visit(ComputedQuestionStmt stmt);
		T visit(IfStmt stmt);
		T visit(IfElseStmt stmt);
	}
	
	public abstract <T> T accept(Visitor<T> visitor);
}