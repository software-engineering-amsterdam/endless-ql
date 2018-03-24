package nl.khonraad.ql.algebra;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Value {

    private static Exception              unaryOperationNotSupported = new Exception( "Unsupported unary operation" );

    public static final DateTimeFormatter SIMPLE_DATE_FORMAT         = DateTimeFormat.forPattern( "dd/MM/yyyy" );

    public static final Value             FALSE                      = new Value( false );
    public static final Value             TRUE                       = new Value( true );

    private Type                          type;
    private String                        text;

    public Value(boolean b) {
        this( Type.Boolean, b ? "True" : "False" );
    }

    public Value(DateTime m) {
        this( Type.Date, SIMPLE_DATE_FORMAT.print( m ) );
    }

    public Value(Integer i) {
        this( Type.Integer, Integer.toString( i ) );
    }

    public Value(BigDecimal m) {
        this( Type.Money, m.toString() );
    }

    public Value(String s) {
        this( Type.String, s );
    }

    public Value(Type type, String string) {

        this.type = type;
        this.text = string;
    }

    public Value apply( String operator ) throws Exception {

        switch ( operator ) {

            case "+":
                return this;

            case "-":
                return new PartialFunction( this, "*" ).applyOperand( new Value( -1 ) );

            case "!":
                return (this.equals( TRUE )) ? FALSE : TRUE;

            default:
                throw unaryOperationNotSupported;
        }

    }

    public Value apply( String operator, Value operand ) throws Exception {

        return new PartialFunction( this, operator ).applyOperand( operand );

    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Value other = (Value) obj;
        if ( text == null ) {
            if ( other.text != null ) return false;
        } else
            if ( !text.equals( other.text ) ) return false;
        if ( type != other.type ) return false;
        return true;
    }
}