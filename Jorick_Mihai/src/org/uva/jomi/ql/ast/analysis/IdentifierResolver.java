package org.uva.jomi.ql.ast.analysis;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.ErrorHandler;

public class IdentifierResolver implements Expr.Visitor<Void>, Stmt.Visitor<Void> {

	
	public final IdentifierStack identifierStack;
	private final ErrorHandler errorHandler;

	public IdentifierResolver(boolean printErrors) {
		this.identifierStack = new IdentifierStack();
		this.errorHandler = new ErrorHandler(this.getClass().getSimpleName(), printErrors);
	}

	public void resolve(List<Stmt> statements) {
		// Clear previous errors first
		errorHandler.clearErrors();
		
		for (Stmt statment : statements) {
			statment.accept(this);
		}
	}
	
	public int getNumberOfErrors() {
		return errorHandler.getNumberOfErrors();
	}
	
	// This method was added for testing purposes
	public String getErrorAtIndex(int index) {
		return errorHandler.getErrorAtIndex(index);
	}

	/*
	 * Add supplementary method in order to add the question identifier 
	 * to the stack.
	 */
	public void resolveQuestionIdentifier(IdentifierExpr identifier) {
		// First check if the identifier is already present in the current scope
		if (identifierStack.contains(identifier.getName())) {
			errorHandler.addIdentifierError(identifier.getToken(), "Read-only identifier already declared the current scope");
		// Make sure the identifier is not declared in any outside scope
		} else if (identifierStack.getIdentifier(identifier.getName()) != null) {
			errorHandler.addIdentifierError(identifier.getToken(), "Read-only identifier already declared in an outside scope");
		// The identifier is not present in any scope, add it to the top stack;
		} else {
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
		
		/*
		 * Visit the expressions first in case the question identifier is used 
		 * inside the expression.
		 */
		stmt.expression.accept(this);
		
		// Make  sure the question name has not been already declared
		resolveQuestionIdentifier(stmt.getIdentifier());
		return null;
	}

	@Override
	public Void visit(IfStmt stmt) {
		stmt.expression.accept(this);
		stmt.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		stmt.expression.accept(this);
		stmt.ifBlockStmt.accept(this);
		stmt.elseBlockStmt.accept(this);
		return null;
	}

	@Override
	public Void visit(IdentifierExpr identifier) {
		// Search the identifier
		IdentifierExpr retrievedIdentifier = identifierStack.getIdentifier(identifier.getName());
		
		if (retrievedIdentifier != null) {
			identifier.updateAllFields(retrievedIdentifier);
		} else {
			errorHandler.addIdentifierError(identifier.getToken(), "Undeclared identifier");
		}

		return null;
	}

	@Override
	public Void visit(PrimaryExpr expr) {
		return null;
	}

	@Override
	public Void visit(BinaryExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(GroupingExpr expr) {
		expr.getExpression().accept(this);
		return null;
	}

	@Override
	public Void visit(UnaryExpr expr) {
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(AdditionExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(SubtractionExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(MultiplicationExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(DivisionExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(LessThanExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(GreaterThanExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(NotEqualExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(EqualExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(AndExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(OrExpr expr) {
		expr.getLeftExpr().accept(this);
		expr.getRightExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(UnaryNotExpr expr) {
		expr.getRightExpr().accept(this);
		return null;
	}
}