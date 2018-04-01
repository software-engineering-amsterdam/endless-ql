package nl.khonraad.qls.ast.data;

import nl.khonraad.ql.algebra.value.Type;

public class StyleElement {

    Type   type;
    String forTrue;
    String forFalse;

    public StyleElement( Type type, String forTrue, String forFalse ) {

        this.type = type;
        this.forTrue = forTrue;
        this.forFalse = forFalse;

    }

    public String[] displayValues() {

        return new String[] { forFalse, forTrue };
    }
}
