package ast.type;

import visiting.TypeVisitor;
import ast.AstNode;

public abstract class Type extends AstNode {
	
	public static final Type BOOLEAN = new BooleanType();
	public static final Type DATE = new DateType();
	public static final Type STRING = new StringType();
	public static final Type DECIMAL = new DecimalType();
	public static final Type MONEY = new MoneyType();
	public static final Type INTEGER = new IntegerType();

	public abstract <T, U> T accept(TypeVisitor<T, U> visitor, U ctx);
}