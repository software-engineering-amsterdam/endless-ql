package nl.khonraad.qls.ast.data;

import nl.khonraad.ql.algebra.values.Type;

public class StyleElement {

    Type          type;
    DisplayValues displayValues;

    public StyleElement( Type type, String styledTrueString, String styledFalseString ) {

        this.type = type;
        displayValues = new DisplayValues( styledFalseString, styledTrueString );
    }

    public String styledTrueString() {
        return displayValues.trueString();
    }

    public String styledFalseString() {
        return displayValues.falseString();
    }

}
