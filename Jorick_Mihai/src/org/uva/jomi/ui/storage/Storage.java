package org.uva.jomi.ui.storage;

import java.util.List;

import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.models.Question;

public abstract class Storage {

	public abstract void store(List<Question> questions);
	
}
