package org.uva.jomi.ui.views.fields;

import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.views.ViewGenerator;

public abstract class InputField implements ViewGenerator{
	
	protected InputFieldListener listener;
	
	public abstract GenericValue getValue();
	
	public abstract void setValue(GenericValue value);
	
	public abstract void setEnabled(boolean enabled);
	
	public abstract void clearValue();
	
	public void setFieldListener(InputFieldListener listener) {
		this.listener = listener;
	}
	
	public interface InputFieldListener {
		void valueDidChange(InputField field);
	}

	
}
