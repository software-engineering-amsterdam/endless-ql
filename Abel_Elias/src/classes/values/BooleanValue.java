package classes.types;

import classes.CodeBlock;

public class BooleanValue extends Value<Boolean> {
    BooleanValue(Boolean value, String type) {
        super(value);
        setType(Value.BOOLEAN);
    }
}
