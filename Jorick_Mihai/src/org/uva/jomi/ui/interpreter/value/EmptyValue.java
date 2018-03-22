package org.uva.jomi.ui.interpreter.value;

public class EmptyValue implements GenericValue {

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue add(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue subtract(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue subtract(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue subtract(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue subtract(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue multiply(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue multiply(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue multiply(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue multiply(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue divide(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue divide(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue divide(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue divide(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue and(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue and(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue and(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue and(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue or(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue or(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue or(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue or(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue less(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue less(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue less(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue less(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue lessOrEqual(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue lessOrEqual(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue lessOrEqual(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue lessOrEqual(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greater(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greater(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greater(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greater(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greaterOrEqual(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greaterOrEqual(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greaterOrEqual(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue greaterOrEqual(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue equal(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue equal(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue equal(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue equal(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue notEqual(GenericValue rightHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue notEqual(IntegerValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue notEqual(StringValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue notEqual(BooleanValue leftHandSideValue) {
		return new EmptyValue();
	}

	@Override
	public GenericValue negate() {
		return new EmptyValue();
	}

}
