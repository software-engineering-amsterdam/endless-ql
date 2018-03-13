package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
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
	
	// Return a list that contains only question statements
	public List<QuestionStmt> filterQuestionStmt(List<Stmt> statements) {
		List<QuestionStmt> questions = new ArrayList<>();
		
		statements.stream().filter(questionStmt -> questionStmt instanceof QuestionStmt)
		.forEach(question -> questions.add((QuestionStmt) question));
		
		return questions;
	}
	
	public boolean findDuplicatedIdentifier(IdentifierExpr identifier) {
		if (identifierStack.isInCurrentScope(identifier.getName())) {
			errorHandler.addIdentifierError(identifier.getToken(), "Read-only identifier already declared the current scope");
			return true;
		} else {
			IdentifierExpr outsideIdentifier = identifierStack.getIdentifier(identifier.getName());
			if (outsideIdentifier != null) {
				if (outsideIdentifier.getType() != identifier.getType()) {
					String error = String.format("Read-only identifier (line: %d, column: %d) with a different type is declared in an outside scope",
							outsideIdentifier.getLineNumber(), outsideIdentifier.getColumnNumber());
					errorHandler.addIdentifierError(identifier.getToken(), error);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void visitBinaryExpr(BinaryExpr expr) {
		expr.visitLeftExpr(this);
		expr.visitRightExpr(this);
	}

	@Override
	public Void visit(FormStmt stmt) {
		stmt.visitBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(BlockStmt stmt) {
		// Create a new scope for the block statement.
		identifierStack.enterScope();
		
		// Make sure we add all the question identifiers to the stack before we visit each statement.
		for (QuestionStmt question : filterQuestionStmt(stmt.getStatements())) {
			if (!findDuplicatedIdentifier(question.getIdentifier())) {
				identifierStack.add(question.getIdentifier());
			}
		}

		// Visit every statement in the block.
		stmt.getStatements().forEach( statement -> statement.accept(this));

		// Remove the innermost scope
		identifierStack.leaveScope();
		return null;
	}

	@Override
	public Void visit(QuestionStmt stmt) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		
		// Visit the expression.
		stmt.visitExpr(this);
		return null;
	}

	@Override
	public Void visit(IfStmt stmt) {
		stmt.visitExpr(this);
		stmt.visitIfBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		stmt.visitExpr(this);
		stmt.visitIfBlockStmt(this);
		stmt.visitElseBlockStmt(this);
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
	public Void visit(GroupingExpr expr) {
		expr.visitInnerExpr(this);
		return null;
	}

	@Override
	public Void visit(AdditionExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(SubtractionExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(MultiplicationExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(DivisionExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(LessThanExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(GreaterThanExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(NotEqualExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(EqualExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(AndExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(OrExpr expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(UnaryNotExpr expr) {
		expr.visitRightExpr(this);
		return null;
	}

	@Override
	public Void visit(IntegerExpr expr) {
		return null;
	}

	@Override
	public Void visit(StringExpr expr) {
		return null;
	}

	@Override
	public Void visit(BooleanExpr expr) {
		return null;
	}
}