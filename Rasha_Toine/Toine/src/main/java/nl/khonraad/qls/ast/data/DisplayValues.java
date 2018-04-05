package nl.khonraad.qls.ast.data;

public class DisplayValues {

    private String falseString;
    private String trueString;

    DisplayValues( String falseString, String trueString ) {

        this.falseString = falseString;
        this.trueString = trueString;
    }

    String falseString() {
        return falseString;
    }

    String trueString() {
        return trueString;
    }

}
