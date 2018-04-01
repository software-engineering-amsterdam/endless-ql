package nl.khonraad.ql.algebra;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;
import nl.khonraad.ql.algebra.function.BinarySignature;
import nl.khonraad.ql.algebra.function.BinaryFunctions;
import nl.khonraad.ql.algebra.function.UnarySignature;
import nl.khonraad.ql.algebra.function.UnaryFunctions;
import nl.khonraad.ql.algebra.value.Operator;
import nl.khonraad.ql.algebra.value.Storage;
import nl.khonraad.ql.algebra.value.Type;

public class Value implements StringAble {

    private Type    type;
    private Storage storage;

    @Override
    public String string() {
        return storage.string();
    }

    public Value( Type type, String string ) {

        this.type = type;
        this.storage = new Storage( string );
    }

    public Value( boolean b ) {
        this( Type.Boolean, b ? "True" : "False" );
    }

    public Value( DateTime m ) {
        this( Type.Date, SimpleDateFormatter.string( m ) );
    }

    public Value( Integer i ) {
        this( Type.Integer, Integer.toString( i ) );
    }

    public Value( BigDecimal m ) {
        this( Type.Money, m.toString() );
    }

    public Value( String s ) {
        this( Type.String, s );
    }

    public Value apply( Operator operator ) {

        return UnaryFunctions.function( UnarySignature.signature( operator, type() ) ).apply( this );
    }

    public Value apply( Operator operator, Value other ) {
        
        BiFunction<Value, Value, Value> function = BinaryFunctions.function( BinarySignature.signature( this.type(), operator, other.type() ) );
        
        return function.apply( this, other );

    }

    public Type type() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.storage, this.type );
    }

    @Override
    public boolean equals( Object object ) {

        if ( object == null || getClass() != object.getClass() )
            return false;

        final Value other = (Value) object;

        return Objects.equals( this.storage, other.storage ) && Objects.equals( this.type, other.type );
    }
}