package org.uva.jomi.ui.elements.fields;

public class FieldFactory {
	
	public InputField getField(String type) {
		
		System.out.println("Return field of type " + type);
		
		if(type == "boolean") {
			return new BooleanField();
		} else if(type == "money") {
			return new MoneyField();
		}
		
		return null;
	}
	
}
