package org.uva.jomi.ui.elements.fields;

import org.uva.jomi.ui.elements.BaseElement;

public abstract class InputField<T> implements BaseElement{
	
	protected InputFieldListener listener;
	
	public abstract T getValue();
	
	public void setFieldListener(InputFieldListener listener) {
		this.listener = listener;
	}
	
	public interface InputFieldListener {
		void valueDidChange(InputField<?> field);
	}

	
}
