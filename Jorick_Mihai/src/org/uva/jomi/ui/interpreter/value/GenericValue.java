package org.uva.jomi.ui.interpreter.value;

public interface GenericValue {

	Object getValue();

	// Addition.
	default GenericValue add(GenericValue rightHandSideValue) {
		throw additionException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue add(IntegerValue leftHandSideValue) {
		throw additionException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue add(StringValue leftHandSideValue) {
		throw additionException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue add(BooleanValue leftHandSideValue) {
		throw additionException(leftHandSideValue.getClass(), this.getClass());
	}

	// Subtraction.
	default GenericValue subtract(GenericValue rightHandSideValue) {
		throw subtractionException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue subtract(IntegerValue leftHandSideValue) {
		throw subtractionException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue subtract(StringValue leftHandSideValue) {
		throw subtractionException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue subtract(BooleanValue leftHandSideValue) {
		throw subtractionException(leftHandSideValue.getClass(), this.getClass());
	}

	// Multiplication.
	default GenericValue multiply(GenericValue rightHandSideValue) {
		throw multiplicationException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue multiply(IntegerValue leftHandSideValue) {
		throw multiplicationException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue multiply(StringValue leftHandSideValue) {
		throw multiplicationException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue multiply(BooleanValue leftHandSideValue) {
		throw multiplicationException(leftHandSideValue.getClass(), this.getClass());
	}

	// Division.
	default GenericValue divide(GenericValue rightHandSideValue) {
		throw divisionException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue divide(IntegerValue leftHandSideValue) {
		throw divisionException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue divide(StringValue leftHandSideValue) {
		throw divisionException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue divide(BooleanValue leftHandSideValue) {
		throw divisionException(leftHandSideValue.getClass(), this.getClass());
	}

	// And operation.
	default GenericValue and(GenericValue rightHandSideValue) {
		throw andOperationException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue and(IntegerValue leftHandSideValue) {
		throw andOperationException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue and(StringValue leftHandSideValue) {
		throw andOperationException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue and(BooleanValue leftHandSideValue) {
		throw andOperationException(leftHandSideValue.getClass(), this.getClass());
	}

	// Or operation.
	default GenericValue or(GenericValue rightHandSideValue) {
		throw orOperationException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue or(IntegerValue leftHandSideValue) {
		throw orOperationException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue or(StringValue leftHandSideValue) {
		throw orOperationException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue or(BooleanValue leftHandSideValue) {
		throw orOperationException(leftHandSideValue.getClass(), this.getClass());
	}

	// less than operation.
	default GenericValue less(GenericValue rightHandSideValue) {
		throw compareException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue less(IntegerValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	default  GenericValue less(StringValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue less(BooleanValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	// less than or equal operation.
	default GenericValue lessOrEqual(GenericValue rightHandSideValue) {
		throw compareException(this.getClass(), rightHandSideValue.getClass());
	}

	default GenericValue lessOrEqual(IntegerValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue lessOrEqual(StringValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue lessOrEqual(BooleanValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	// greater than operation.
	default GenericValue greater(GenericValue rightHandSideValue) {
		throw compareException(this.getClass(), rightHandSideValue.getClass());
	}
	default GenericValue greater(IntegerValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}
	default GenericValue greater(StringValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}
	default GenericValue greater(BooleanValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	// greater than or equal operation.
	default GenericValue greaterOrEqual(GenericValue rightHandSideValue) {
		throw compareException(this.getClass(), rightHandSideValue.getClass());
	}
	
	default GenericValue greaterOrEqual(IntegerValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}
	default GenericValue greaterOrEqual(StringValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	default GenericValue greaterOrEqual(BooleanValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	// equal equal operation.
	default GenericValue equal(GenericValue rightHandSideValue) {
		throw compareException(this.getClass(), rightHandSideValue.getClass());
	}
	
	default GenericValue equal(IntegerValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}
	
	default GenericValue equal(StringValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}
	
	default GenericValue equal(BooleanValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	// not equal operation.
	default GenericValue notEqual(GenericValue rightHandSideValue) {
		throw compareException(this.getClass(), rightHandSideValue.getClass());
	}
	
	default GenericValue notEqual(IntegerValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}
	
	default GenericValue notEqual(StringValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}
	
	default GenericValue notEqual(BooleanValue leftHandSideValue) {
		throw compareException(leftHandSideValue.getClass(), this.getClass());
	}

	// negation.
	default GenericValue negate() {
		throw negationException(this.getClass());
	}

	default public String additionError(Class<?> left, Class<?> right) {
		return error(left, right, "add");
	}

	default public RuntimeException additionException(Class<?> left, Class<?> right) {
		String error = additionError(left, right);
		return new RuntimeException(error);
	}

	default public String subtractionError(Class<?> left, Class<?> right) {
		return error(left, right, "subtract");
	}

	default public RuntimeException subtractionException(Class<?> left, Class<?> right) {
		String error = subtractionError(left, right);
		return new RuntimeException(error);
	}

	default public String multiplicationError(Class<?> left, Class<?> right) {
		return error(left, right, "multiply");
	}

	default public RuntimeException multiplicationException(Class<?> left, Class<?> right) {
		String error = multiplicationError(left, right);
		return new RuntimeException(error);
	}


	default public String divisionError(Class<?> left, Class<?> right) {
		return error(left, right, "divide");
	}

	default public RuntimeException divisionException(Class<?> left, Class<?> right) {
		String error = divisionError(left, right);
		return new RuntimeException(error);
	}

	default public String andOperationError(Class<?> left, Class<?> right) {
		return error(left, right, "peform an And operation using");
	}

	default public RuntimeException andOperationException(Class<?> left, Class<?> right) {
		String error = andOperationError(left, right);
		return new RuntimeException(error);
	}

	default public String orOperationError(Class<?> left, Class<?> right) {
		return error(left, right, "perform an Or operation using");
	}

	default public RuntimeException orOperationException(Class<?> left, Class<?> right) {
		String error = orOperationError(left, right);
		return new RuntimeException(error);
	}

	default public String compareError(Class<?> left, Class<?> right) {
		return error(left, right, "compare");
	}

	default public RuntimeException compareException(Class<?> left, Class<?> right) {
		String error = compareError(left, right);
		return new RuntimeException(error);
	}

	default public String negationError(Class<?> right) {
		return error(right, "negate");
	}

	default public RuntimeException negationException(Class<?> right) {
		String error = negationError(right);
		return new RuntimeException(error);
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
