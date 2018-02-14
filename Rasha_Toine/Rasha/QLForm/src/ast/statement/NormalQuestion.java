package ast.statement;

import utils.CodeReference;
import utils.TypeEnum;

public class NormalQuestion extends Question {
	//TODO what condition?
	
	public NormalQuestion(String id, String name, TypeEnum type, CodeReference location) {
		super(id, name, type, location);
	}
}