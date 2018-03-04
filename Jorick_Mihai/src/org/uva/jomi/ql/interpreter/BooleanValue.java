package org.uva.jomi.ql.interpreter;

public class BooleanValue implements GenericValue {
	private final Boolean value;
	
	public BooleanValue(Boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return value;
	}

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		addError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue add(IntegerValue leftHandSideValue) {
		addError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		addError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		addError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
}
