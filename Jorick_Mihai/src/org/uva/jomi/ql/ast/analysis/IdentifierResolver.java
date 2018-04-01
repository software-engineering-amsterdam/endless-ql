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
					String error = String.format("Read-only identifier with a different type is declared in an outside scope (line: %d, column: %d)",
							outsideIdentifier.getLineNumber(), outsideIdentifier.getColumnNumber());
					errorHandler.addIdentifierError(identifier.getToken(), error);
					return true;
				} else {
					String error = String.format("Read-only identifier is already declared in an outside scope (line: %d, column: %d)",
							outsideIdentifier.getLineNumber(), outsideIdentifier.getColumnNumber());
					errorHandler.addIdentifierError(identifier.getToken(), error);
				}
			}
		}

		return false;
	}

	public void visitBinaryExpression(BinaryExpression expression) {
		expression.visitLeftExpression(this);
		expression.visitRightExpression(this);
	}

	@Override
	public Void visit(FormStatement statement) {
		statement.visitBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(BlockStatement statement) {
		// Create a new scope for the block statement.
		identifierStack.enterScope();

		// Make sure we add all the question identifiers to the stack before we visit each statement.
		for (QuestionStatement question : filterQuestionStmt(statement.getStatements())) {
			if (!findDuplicatedIdentifier(question.getIdentifier())) {
				identifierStack.add(question.getIdentifier());
			}
		}

		// Visit every statement in the block.
		statement.getStatements().forEach( blockStatement -> blockStatement.accept(this));

		// Remove the innermost scope
		identifierStack.leaveScope();
		return null;
	}

	@Override
	public Void visit(QuestionStatement statement) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement statement) {

		// Visit the expression.
		statement.visitExpression(this);
		return null;
	}

	@Override
	public Void visit(IfStatement statement) {
		statement.visitExpression(this);
		statement.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement statement) {
		statement.visitExpression(this);
		statement.visitIfBlockStatement(this);
		statement.visitElseBlockStatement(this);
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
	public Void visit(GroupingExpression expression) {
		expression.visitInnerExpression(this);
		return null;
	}

	@Override
	public Void visit(AdditionExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(SubtractionExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(MultiplicationExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(DivisionExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(LessThanExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(GreaterThanExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(NotEqualExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(EqualExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(AndExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(OrExpression expression) {
		visitBinaryExpression(expression);
		return null;
	}

	@Override
	public Void visit(UnaryNotExpression expression) {
		expression.visitRightExpression(this);
		return null;
	}

	@Override
	public Void visit(IntegerExpression expression) {
		return null;
	}

	@Override
	public Void visit(StringExpression expression) {
		return null;
	}

	@Override
	public Void visit(BooleanExpression expression) {
		return null;
	}
}