package org.uva.jomi.ql.interpreter;

public interface GenericValue {
	GenericValue add(GenericValue value);
	GenericValue add(IntegerValue value);
	GenericValue add(StringValue value);
	GenericValue add(BooleanValue value);
	
	default public void addError(Class<?> left, Class<?> right) {
		error(left, right, "add");
	}
	
	default public void error(Class<?> left, Class<?> right, String type) {
		try {
			throw new Exception(String.format("Cannot %s a %s and a %s", type, left.getSimpleName(), right.getSimpleName()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
