package classes.values;

import classes.CodeBlock;

public abstract class Value <T extends Object>{
    public static final String BOOLEAN = "BOOLEAN",
            DECIMAL = "DECIMAL",
            INTEGER = "INTEGER",
            DATE = "DATE",
            STRING = "STRING",
            MONEY = "MONEY";

    private T value;
    private String type;

    Value(T value){
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
