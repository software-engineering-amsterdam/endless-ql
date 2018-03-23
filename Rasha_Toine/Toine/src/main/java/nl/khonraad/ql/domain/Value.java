package nl.khonraad.ql.domain;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class Value {

    public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormat.forPattern( "dd/MM/yyyy" );
    public static final Value             FALSE              = new Value( Type.Boolean, "False" );
    public static final Value             TRUE               = new Value( Type.Boolean, "True" );

    private Type                          type;
    private String                        text;

    public Value(Type type, String string) {

        this.type = type;
        this.text = string;
    }

    public Value apply( String operator ) {

        switch ( operator ) {

            case "+":
                return this;

            case "-":
                return this.times( asValue( -1 ) );

            case "!":
                return not( this );

            default:
                throw new RuntimeException( "Grammar defines operator not implemented: " + operator );
        }
    }

    public Value apply( String operator, Value operand ) {

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
                throw new RuntimeException( "Grammar defines operator not implemented: " + operator );
        }
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    private Value not( Value value ) {
        return asValue( !TRUE.equals( value ) );
    }

    private Value less( Value operand ) {

        switch ( operation( "<", operand ) ) {

            case "Date < Date":

                return asValue( asDateTime( this ).isBefore( asDateTime( operand ) ) );

            case "Integer < Integer":

                return asValue( asInteger( this ) < asInteger( operand ) );

            case "Money < Money":

                return asValue( asBigDecimal( this ).compareTo( asBigDecimal( operand ) ) < 0 );

            case "String < String":

                return asValue( asString( this ).compareTo( asString( operand ) ) < 0 );

            default:

                throw new RuntimeException( "Operation on types not supported:" );
        }
    }

    private Value notMore( Value operand ) {

        switch ( operation( "<=", operand ) ) {

            case "Date <= Date":
                return asValue( (asDateTime( this ).isBefore( asDateTime( operand ) )
                        || (asDateTime( this ).equals( asDateTime( operand ) ))) );

            case "Integer <= Integer":
                return asValue( (asInteger( this ) <= asInteger( operand )) );

            case "Money <= Money":
                return asValue( asBigDecimal( this ).compareTo( asBigDecimal( operand ) ) > -1 );

            case "String <= String":
                return asValue( asString( this ).compareTo( asString( operand ) ) < 1 );

            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value is( Value operand ) {

        switch ( operation( "==", operand ) ) {

            case "Boolean == Boolean":
            case "Date == Date":
            case "Integer == Integer":
            case "Money == Money":
            case "String == String":
                return asValue( this.equals( operand ) );

            default:

                Type typeRight = operand.getType();
                Type typeLeft = this.getType();

                String textLeft = this.getText();
                String textRight = operand.getText();

                throw new RuntimeException( "Check Antlr grammar. Operation impossible " + typeRight + " " + typeLeft
                        + " " + textLeft + " " + textRight + " " );
        }
    }

    private Value more( Value operand ) {

        switch ( operation( ">", operand ) ) {

            case "Date > Date":
                return asValue( asDateTime( this ).isAfter( asDateTime( operand ) ) );

            case "Integer > Integer":
                return asValue( (asInteger( this ) > asInteger( operand )) );

            case "Money > Money":
                return asValue( asBigDecimal( this ).compareTo( asBigDecimal( operand ) ) > 0 );

            case "String > String":
                return asValue( asString( this ).compareTo( asString( operand ) ) > 0 );
            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value notLess( Value operand ) {

        switch ( operation( ">=", operand ) ) {

            case "Date >= Date":
                return asValue( (asDateTime( this ).isAfter( asDateTime( operand ) )
                        || (asDateTime( this ).equals( asDateTime( operand ) ))) );

            case "Integer >= Integer":
                return asValue( (asInteger( this ) >= asInteger( operand )) );

            case "Money >= Money":
                return asValue( asBigDecimal( this ).compareTo( asBigDecimal( operand ) ) > -1 );

            case "String >= String":
                return asValue( asString( this ).compareTo( asString( operand ) ) > -1 );
            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value and( Value operand ) {

        switch ( operation( "&&", operand ) ) {

            case "Boolean && Boolean":
                return asValue( asBoolean( this ) && asBoolean( operand ) );
            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value or( Value operand ) {

        switch ( operation( "||", operand ) ) {

            case "Boolean || Boolean":
                return asValue( asBoolean( this ) || asBoolean( operand ) );

            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value times( Value operand ) {

        switch ( operation( "*", operand ) ) {

            case "Integer * Integer":
                return asValue( asInteger( this ) * asInteger( operand ) );

            case "Integer * Money":
                return asValue( asBigDecimal( operand ).multiply( asBigDecimal( this ) ) );

            case "Money * Integer":
                return asValue( asBigDecimal( this ).multiply( asBigDecimal( operand ) ) );

            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value subtract( Value operand ) {

        switch ( operation( "/", operand ) ) {

            case "Integer / Integer":
                return asValue( (asInteger( this ) / asInteger( operand )) );

            case "Money / Integer":
                return asValue( (asBigDecimal( this ).divide( new BigDecimal( asInteger( operand ) ) )) );

            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value plus( Value operand ) {

        String textLeft = this.getText();
        String textRight = operand.getText();

        switch ( operation( "+", operand ) ) {

            case "Date + Integer":
                int days = asInteger( operand );

                return asValue( asDateTime( this ).plusDays( days ) );

            case "Integer + Integer":
                return asValue( asInteger( this ) + asInteger( operand ) );

            case "Money + Money":
                return asValue( asBigDecimal( this ).add( asBigDecimal( operand ) ) );

            case "String + Integer":
            case "String + Money":
            case "String + String":
                return asValue( textLeft + textRight );

            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private Value minus( Value operand ) {

        switch ( operation( "-", operand ) ) {

            case "Date - Integer":
                int days = asInteger( operand );

                return asValue( asDateTime( this ).minusDays( days ) );

            case "Integer - Integer":
                return asValue( asInteger( this ) - asInteger( operand ) );

            case "Money - Money":
                return asValue( asBigDecimal( this ).subtract( asBigDecimal( operand ) ) );
            default:
                throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
        }
    }

    private boolean asBoolean( Value right ) {
        return Value.TRUE.equals( right );
    }

    private DateTime asDateTime( Value value ) {

        return new DateTime( SIMPLE_DATE_FORMAT.parseDateTime( value.getText() ) );
    }

    private static Integer asInteger( Value value ) {
        return Integer.parseInt( value.text );
    }

    private static BigDecimal asBigDecimal( Value value ) {
        return new BigDecimal( value.text );
    }

    private static String asString( Value value ) {
        return value.text;
    }

    public static Value asValue( Boolean b ) {
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