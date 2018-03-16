package classes.values;

import classes.CodeBlock;

public abstract class Value <T extends Object>{
<<<<<<< HEAD
    public static final String BOOLEAN = "boolean",
            DECIMAL = "decimal",
            INTEGER = "integer",
            DATE = "date",
            STRING = "string",
            MONEY = "money";
=======
    public static final String BOOLEAN = "BOOLEAN",
            DECIMAL = "DECIMAL",
            INTEGER = "INTEGER",
            DATE = "DATE",
            STRING = "STRING",
            MONEY = "MONEY";
>>>>>>> bdeb7b6ce55ff3bf5c230a0742ab4d5fbe008b58

    private T value;
    private String type;

    Value(T value){
        this.value = value;
    }

    public T getValue(){
        return value;
    }

<<<<<<< HEAD
    //TODO: put setType in constructor??
=======
>>>>>>> bdeb7b6ce55ff3bf5c230a0742ab4d5fbe008b58
    public void setType(String type){
        this.type = type;
    }

<<<<<<< HEAD
    public void setValue(T value){
        this.value = value;
    }

=======
>>>>>>> bdeb7b6ce55ff3bf5c230a0742ab4d5fbe008b58
    public String getType(){
        return type;
    }

    public abstract void setValueGeneric(Object o);
}
