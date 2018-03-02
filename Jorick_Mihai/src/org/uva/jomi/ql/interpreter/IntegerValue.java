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
	public GenericValue add(IntegerValue value) {
		return new IntegerValue(this.getValue() + value.getValue());
	}

	@Override
	public GenericValue add(GenericValue value) {
		return value.add(this);
	}
}
