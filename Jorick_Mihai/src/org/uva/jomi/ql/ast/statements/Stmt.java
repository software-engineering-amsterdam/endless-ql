package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.AstNode;

public abstract class Stmt extends AstNode {
	public interface Visitor<T> {
		T visitFormStmt(FormStmt stmt);
		T visitBlockStmt(BlockStmt stmt);
		T visitQuestionStmt(QuestionStmt stmt);
		T visitIfStmt(IfStmt stmt);
	}
	
	public abstract <T> T accept(Visitor<T> visitor);
}