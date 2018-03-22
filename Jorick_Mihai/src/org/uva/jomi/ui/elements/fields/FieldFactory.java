package org.uva.jomi.ui.elements.fields;

public class FieldFactory {
	
	public InputField getField(String type) {		
		if(type == "string") {
			return new TextField();
		} else if(type == "boolean") {
			return new BooleanField();
		} else if(type == "integer") {
			return new IntegerField();
		}
		
		return null;
	}
	
}
