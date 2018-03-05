package nl.khonraad.domain;

public enum Type {

	Boolean("boolean"), Integer("integer"), Money("money");

	private String text;

	Type(String text) {
		this.text = text;
	}

	public static Type fromString(String text) {
		for (Type type : Type.values()) {
			if (type.text.equalsIgnoreCase(text)) {
				return type;
			}
		}
		return null;
	}
}
