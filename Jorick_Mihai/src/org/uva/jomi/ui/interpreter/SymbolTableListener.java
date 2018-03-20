package org.uva.jomi.ui.interpreter;

import org.uva.jomi.ui.interpreter.value.GenericValue;

public interface SymbolTableListener {

	public void update(String key, GenericValue value);
	
}
