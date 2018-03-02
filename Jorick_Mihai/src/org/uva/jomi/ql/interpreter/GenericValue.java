package org.uva.jomi.ql.interpreter;

public interface GenericValue {
	GenericValue add(GenericValue value);
	GenericValue add(IntegerValue value);
}
