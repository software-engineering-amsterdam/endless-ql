package ast.literal;

public class Identifier {

	private final String id;

	public Identifier(String id) {
		this.id = id;
	}

	public String getIdentifier() {
		return id;
	}
}