package ast.statement;

import ast.literal.Identifier;
import ast.type.Type;
import utils.CodeReference;

public abstract class Question extends Statement {

	private final Identifier id;
	private final String name;
	private final Type type;

	public Question(Identifier id, String name, Type type, CodeReference location) {
		super(location);
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public Identifier getIdentifier() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

}