package QL.classes.values;

import QL.classes.CodeBlock;

import java.util.Observable;

public abstract class Value <T extends Object> extends Observable{
    public static final String BOOLEAN = "boolean",
            DECIMAL = "decimal",
            INTEGER = "integer",
            DATE = "date",
            STRING = "string",
            MONEY = "money",
            UNDEFINED = "undefined";

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

        if(countObservers() > 0){
            setChanged();
            notifyObservers();
        }
    }

    public String getType(){
        return type;
    }

    public abstract void setValueGeneric(Object o);

    public boolean isDefined () {
        return true;
    }
}
