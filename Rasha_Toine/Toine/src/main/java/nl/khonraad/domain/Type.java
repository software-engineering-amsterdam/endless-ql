package nl.khonraad.domain;

public enum Type {

	Boolean("boolean"), Integer("integer"), Money("money");

	private String text;

	private Type(String text) {
		this.text = text;
	}

	public static Type fromString(String text) {
		
		for (Type type : Type.values()) {
		
			if (type.text.equalsIgnoreCase(text)) {
				return type;
			}
		}
		
		throw new RuntimeException(
				"CHeck your grammar. Do not know how to instantiate a Type from \"" + text + "\"" );
	}
	
}
