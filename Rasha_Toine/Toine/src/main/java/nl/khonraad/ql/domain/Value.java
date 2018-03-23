package nl.khonraad.ql.domain;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class Value {

    private static Exception              binaryOperationNotSupported = new Exception( "Unsupported binary operation" );
    private static Exception              unaryOperationNotSupported  = new Exception( "Unsupported unary operation" );

    public static final DateTimeFormatter SIMPLE_DATE_FORMAT          = DateTimeFormat.forPattern( "dd/MM/yyyy" );

    public static final Value             FALSE                       = new Value( Type.Boolean, "False" );
    public static final Value             TRUE                        = new Value( Type.Boolean, "True" );

    private Type                          type;
    private String                        text;

    public Value(Type type, String string) {

        this.type = type;
        this.text = string;
    }

    public Value apply( String operator ) throws Exception {

        switch ( operator ) {

            case "+":
                return this;

            case "-":
                return times( asValue( -1 ) );

            case "!":
                return not( this );

            default:
                throw unaryOperationNotSupported;
        }

    }

    public Value apply( String operator, Value operand ) throws Exception {

        switch ( operator ) {

            case "*":
                return times( operand );

            case "/":
                return subtract( operand );

            case "+":
                return plus( operand );

            case "-":
                return minus( operand );

            case "&&":
                return and( operand );

            case "||":
                return or( operand );

            case "==":
                return is( operand );

            case "<=":
                return notMore( operand );

            case ">=":
                return notLess( operand );

            case "<":
                return less( operand );

            case ">":
                return more( operand );

            default:
                throw binaryOperationNotSupported;
        }
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    private Value not( Value value ) {
        return valueOf( !TRUE.equals( value ) );
    }

    private Value less( Value that ) throws Exception {

        switch ( operation( "<", that ) ) {

            case "Date < Date":
                return valueOf( dateTimeFrom( this ).isBefore( dateTimeFrom( that ) ) );

            case "Integer < Integer":
                return valueOf( integerFrom( this ) < integerFrom( that ) );

            case "Money < Money":
                return valueOf( bigDecimalFrom( this ).compareTo( bigDecimalFrom( that ) ) < 0 );

            case "String < String":
                return valueOf( stringFrom( this ).compareTo( stringFrom( that ) ) < 0 );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value notMore( Value that ) throws Exception {

        switch ( operation( "<=", that ) ) {

            case "Date <= Date":
                return valueOf( (dateTimeFrom( this ).isBefore( dateTimeFrom( that ) )
                        || (dateTimeFrom( this ).equals( dateTimeFrom( that ) ))) );

            case "Integer <= Integer":
                return valueOf( (integerFrom( this ) <= integerFrom( that )) );

            case "Money <= Money":
                return valueOf( bigDecimalFrom( this ).compareTo( bigDecimalFrom( that ) ) > -1 );

            case "String <= String":
                return valueOf( stringFrom( this ).compareTo( stringFrom( that ) ) < 1 );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value is( Value that ) throws Exception {

        switch ( operation( "==", that ) ) {

            case "Boolean == Boolean":
            case "Date == Date":
            case "Integer == Integer":
            case "Money == Money":
            case "String == String":
                return valueOf( this.equals( that ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value more( Value that ) throws Exception {

        switch ( operation( ">", that ) ) {

            case "Date > Date":
                return valueOf( dateTimeFrom( this ).isAfter( dateTimeFrom( that ) ) );

            case "Integer > Integer":
                return valueOf( (integerFrom( this ) > integerFrom( that )) );

            case "Money > Money":
                return valueOf( bigDecimalFrom( this ).compareTo( bigDecimalFrom( that ) ) > 0 );

            case "String > String":
                return valueOf( stringFrom( this ).compareTo( stringFrom( that ) ) > 0 );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value notLess( Value that ) throws Exception {

        switch ( operation( ">=", that ) ) {

            case "Date >= Date":
                return valueOf( (dateTimeFrom( this ).isAfter( dateTimeFrom( that ) )
                        || (dateTimeFrom( this ).equals( dateTimeFrom( that ) ))) );

            case "Integer >= Integer":
                return valueOf( (integerFrom( this ) >= integerFrom( that )) );

            case "Money >= Money":
                return valueOf( bigDecimalFrom( this ).compareTo( bigDecimalFrom( that ) ) > -1 );

            case "String >= String":
                return valueOf( stringFrom( this ).compareTo( stringFrom( that ) ) > -1 );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value and( Value that ) throws Exception {

        /*
         * Could be implemented with an if statement, though this 
         * follows the style of the'richer' cases like 'plus'.
         */

        switch ( operation( "&&", that ) ) {

            case "Boolean && Boolean":
                return valueOf( asBoolean( this ) && asBoolean( that ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value or( Value that ) throws Exception {

        /*
         * Could be implemented with an if statement, though this 
         * follows the style of the'richer' cases like 'plus'.
         */
        switch ( operation( "||", that ) ) {

            case "Boolean || Boolean":
                return valueOf( asBoolean( this ) || asBoolean( that ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value times( Value that ) throws Exception {

        switch ( operation( "*", that ) ) {

            case "Integer * Integer":
                return asValue( integerFrom( this ) * integerFrom( that ) );

            case "Integer * Money":
                return asValue( bigDecimalFrom( that ).multiply( bigDecimalFrom( this ) ) );

            case "Money * Integer":
                return asValue( bigDecimalFrom( this ).multiply( bigDecimalFrom( that ) ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value subtract( Value that ) throws Exception {

        switch ( operation( "/", that ) ) {

            case "Integer / Integer":
                return asValue( (integerFrom( this ) / integerFrom( that )) );

            case "Money / Integer":
                return asValue( (bigDecimalFrom( this ).divide( new BigDecimal( integerFrom( that ) ) )) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value plus( Value that ) throws Exception {

        switch ( operation( "+", that ) ) {

            case "Date + Integer":
                int days = integerFrom( that );

                return asValue( dateTimeFrom( this ).plusDays( days ) );

            case "Integer + Integer":
                return asValue( integerFrom( this ) + integerFrom( that ) );

            case "Money + Money":
                return asValue( bigDecimalFrom( this ).add( bigDecimalFrom( that ) ) );

            case "String + Integer":
            case "String + Money":
            case "String + String":
                return asValue( this.text + that.text);

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value minus( Value that ) throws Exception {

        switch ( operation( "-", that ) ) {

            case "Date - Integer":
                int days = integerFrom( that );
                return asValue( dateTimeFrom( this ).minusDays( days ) );

            case "Integer - Integer":
                return asValue( integerFrom( this ) - integerFrom( that ) );

            case "Money - Money":
                return asValue( bigDecimalFrom( this ).subtract( bigDecimalFrom( that ) ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private boolean asBoolean( Value right ) {
        return Value.TRUE.equals( right );
    }

    private DateTime dateTimeFrom( Value value ) {

        return new DateTime( SIMPLE_DATE_FORMAT.parseDateTime( value.getText() ) );
    }

    private static Integer integerFrom( Value value ) {
        return Integer.parseInt( value.text );
    }

    private static BigDecimal bigDecimalFrom( Value value ) {
        return new BigDecimal( value.text );
    }

    private static String stringFrom( Value value ) {
        return value.text;
    }

    public static Value valueOf( Boolean b ) {
        return b ? Value.TRUE : Value.FALSE;
    }

    public static Value asValue( DateTime m ) {
        return new Value( Type.Date, Value.SIMPLE_DATE_FORMAT.print( m ) );
    }

    public static Value asValue( BigDecimal m ) {
        return new Value( Type.Money, m.toString() );
    }

    public static Value asValue( Integer i ) {
        return new Value( Type.Integer, Integer.toString( i ) );
    }

    public static Value asValue( String s ) {
        return new Value( Type.String, s );
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

    private String operation( String operator, Value operand ) {
        return this.getType() + " " + operator + " " + operand.getType();
    }

}