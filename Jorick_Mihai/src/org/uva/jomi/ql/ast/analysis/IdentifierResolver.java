package org.uva.jomi.ql.ast.analysis;

import java.util.HashMap;
import java.util.Stack;

import org.uva.jomi.ql.ast.expressions.*;

public class IdentifierResolver {
	private int numberOfErrors = 0;
	private final Stack<HashMap<String, IdentifierExpr>> indentifierStack;
	
	// Create a new stack
	public IdentifierResolver() {
		this.indentifierStack = new Stack<HashMap<String, IdentifierExpr>>();
	}
	
	public int getNumberOfErrors() {
		return numberOfErrors;
	}

	public void incrementNumberOfErrors() {
		this.numberOfErrors++;
	}
	
	// Create a new scope
	public void enterScope() {
		indentifierStack.push(new HashMap<String, IdentifierExpr>());
	}
	
	// Destroy the top scope
	public void leaveScope() {
		indentifierStack.pop();
	}
	
	// Add a new element to the inner most scope
	public void add(IdentifierExpr identifier) {
		indentifierStack.peek().put(identifier.token.getLexeme(), identifier);
	}
	
	// Try to add a new element to the top most scope
	public boolean tryAdd(String name, IdentifierExpr indetifier) {
		if (indentifierStack.isEmpty()) {
			System.err.println("Empty Stack - Could not add the element");
			return false;
		}
		indentifierStack.peek().put(name, indetifier);
		return true;
	}
	
	// Check if a particular identifier is present in the inner most scope
	public boolean isInCurrentScope(String identifierName) {
		if (indentifierStack.peek().containsKey(identifierName)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Search from the inner to the outer most scope for a particular identifier name
	public IdentifierExpr getIndetifier(String name) {
		for (HashMap<String, IdentifierExpr> map : indentifierStack) {
			if (map.containsKey(name)) {
				return map.get(name);
			}
		}

		return null;
	}
}