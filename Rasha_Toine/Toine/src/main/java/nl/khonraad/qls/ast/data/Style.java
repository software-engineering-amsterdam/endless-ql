package nl.khonraad.qls.ast.data;

import nl.khonraad.ql.algebra.value.Type;

public class Style {

    Type   type;
    String t;
    String f;

    public Style( Type type, String t, String f ) {

        this.type = type;
        this.t = t;
        this.f = f;

    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the t
     */
    public String getT() {
        return t;
    }

    /**
     * @return the f
     */
    public String getF() {
        return f;
    }
}
