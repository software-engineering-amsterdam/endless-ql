package nl.khonraad.ql.algebra.values;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;
import nl.khonraad.ql.algebra.functions.BinaryFunctions;
import nl.khonraad.ql.algebra.functions.BinarySignature;
import nl.khonraad.ql.algebra.functions.UnaryFunctions;
import nl.khonraad.ql.algebra.functions.UnarySignature;

final class ImmutableValue implements Value {

    private Type              type;
    private MutableValue      mutableValue;

    @Override
    public String string() {

        return mutableValue.string();
    }

    public ImmutableValue( Type type, String string ) {

        this.type = type;
        this.mutableValue = new MutableValue( string );
    }

    ImmutableValue( boolean b ) {
        this( Type.Boolean, b ? "True" : "False" );
    }

    public ImmutableValue( DateTime m ) {
        this( Type.Date, SimpleDateFormatter.string( m ) );
    }

    public ImmutableValue( Integer i ) {
        this( Type.Integer, Integer.toString( i ) );
    }

    public ImmutableValue( BigDecimal m ) {
        this( Type.Money, m.toString() );
    }

    public ImmutableValue( String s ) {
        this( Type.String, s );
    }

    @Override
    public Value apply( Operator operator ) {

        return UnaryFunctions.function( UnarySignature.signature( operator, type() ) ).apply( this );
    }

    @Override
    public Value apply( Operator operator, Value other ) {

        BiFunction<Value, Value, Value> function = BinaryFunctions.function( BinarySignature.signature( this.type(), operator, other.type() ) );

        return function.apply( this, other );

    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.mutableValue, this.type );
    }

    @Override
    public boolean equals( Object object ) {

        if ( object == null || getClass() != object.getClass() )
            return false;

        final ImmutableValue other = (ImmutableValue) object;

        return Objects.equals( this.mutableValue, other.mutableValue ) && Objects.equals( this.type, other.type );
    }


}