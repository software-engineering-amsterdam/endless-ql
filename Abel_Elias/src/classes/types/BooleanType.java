package classes.types;

import classes.CodeBlock;

public class BooleanType extends Type {
    public BooleanType(CodeBlock code, String typeName) {
        super(code, "boolean");
    }

    public BooleanType() {
        super(new CodeBlock(-1, 1), "boolean");
    }




}
