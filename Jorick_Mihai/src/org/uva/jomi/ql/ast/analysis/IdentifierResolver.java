package org.uva.jomi.ql.ast.analysis;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;

public class IdentifierResolver implements Expr.Visitor<Void>, Stmt.Visitor<Void> {

	public final IdentifierStack identifierStack;

	public IdentifierResolver() {
		identifierStack = new IdentifierStack();
	}

	public void resolve(List<Stmt> statements) {
		for (Stmt statment : statements) {
			statment.accept(this);
		}
	}

	public void visitQuestionIdentifierExpr(IdentifierExpr identifier) {

	}

	@Override
	public Void visitFormStmt(FormStmt stmt) {
		stmt.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visitBlockStmt(BlockStmt stmt) {
		// Create a new scope for the block statement
		identifierStack.enterScope();

		// Visit every statement in the block and add it to the statements array.
		for (Stmt statement : stmt.statements) {
			statement.accept(this);
		}

		// Remove the innermost scope
		identifierStack.leaveScope();
		return null;
	}

	@Override
	public Void visitQuestionStmt(QuestionStmt stmt) {
		// Make  sure the question name has not been already declared
		stmt.identifier.accept(this);
		return null;
	}

	@Override
	public Void visitComputedQuestionStmt(ComputedQuestionStmt stmt) {
		// Make  sure the question name has not been already declared
		stmt.identifier.accept(this);
		stmt.expression.accept(this);
		return null;
	}

	@Override
	public Void visitIfStmt(IfStmt stmt) {
		return null;
	}

	@Override
	public Void visitIfElseStmt(IfElseStmt stmt) {
		return null;
	}

	@Override
	public Void visitIdentifierExpr(IdentifierExpr identifier) {
		if (identifierStack.contains(identifier.token.getLexeme())) {
			// TODO - create an error handler
			System.err.printf("[IdentifierResolver] line: %s, column: %s: Duplicated identifier: %s\n",
						identifier.token.getLine(),
						identifier.token.getColumn(),
						identifier.token.getLexeme());

			// Increment the number of identifier resolution errors
			identifierStack.incrementNumberOfErrors();
		} else {
			// Add the identifier to the inner most scope map
			identifierStack.add(identifier);
		}

		return null;
	}

	@Override
	public Void visitPrimaryExpr(PrimaryExpr expr) {
		return null;
	}

	@Override
	public Void visitBinaryExpr(BinaryExpr expr) {
		return null;
	}

	@Override
	public Void visitGroupingExpr(GroupingExpr expr) {
		return null;
	}

	@Override
	public Void visitUnaryExpr(UnaryExpr expr) {
		return null;
	}
}