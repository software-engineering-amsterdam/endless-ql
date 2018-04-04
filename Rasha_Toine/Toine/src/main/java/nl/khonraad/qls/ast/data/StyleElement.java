package nl.khonraad.qls.ast.data;

import nl.khonraad.ql.algebra.values.Type;

public class StyleElement {

    Type          type;
    DisplayValues displayValues;

    public StyleElement( Type type, String falseString, String trueString ) {

        this.type = type;
        displayValues = new DisplayValues( falseString, trueString );
    }

    public String trueString() {
        return displayValues.trueString();
    }

    public String falseString() {
        return displayValues.falseString();
    }

}
