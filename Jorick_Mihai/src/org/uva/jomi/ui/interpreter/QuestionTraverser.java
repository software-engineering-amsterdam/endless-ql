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
	public List<Question> visit(FormStatement stmt) {
		return stmt.getBlockStmt().accept(this);
	}

	@Override
	public List<Question> visit(BlockStatement stmt) {
		List<Question> questions = new ArrayList<Question>();
		
		for(Statement statement : stmt.getStatements()) {
			questions.addAll(statement.accept(this));
		}
		
		return questions;
	}

	@Override
	public List<Question> visit(QuestionStatement stmt) {
		Question question = new Question(stmt.getLabel(), stmt.getIdentifier().getName(), stmt.getType().getName());
		// Adding scope expressions
		question.setConditions(this.conditionalScope);
		
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		return questions;
	}

	@Override
	public List<Question> visit(ComputedQuestionStatement stmt) {
		Question question = new Question(stmt.getLabel(), stmt.getIdentifier().getName(), stmt.getType().getName(), stmt.getExpression());
		// Adding scope expressions
		question.setConditions(this.conditionalScope);
		
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		return questions;
	}

	@Override
	public List<Question> visit(IfStatement stmt) {
		List<Question> questions = new ArrayList<Question>();
		
		// Adding expression to the scope
		this.conditionalScope.add(stmt.getExpression());
		
		// Walk through if block
		questions.addAll(stmt.getIfBlockStatement().accept(this));
		
		// Removing expression from the scope
		this.conditionalScope.remove(stmt.getExpression());
		
		return questions;
	}

	@Override
	public List<Question> visit(IfElseStatement stmt) {
		List<Question> questions = new ArrayList<Question>();
		
		// Adding expression to the scope
		this.conditionalScope.add(stmt.getExpression());
		
		// Walk through if block
		questions.addAll(stmt.getIfBlockStatement().accept(this));
		
		// Removing expression from the scope
		this.conditionalScope.remove(stmt.getExpression());
		
		// Negation expression for else statement
		Expression negation = new UnaryNotExpression(null, stmt.getExpression());
		
		// Adding negation expression to the scope
		this.conditionalScope.add(negation);
		
		// Walk through else block
		questions.addAll(stmt.getElseBlockStatement().accept(this));		
		
		// Removing negation expression to the scope
		this.conditionalScope.remove(negation);
		
		return questions;
	}

}
