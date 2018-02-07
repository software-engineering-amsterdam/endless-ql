package ql.ast;

public class AstForm extends AstBlock {
	
	private String name;
	
	AstForm(Ast ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return FORM;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
