package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.AstNode;

public abstract class Stmt extends AstNode {
	public interface Visitor<T> {
		T visitFormStmt(FormStmt stmt);
		T visitBlockStmt(BlockStmt stmt);
		T visitQuestionStmt(QuestionStmt stmt);
		T visitComputedQuestionStmt(ComputedQuestionStmt stmt);
		T visitIfStmt(IfStmt stmt);
		T visitIfElseStmt(IfElseStmt stmt);
	}
	
	public abstract <T> T accept(Visitor<T> visitor);
}