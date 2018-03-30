package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.ErrorHandler;

public class IdentifierResolver implements Expression.Visitor<Void>, Statement.Visitor<Void> {

	public final IdentifierStack identifierStack;
	private final ErrorHandler errorHandler;

	public IdentifierResolver() {
		this(false);
	}

	public IdentifierResolver(boolean printErrors) {
		this.identifierStack = new IdentifierStack();
		this.errorHandler = new ErrorHandler(this.getClass().getSimpleName(), printErrors);
	}

	public void resolve(List<Statement> statements) {
		// Clear previous errors first
		errorHandler.clearErrors();

		for (Statement statment : statements) {
			statment.accept(this);
		}
	}

	public List<String> getErrors() {
		return errorHandler.getReports();
	}

	public int getNumberOfErrors() {
		return errorHandler.getNumberOfErrors();
	}

	// This method was added for testing purposes
	public String getErrorAtIndex(int index) {
		return errorHandler.getErrorAtIndex(index);
	}

	// Return a list that contains only question statements
	public List<QuestionStatement> filterQuestionStmt(List<Statement> statements) {
		List<QuestionStatement> questions = new ArrayList<>();

		statements.stream().filter(questionStmt -> questionStmt instanceof QuestionStatement)
		.forEach(question -> questions.add((QuestionStatement) question));

		return questions;
	}

	public boolean findDuplicatedIdentifier(IdentifierExpression identifier) {
		if (identifierStack.isInCurrentScope(identifier.getName())) {
			errorHandler.addIdentifierError(identifier.getToken(), "Read-only identifier already declared the current scope");
			return true;
		} else {
			IdentifierExpression outsideIdentifier = identifierStack.getIdentifier(identifier.getName());
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

	public void visitBinaryExpr(BinaryExpression expr) {
		expr.visitLeftExpression(this);
		expr.visitRightExpression(this);
	}

	@Override
	public Void visit(FormStatement stmt) {
		stmt.visitBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(BlockStatement stmt) {
		// Create a new scope for the block statement.
		identifierStack.enterScope();

		// Make sure we add all the question identifiers to the stack before we visit each statement.
		for (QuestionStatement question : filterQuestionStmt(stmt.getStatements())) {
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
	public Void visit(QuestionStatement stmt) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement stmt) {

		// Visit the expression.
		stmt.visitExpression(this);
		return null;
	}

	@Override
	public Void visit(IfStatement stmt) {
		stmt.visitExpression(this);
		stmt.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement stmt) {
		stmt.visitExpression(this);
		stmt.visitIfBlockStatement(this);
		stmt.visitElseBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IdentifierExpression identifier) {
		// Search the identifier
		IdentifierExpression retrievedIdentifier = identifierStack.getIdentifier(identifier.getName());

		if (retrievedIdentifier != null) {
			identifier.updateAllFields(retrievedIdentifier);
		} else {
			errorHandler.addIdentifierError(identifier.getToken(), "Undeclared identifier");
		}

		return null;
	}

	@Override
	public Void visit(GroupingExpression expr) {
		expr.visitInnerExpression(this);
		return null;
	}

	@Override
	public Void visit(AdditionExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(SubtractionExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(MultiplicationExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(DivisionExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(LessThanExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(GreaterThanExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(NotEqualExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(EqualExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(AndExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(OrExpression expr) {
		visitBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(UnaryNotExpression expr) {
		expr.visitRightExpression(this);
		return null;
	}

	@Override
	public Void visit(IntegerExpression expr) {
		return null;
	}

	@Override
	public Void visit(StringExpression expr) {
		return null;
	}

	@Override
	public Void visit(BooleanExpression expr) {
		return null;
	}
}