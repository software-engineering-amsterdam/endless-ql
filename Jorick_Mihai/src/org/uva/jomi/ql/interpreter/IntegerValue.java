package org.uva.jomi.ql.interpreter;

public class IntegerValue implements GenericValue {
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}

	@Override
	public GenericValue add(IntegerValue leftHandSideValue) {
		return new IntegerValue(leftHandSideValue.getValue() + this.getValue());
	}

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		return rightHandSideValue.add(this);
	}

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		addError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		addError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
}
