package org.uva.jomi.ql.interpreter;

import javax.management.RuntimeErrorException;

public interface GenericValue {
	// Addition
	GenericValue add(GenericValue value);
	GenericValue add(IntegerValue value);
	GenericValue add(StringValue value);
	GenericValue add(BooleanValue value);
	
	// Subtraction
	GenericValue subtract(GenericValue value);
	GenericValue subtract(IntegerValue value);
	GenericValue subtract(StringValue value);
	GenericValue subtract(BooleanValue value);
	
	default public void addError(Class<?> left, Class<?> right){
		error(left, right, "add");
	}
	
	default public void subtractError(Class<?> left, Class<?> right) {
		error(left, right, "subtract");
	}
	
	default public void error(Class<?> left, Class<?> right, String type) {
		String error = String.format("RuntimeError: Cannot %s a %s and a %s", type, left.getSimpleName(), right.getSimpleName());
		//System.err.println(error);
		throw new RuntimeException(error);
	}
}
