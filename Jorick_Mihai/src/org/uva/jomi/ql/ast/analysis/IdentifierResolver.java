package org.uva.jomi.ql.ast.analysis;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;

public class IdentifierResolver implements Expr.Visitor<Void>, Stmt.Visitor<Void> {

	private int numberOfErrors = 0;
	public final IdentifierStack identifierStack;

	public IdentifierResolver() {
		identifierStack = new IdentifierStack();
	}

	public void resolve(List<Stmt> statements) {
		for (Stmt statment : statements) {
			statment.accept(this);
		}
	}

	public int getNumberOfErrors() {
		return numberOfErrors;
	}

	public void incrementNumberOfErrors() {
		this.numberOfErrors++;
	}

	/*
	 * Add supplementary visit method for questions in order to add a new identifier
	 * to the stack.
	 */
	public void resolveQuestionIdentifier(IdentifierExpr identifier) {
		if (identifierStack.contains(identifier.getName())) {
			// TODO - create an error handler
			System.err.printf("[IdentifierResolver] line: %s, column: %s: Duplicated identifier: %s\n",
						identifier.getLine(),
						identifier.getColumn(),
						identifier.getName());

			// Increment the number of identifier resolution errors
			incrementNumberOfErrors();
		} else {
			// Add the identifier to the inner most scope map
			identifierStack.add(identifier);
		}
	}

	@Override
	public Void visit(FormStmt stmt) {
		stmt.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visit(BlockStmt stmt) {
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
	public Void visit(QuestionStmt stmt) {
		// Make  sure the question name has not been already declared
		resolveQuestionIdentifier(stmt.getIdentifier());
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		// Make  sure the question name has not been already declared
		resolveQuestionIdentifier(stmt.getIdentifier());
		stmt.expression.accept(this);
		return null;
	}

	@Override
	public Void visit(IfStmt stmt) {
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		return null;
	}

	@Override
	public Void visit(IdentifierExpr identifier) {

		if (identifierStack.contains(identifier.getName())) {
			IdentifierExpr retrievedIndetifier = identifierStack.getIdentifier(identifier.getName());
			identifier.updateAllFields(retrievedIndetifier);
		} else {
			// TODO - create an error handler
			System.err.printf("[IdentifierResolver] line: %s, column: %s: Undefined identifier: %s\n",
						identifier.getLine(),
						identifier.getColumn(),
						identifier.getName());

			// Increment the number of identifier resolution errors
			incrementNumberOfErrors();
		}

		return null;
	}

	@Override
	public Void visit(PrimaryExpr expr) {
		return null;
	}

	@Override
	public Void visit(BinaryExpr expr) {
		return null;
	}

	@Override
	public Void visit(GroupingExpr expr) {
		return null;
	}

	@Override
	public Void visit(UnaryExpr expr) {
		return null;
	}

	@Override
	public Void visit(AdditionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(SubtractionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(MultiplicationExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(DivisionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(LessThanExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(GreaterThanExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}
}