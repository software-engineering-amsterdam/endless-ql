package org.uva.jomi.ql.ast;

import java.util.List;

abstract class Stmt extends AstNode {
	interface Visitor<T> {
		T visitFormStmt(FormStmt stmt);
		T visitBlockStmt(BlockStmt stmt);
		T visitQuestionStmt(QuestionStmt stmt);
	}
	
	abstract <T> T accept(Visitor<T> visitor);
}

class FormStmt extends Stmt {
	final IndentifierExpr identifier;
	final BlockStmt blockStmt;
	
	public FormStmt(IndentifierExpr identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitFormStmt(this);
	}
}

class BlockStmt extends Stmt {
	final List<Stmt> statements;
	
	public BlockStmt(List<Stmt> statements) {
		this.statements = statements;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitBlockStmt(this);
	}
}

class QuestionStmt extends Stmt {
	final IndentifierExpr identifier;
	final String label;
	final QLType type;
	final Expr expression;

	public QuestionStmt(IndentifierExpr identifier, String label, QLType type) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
		this.expression = null;
	}
	
	public QuestionStmt(IndentifierExpr identifier, String label, QLType type, Expr expression) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
		this.expression = expression;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitQuestionStmt(this);
	}
}