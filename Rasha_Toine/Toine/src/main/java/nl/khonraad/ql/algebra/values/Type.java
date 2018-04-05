package nl.khonraad.ql.algebra.values;

/**
 * Map the QL keywords to enumeration type
 * Avoid clash to Java keywords
 */
public enum Type {

    Boolean("boolean"), Date("date"), Integer("integer"), Money("money"), String("string");

    private String terminalSymbol;

    private Type( String terminalSymbol ) {

        this.terminalSymbol = terminalSymbol;
    }

    public String string() {
        return terminalSymbol;
    }

    public static Type type( String terminalSymbol ) {

        for ( Type type : values() ) {

            if ( type.terminalSymbol.equals( terminalSymbol ) ) {

                return type;
            }
        }

        throw new RuntimeException( "Check your grammar. Do not know how to instantiate a Type from \"" + terminalSymbol + "\"" );
    }
}
