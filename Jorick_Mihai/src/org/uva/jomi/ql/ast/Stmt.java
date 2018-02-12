package org.uva.jomi.ql.ast;

public abstract class Stmt extends AstNode {
	public interface Visitor<T> {
		T visitFormStmt(FormStmt stmt);
		T visitBlockStmt(BlockStmt stmt);
		T visitQuestionStmt(QuestionStmt stmt);
		T visitIfStmt(IfStmt stmt);
	}
	
	abstract <T> T accept(Visitor<T> visitor);
}