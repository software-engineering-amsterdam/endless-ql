package org.uva.jomi.ui.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.expressions.UnaryNotExpression;
import org.uva.jomi.ql.ast.statements.BlockStatement;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStatement;
import org.uva.jomi.ql.ast.statements.FormStatement;
import org.uva.jomi.ql.ast.statements.IfElseStatement;
import org.uva.jomi.ql.ast.statements.IfStatement;
import org.uva.jomi.ql.ast.statements.QuestionStatement;
import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ui.models.Question;
import org.uva.jomi.ui.views.ViewGenerator;

public class QuestionTraverser implements Statement.Visitor<List<Question>> {

	private List<Expression> conditionalScope = new ArrayList<Expression>();
	
	public List<Question> traverse(List<Statement> statements) {
		List<Question> questions = new ArrayList<Question>();
		
		for (Statement statement : statements) {
			questions.addAll(statement.accept(this));
		}

		return questions;		
	}
	
	@Override
	public List<Question> visit(FormStatement statement) {
		return statement.getBlockStmt().accept(this);
	}

	@Override
	public List<Question> visit(BlockStatement statement) {
		List<Question> questions = new ArrayList<Question>();
		
		for(Statement blockStatement : statement.getStatements()) {
			questions.addAll(blockStatement.accept(this));
		}
		
		return questions;
	}

	@Override
	public List<Question> visit(QuestionStatement statement) {
		Question question = new Question(statement.getLabel(), statement.getIdentifier().getName(), statement.getType().getName());
		// Adding scope expressions
		question.setConditions(this.conditionalScope);
		
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		return questions;
	}

	@Override
	public List<Question> visit(ComputedQuestionStatement statement) {
		Question question = new Question(statement.getLabel(), statement.getIdentifier().getName(), statement.getType().getName(), statement.getExpression());
		// Adding scope expressions
		question.setConditions(this.conditionalScope);
		
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		return questions;
	}

	@Override
	public List<Question> visit(IfStatement statement) {
		List<Question> questions = new ArrayList<Question>();
		
		// Adding expression to the scope
		this.conditionalScope.add(statement.getExpression());
		
		// Walk through if block
		questions.addAll(statement.getIfBlockStatement().accept(this));
		
		// Removing expression from the scope
		this.conditionalScope.remove(statement.getExpression());
		
		return questions;
	}

	@Override
	public List<Question> visit(IfElseStatement statement) {
		List<Question> questions = new ArrayList<Question>();
		
		// Adding expression to the scope
		this.conditionalScope.add(statement.getExpression());
		
		// Walk through if block
		questions.addAll(statement.getIfBlockStatement().accept(this));
		
		// Removing expression from the scope
		this.conditionalScope.remove(statement.getExpression());
		
		// Negation expression for else statement
		Expression negation = new UnaryNotExpression(null, statement.getExpression());
		
		// Adding negation expression to the scope
		this.conditionalScope.add(negation);
		
		// Walk through else block
		questions.addAll(statement.getElseBlockStatement().accept(this));		
		
		// Removing negation expression to the scope
		this.conditionalScope.remove(negation);
		
		return questions;
	}

}
