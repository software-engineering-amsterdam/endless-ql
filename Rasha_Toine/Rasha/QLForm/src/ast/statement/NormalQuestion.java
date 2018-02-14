package ast.statement;

import ast.expression.Identifier;
import utils.CodeReference;
import utils.TypeEnum;

public class NormalQuestion extends Question {
	//TODO what condition?
	
	public NormalQuestion(Identifier id, String name, TypeEnum type, CodeReference location) {
		super(id, name, type, location);
	}
}