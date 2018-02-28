package ast.type;

import visiting.TypeVisitor;

public class UndefinedType extends Type {
	
	public UndefinedType() {
		
	}
	
    @Override
    public <T, U> T accept(TypeVisitor<T, U> visitor, U ctx) {
      return visitor.visit(this, ctx);
    }
}
