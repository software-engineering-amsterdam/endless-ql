package nl.khonraad.ql.algebra.values;

public enum Operator {

    Multiply  (  "*" ),
    DividedBy (  "/" ),
    Plus      (  "+" ), 
    Minus     (  "-" ), 
    And       ( "&&" ), 
    Or        ( "||" ), 
    Less      (  "<" ), 
    NotMore   ( "<=" ),
    Equals    ( "==" ), 
    More      (  ">" ), 
    NotLess   ( ">=" ), 
    Not       (  "!" );

    private String terminalSymbol;

    private Operator(String terminalSymbol) {
        this.terminalSymbol = terminalSymbol;
    }

    public static Operator parse( String terminalSymbol ) {

        for ( Operator operator : values() ) {

            if ( operator.terminalSymbol.equals( terminalSymbol ) ) {
                return operator;
            }
        }

        throw new RuntimeException( "Check your grammar. Found no Operator from \"" + terminalSymbol + "\"" );
    }
}
