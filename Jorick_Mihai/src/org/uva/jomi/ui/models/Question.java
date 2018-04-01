package org.uva.jomi.ui.models;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ui.interpreter.ExpressionEvaluator;
import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.interpreter.value.BooleanValue;
import org.uva.jomi.ui.interpreter.value.EmptyValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;

public class Question {

	private String question;
	private String identifier;
	private String type;
	private Expression expression;
	private List<Expression> conditions = new ArrayList<Expression>();
	
	public Question(String question, String identifier, String type) {
		this.question = question;
		this.identifier = identifier;
		this.type = type;
	}
	
	public Question(String question, String identifier, String type, Expression expression) {
		this(question, identifier, type);
		this.expression = expression;
	}

	public String getQuestion() {
		return question;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getType() {
		return type;
	}
	
	public Expression getExpression() {
		return expression;
	}

	public List<Expression> getConditions() {
		return conditions;
	}
	
	public void setConditions(List<Expression> conditions) {
		this.conditions.addAll(conditions);
	}
	
	public void addCondition(Expression condition) {
		this.conditions.add(condition);
	}
	
	public boolean showQuestion() {
		ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
		
		for(Expression expression: this.conditions) {
			GenericValue genericValue = expressionEvaluator.execute(expression);
			if(genericValue instanceof EmptyValue || !((BooleanValue)genericValue).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	public GenericValue getValue() {
		return SymbolTable.getInstance().get(this.identifier);
	}
	
}
