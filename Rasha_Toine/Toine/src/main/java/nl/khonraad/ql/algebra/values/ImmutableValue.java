package nl.khonraad.ql.algebra.values;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.functions.BinaryFunctions;
import nl.khonraad.ql.algebra.functions.BinarySignature;
import nl.khonraad.ql.algebra.functions.UnaryFunctions;
import nl.khonraad.ql.algebra.functions.UnarySignature;
import nl.khonraad.ql.algebra.values.utility.SimpleDateFormatter;

final class ImmutableValue implements Value {

    private Type    type;
    private Storage storage;

    /*
     * Constructors
     */
    public ImmutableValue( Type type, String string ) {
        this.type = type;
        this.storage = new Storage( string );
    }

    protected ImmutableValue( boolean aBoolean )  { this( Type.Boolean, aBoolean ? "True" : "False" ); }
    protected ImmutableValue( DateTime dateTime ) { this( Type.Date,    SimpleDateFormatter.string( dateTime ) ); }
    protected ImmutableValue( Integer integer )   { this( Type.Integer, Integer.toString( integer ) ); }
    protected ImmutableValue( BigDecimal money )  { this( Type.Money,   money.toString() ); }
    protected ImmutableValue( String string )     { this( Type.String,  string ); }
    
    /*
     * Methods
     */
    @Override public String string() { 
        return storage.string();  
    }
    
    @Override public Type type() {
        return type;
    }
    
    @Override public Value apply( Operator operator ) {
        return UnaryFunctions.function( UnarySignature.signature( operator, type() ) ).apply( this );
    }

    @Override public Value apply( Operator operator, Value other ) {
        BiFunction<Value, Value, Value> function = BinaryFunctions.function( BinarySignature.signature( this.type(), operator, other.type() ) );
        return function.apply( this, other );
    }
    
    @Override public int hashCode() {
        return Objects.hash( this.storage, this.type );
    }

    @Override public boolean equals( Object object ) {
        if ( object == null || getClass() != object.getClass() )
            return false;
        final ImmutableValue other = (ImmutableValue) object;
        return Objects.equals( this.storage, other.storage ) && Objects.equals( this.type, other.type );
    }
}