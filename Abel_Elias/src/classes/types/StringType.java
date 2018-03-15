package classes.types;

import classes.CodeBlock;

public class StringType extends Type {
    public StringType(CodeBlock code, String typeName) {
        super(code, typeName);
    }

    public StringType() {
        super(new CodeBlock(-1, -1), "String");
    }
}
