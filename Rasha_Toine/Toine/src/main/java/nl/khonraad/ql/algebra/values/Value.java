package nl.khonraad.ql.algebra.values;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;

public interface Value {

    public static final Value True  = Value.of( true );
    public static final Value Unit  = new ImmutableValue( null, null );
    public static final Value False = Value.of( false );

    String string();

    Type type();

    Value apply( Operator operator );

    Value apply( Operator operator, Value other );

    static Value typed( Type type, String string ) {
        return new ImmutableValue( type, string );
    }

    static Value of( boolean b ) {
        return new ImmutableValue( b );
    }

    static Value of( DateTime m ) {
        return new ImmutableValue( Type.Date, SimpleDateFormatter.string( m ) );
    }

    static Value of( Integer i ) {
        return new ImmutableValue( Type.Integer, Integer.toString( i ) );
    }

    static Value of( BigDecimal m ) {
        return new ImmutableValue( Type.Money, m.toString() );
    }

    static Value of( String s ) {
        return new ImmutableValue( Type.String, s );
    }

}