package classes.values;

import classes.CodeBlock;

public abstract class Value <T extends Object>{
    public static final String BOOLEAN = "boolean",
            DECIMAL = "decimal",
            INTEGER = "integer",
            DATE = "date",
            STRING = "string",
            MONEY = "money";

    private T value;
    private String type;

    Value(T value){
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    //TODO: put setType in constructor??
    public void setType(String type){
        this.type = type;
    }

    public void setValue(T value){
        this.value = value;
    }

    public String getType(){
        return type;
    }

    public abstract void setValueGeneric(Object o);
}
