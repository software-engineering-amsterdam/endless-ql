package classes.types;

import classes.CodeBlock;

public abstract class Value <T extends Object>{
    public static final String BOOLEAN = "BOOLEAN",
            DECIMAL = "DECIMAL",
            INTEGER = "INTEGER",
            DATE = "BOOLEAN",
            STRING = "STRING",
            MONEY = "MONEY";

    private T value;
    private String type;

    Value(T value){
        this.value = value;
    }

    T getValue(){
        return value;
    }

    void setType(String type){
        this.type = type;
    }

    String getType(){
        return type;
    }
}
