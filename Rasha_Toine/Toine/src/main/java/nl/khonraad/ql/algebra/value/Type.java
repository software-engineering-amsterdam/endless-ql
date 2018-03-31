package nl.khonraad.ql.algebra.value;

/**
 * Map the QL keywords to enumeration type
 * Avoid clash to Java keywords
 */
public enum Type {

    Boolean("boolean"), Date("date"), Integer("integer"), Money("money"), String("string");

    private String qlName;

    private Type(String qlName) {

        this.qlName = qlName;
    }
    
    public static Type type( String qlName ) {

        for ( Type type : values() ) {

            if ( type.qlName.equals( qlName ) ) {

                return type;
            }
        }

        throw new RuntimeException( "Check your grammar. Do not know how to instantiate a Type from \"" + qlName + "\"" );
    }
}
