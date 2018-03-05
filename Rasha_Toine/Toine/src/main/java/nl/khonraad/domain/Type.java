package nl.khonraad.domain;

public enum Type {

	Boolean("boolean"), Integer("integer"), Money("money");

	private String text;

	Type(String conversionType) {
		this.text = conversionType;
	}

	public String getText() {
		return this.text;
	}

	public static Type fromString(String text) {
		for (Type b : Type.values()) {
			if (b.text.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
}
