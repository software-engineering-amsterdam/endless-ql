package ast.statement;

import utils.CodeReference;
import utils.TypeEnum;

public class Question extends Statement {

	private final String id;
	private final String name;
	private final TypeEnum type;

	public Question(String id, String name, TypeEnum type, CodeReference location) {
		super(location);
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public String getIdentifier() {
		return id;
	}

	public String getName() {
		return name;
	}

	public TypeEnum getType() {
		return type;
	}

}