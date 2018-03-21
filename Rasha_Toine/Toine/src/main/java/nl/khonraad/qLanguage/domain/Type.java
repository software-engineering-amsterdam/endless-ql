package nl.khonraad.qLanguage.domain;

public enum Type {

    Boolean("boolean"), Date("date"), Integer("integer"), Money("money"), String("string");

    private String text;

    private Type(String text) {
        this.text = text;
    }

    public static Type parseType( String text ) {

        for ( Type type : Type.values() ) {

            if ( type.text.equalsIgnoreCase( text ) ) {
                return type;
            }
        }

        throw new RuntimeException( "Check your grammar. Do not know how to instantiate a Type from \"" + text + "\"" );
    }

}
