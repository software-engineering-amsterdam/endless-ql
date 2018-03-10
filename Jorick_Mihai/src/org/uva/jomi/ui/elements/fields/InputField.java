package org.uva.jomi.ui.elements.fields;

import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.interpreter.value.GenericValue;

public abstract class InputField implements BaseElement{
	
	protected InputFieldListener listener;
	
	public abstract GenericValue getValue();
	
	public abstract void setValue(GenericValue value);
	
	public abstract void setEnabled(boolean enabled);
	
	public void setFieldListener(InputFieldListener listener) {
		this.listener = listener;
	}
	
	public interface InputFieldListener {
		void valueDidChange(InputField field);
	}

	
}
