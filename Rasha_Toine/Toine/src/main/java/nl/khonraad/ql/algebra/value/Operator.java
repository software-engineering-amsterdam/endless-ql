package nl.khonraad.ql.algebra.value;

import nl.khonraad.ql.algebra.Identifier;

public enum Operator {

    Multiply( "*" ),
    DividedBy( "/" ), 
    Plus( "+" ), 
    Minus( "-" ), 
    And( "&&" ), 
    Or( "||" ), 
    Less( "<" ), 
    NotMore( "<=" ),
    Equals( "==" ), 
    More( ">" ), 
    NotLess( ">=" ), 
    Not( "!" );

    private Identifier identifier;

    private Operator(String text) {
        this.identifier = new Identifier( text );
    }

    public static Operator parse( String text ) {

        for ( Operator type : values() ) {

            if ( type.identifier.string().equals( text ) ) {
                return type;
            }
        }

        throw new RuntimeException( "Check your grammar. Found no Operator from \"" + text + "\"" );
    }
}
