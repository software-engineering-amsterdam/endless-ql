package nl.khonraad.ql.algebra.values;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.values.utility.SimpleDateFormatter;

public interface Value {

    public static final Value  Unit        = new ImmutableValue( null, null );
    public static final Value  True        = Value.of( true );
    public static final Value  False       = Value.of( false );
    public static final String TrueString  = True.string();
    public static final String FalseString = False.string();

    /*
     * Functions
     */
    public String   string();
    public Type     type();

    public Value    apply( Operator operator );
    public Value    apply( Operator operator, Value other );

    /*
     * Construction  
     */
    public static Value of( Type type, String string ) { return new ImmutableValue( type, string );}

    /*
     * Type adapters
     */
    public static Value of( boolean b )     { return new ImmutableValue( b ); }
    public static Value of( DateTime m )    { return new ImmutableValue( Type.Date, SimpleDateFormatter.string( m ) ); }
    public static Value of( Integer i )     { return new ImmutableValue( Type.Integer, Integer.toString( i ) ); }
    public static Value of( BigDecimal m )  { return new ImmutableValue( Type.Money, m.toString() ); }
    public static Value of( String s )      { return new ImmutableValue( Type.String, s ); }
}