package ast.statement;

import ast.expression.Identifier;
import utils.CodeReference;
import utils.TypeEnum;

public class Question extends Statement {

	private final Identifier id;
	private final String name;
	private final TypeEnum type;

	public Question(Identifier id, String name, TypeEnum type, CodeReference location) {
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

	public TypeEnum getType() {
		return type;
	}

}