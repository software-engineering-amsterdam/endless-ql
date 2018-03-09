package org.uva.jomi.ui.interpreter.value;

public interface GenericValue {

	Object getValue();

	// Addition.
	GenericValue add(GenericValue rightHandSideValue);
	GenericValue add(IntegerValue leftHandSideValue);
	GenericValue add(StringValue leftHandSideValue);
	GenericValue add(BooleanValue leftHandSideValue);

	// Subtraction.
	GenericValue subtract(GenericValue rightHandSideValue);
	GenericValue subtract(IntegerValue leftHandSideValue);
	GenericValue subtract(StringValue leftHandSideValue);
	GenericValue subtract(BooleanValue leftHandSideValue);

	// Multiplication.
	GenericValue multiply(GenericValue rightHandSideValue);
	GenericValue multiply(IntegerValue leftHandSideValue);
	GenericValue multiply(StringValue leftHandSideValue);
	GenericValue multiply(BooleanValue leftHandSideValue);

	// Division.
	GenericValue divide(GenericValue rightHandSideValue);
	GenericValue divide(IntegerValue leftHandSideValue);
	GenericValue divide(StringValue leftHandSideValue);
	GenericValue divide(BooleanValue leftHandSideValue);

	// And operation.
	GenericValue and(GenericValue rightHandSideValue);
	GenericValue and(IntegerValue leftHandSideValue);
	GenericValue and(StringValue leftHandSideValue);
	GenericValue and(BooleanValue leftHandSideValue);

	// Or operation.
	GenericValue or(GenericValue rightHandSideValue);
	GenericValue or(IntegerValue leftHandSideValue);
	GenericValue or(StringValue leftHandSideValue);
	GenericValue or(BooleanValue leftHandSideValue);

	// less than operation.
	GenericValue less(GenericValue rightHandSideValue);
	GenericValue less(IntegerValue leftHandSideValue);
	GenericValue less(StringValue leftHandSideValue);
	GenericValue less(BooleanValue leftHandSideValue);

	// less than or equal operation.
	GenericValue lessOrEqual(GenericValue rightHandSideValue);
	GenericValue lessOrEqual(IntegerValue leftHandSideValue);
	GenericValue lessOrEqual(StringValue leftHandSideValue);
	GenericValue lessOrEqual(BooleanValue leftHandSideValue);

	// greater than operation.
	GenericValue greater(GenericValue rightHandSideValue);
	GenericValue greater(IntegerValue leftHandSideValue);
	GenericValue greater(StringValue leftHandSideValue);
	GenericValue greater(BooleanValue leftHandSideValue);

	// greater than or equal operation.
	GenericValue greaterOrEqual(GenericValue rightHandSideValue);
	GenericValue greaterOrEqual(IntegerValue leftHandSideValue);
	GenericValue greaterOrEqual(StringValue leftHandSideValue);
	GenericValue greaterOrEqual(BooleanValue leftHandSideValue);

	// equal equal operation.
	GenericValue equal(GenericValue rightHandSideValue);
	GenericValue equal(IntegerValue leftHandSideValue);
	GenericValue equal(StringValue leftHandSideValue);
	GenericValue equal(BooleanValue leftHandSideValue);

	// not equal operation.
	GenericValue notEqual(GenericValue rightHandSideValue);
	GenericValue notEqual(IntegerValue leftHandSideValue);
	GenericValue notEqual(StringValue leftHandSideValue);
	GenericValue notEqual(BooleanValue leftHandSideValue);

	// negation.
	GenericValue negate();

	default public String additionError(Class<?> left, Class<?> right) {
		return error(left, right, "add");
	}

	default public String subtractionError(Class<?> left, Class<?> right) {
		return error(left, right, "subtract");
	}

	default public String multiplicationError(Class<?> left, Class<?> right) {
		return error(left, right, "multiply");
	}

	default public String divisionError(Class<?> left, Class<?> right) {
		return error(left, right, "divide");
	}

	default public String andOperationError(Class<?> left, Class<?> right) {
		return error(left, right, "peform an And operation using");
	}

	default public String orOperationError(Class<?> left, Class<?> right) {
		return error(left, right, "perform an Or operation using");
	}

	default public String compareError(Class<?> left, Class<?> right) {
		return error(left, right, "compare");
	}

	default public String negationError(Class<?> expressionClass) {
		return error(expressionClass, "negate");
	}

	default public String error(Class<?> left, Class<?> right, String action) {
		String error = String.format("RuntimeError: Cannot %s a %s and a %s", action, left.getSimpleName(), right.getSimpleName());
		System.err.println(error);
		return error;
	}

	default public String error(Class<?> expressionClass, String action) {
		String error = String.format("RuntimeError: Cannot %s a %s", action, expressionClass.getSimpleName());
		System.err.println(error);
		return error;
	}
}
