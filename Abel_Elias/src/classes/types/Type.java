package classes.types;

import classes.CodeBlock;

public abstract class Type {

    private String typeName;
    private CodeBlock code;

    public Type(CodeBlock code, String typeName){
        this.code = code;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
