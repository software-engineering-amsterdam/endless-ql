package nl.khonraad.qls.language;

public class StyleElement {

    DisplayValues displayValues;

    public StyleElement( String styledTrueString, String styledFalseString ) {
        displayValues = new DisplayValues( styledFalseString, styledTrueString );
    }

    public String styledTrueString() {
        return displayValues.trueString();
    }

    public String styledFalseString() {
        return displayValues.falseString();
    }
}
