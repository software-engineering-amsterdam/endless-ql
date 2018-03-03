package org.uva.jomi.ql.interpreter;

public interface GenericValue {
	GenericValue add(GenericValue value);
	GenericValue add(IntegerValue value);
	GenericValue add(StringValue value);
	
	default public void addError(Class<?> left, Class<?> right) {
		try {
			throw new Exception(String.format("Cannot add a %s and a %s", left.getSimpleName(), right.getSimpleName()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
