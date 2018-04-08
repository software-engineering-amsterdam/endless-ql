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

    private Type         type;
    private MutableValue mutableValue;

    /*
     * Constructors
     */
    public ImmutableValue( Type type, String string ) {
        this.type = type;
        this.mutableValue = new MutableValue( string );
    }

    ImmutableValue( boolean aBoolean )  { this( Type.Boolean, aBoolean ? "True" : "False" ); }
    ImmutableValue( DateTime dateTime ) { this( Type.Date,    SimpleDateFormatter.string( dateTime ) ); }
    ImmutableValue( Integer integer )   { this( Type.Integer, Integer.toString( integer ) ); }
    ImmutableValue( BigDecimal money )  { this( Type.Money,   money.toString() ); }
    ImmutableValue( String string )     { this( Type.String,  string ); }
    
    /*
     * Methods
     */
    @Override public String string() { 
        return mutableValue.string();  
    }
    
    @Override public Value apply( Operator operator ) {
        return UnaryFunctions.function( UnarySignature.signature( operator, type() ) ).apply( this );
    }

    @Override public Value apply( Operator operator, Value other ) {
        BiFunction<Value, Value, Value> function = BinaryFunctions.function( BinarySignature.signature( this.type(), operator, other.type() ) );
        return function.apply( this, other );
    }

    @Override public Type type() {
        return type;
    }
    
    @Override public int hashCode() {
        return Objects.hash( this.mutableValue, this.type );
    }

    @Override public boolean equals( Object object ) {
        if ( object == null || getClass() != object.getClass() )
            return false;
        final ImmutableValue other = (ImmutableValue) object;
        return Objects.equals( this.mutableValue, other.mutableValue ) && Objects.equals( this.type, other.type );
    }
}