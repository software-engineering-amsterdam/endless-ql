package nl.khonraad.ql.algebra;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public final class PartialFunction {

    private static Exception binaryOperationNotSupported = new Exception( "Unsupported binary operation" );

    private Value            value;
    private String           operator;

    public PartialFunction(Value value, String operator) {

        this.value = value;
        this.operator = operator;
    }

    public Value applyOperand( Value right ) throws Exception {

        switch ( this.operator ) {

            case "*":
                return times( right );

            case "/":
                return subtract( right );

            case "+":
                return plus( right );

            case "-":
                return minus( right );

            case "&&":
                return and( right );

            case "||":
                return or( right );

            case "==":
                return is( right );

            case "<=":
                return notMore( right );

            case ">=":
                return notLess( right );

            case "<":
                return less( right );

            case ">":
                return more( right );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value less( Value that ) throws Exception {

        switch ( operation( "<", that ) ) {

            case "Date < Date":
                return new Value( dateTimeFrom( this.value ).isBefore( dateTimeFrom( that ) ) );

            case "Integer < Integer":
                return new Value( integerFrom( this.value ) < integerFrom( that ) );

            case "Money < Money":
                return new Value( bigDecimalFrom( this.value ).compareTo( bigDecimalFrom( that ) ) < 0 );

            case "String < String":
                return new Value( stringFrom( this.value ).compareTo( stringFrom( that ) ) < 0 );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value notMore( Value that ) throws Exception {

        switch ( operation( "<=", that ) ) {

            case "Date <= Date":
                return new Value( (dateTimeFrom( this.value ).isBefore( dateTimeFrom( that ) )
                        || (dateTimeFrom( this.value ).equals( dateTimeFrom( that ) ))) );

            case "Integer <= Integer":
                return new Value( (integerFrom( this.value ) <= integerFrom( that )) );

            case "Money <= Money":
                return new Value( bigDecimalFrom( this.value ).compareTo( bigDecimalFrom( that ) ) > -1 );

            case "String <= String":
                return new Value( stringFrom( this.value ).compareTo( stringFrom( that ) ) < 1 );

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
                return new Value( this.value.equals( that ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value more( Value that ) throws Exception {

        switch ( operation( ">", that ) ) {

            case "Date > Date":
                return new Value( dateTimeFrom( this.value ).isAfter( dateTimeFrom( that ) ) );

            case "Integer > Integer":
                return new Value( (integerFrom( this.value ) > integerFrom( that )) );

            case "Money > Money":
                return new Value( bigDecimalFrom( this.value ).compareTo( bigDecimalFrom( that ) ) > 0 );

            case "String > String":
                return new Value( stringFrom( this.value ).compareTo( stringFrom( that ) ) > 0 );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value notLess( Value that ) throws Exception {

        switch ( operation( ">=", that ) ) {

            case "Date >= Date":
                return new Value( (dateTimeFrom( this.value ).isAfter( dateTimeFrom( that ) )
                        || (dateTimeFrom( this.value ).equals( dateTimeFrom( that ) ))) );

            case "Integer >= Integer":
                return new Value( (integerFrom( this.value ) >= integerFrom( that )) );

            case "Money >= Money":
                return new Value( bigDecimalFrom( this.value ).compareTo( bigDecimalFrom( that ) ) > -1 );

            case "String >= String":
                return new Value( stringFrom( this.value ).compareTo( stringFrom( that ) ) > -1 );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value and( Value that ) throws Exception {

        /*
         * Could be implemented with an if statement, though this.value
         * follows the style of the'richer' cases like 'plus'.
         */

        switch ( operation( "&&", that ) ) {

            case "Boolean && Boolean":
                return new Value( booleanFrom( this.value ) && booleanFrom( that ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value or( Value that ) throws Exception {

        /*
         * Could be implemented with an if statement, though this.value
         * follows the style of the'richer' cases like 'plus'.
         */
        switch ( operation( "||", that ) ) {

            case "Boolean || Boolean":
                return new Value( booleanFrom( this.value ) || booleanFrom( that ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value times( Value that ) throws Exception {

        switch ( operation( "*", that ) ) {

            case "Integer * Integer":
                return new Value( integerFrom( this.value ) * integerFrom( that ) );

            case "Integer * Money":
                return new Value( bigDecimalFrom( that ).multiply( bigDecimalFrom( this.value ) ) );

            case "Money * Integer":
                return new Value( bigDecimalFrom( this.value ).multiply( bigDecimalFrom( that ) ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value subtract( Value that ) throws Exception {

        switch ( operation( "/", that ) ) {

            case "Integer / Integer":
                return new Value( (integerFrom( this.value ) / integerFrom( that )) );

            case "Money / Integer":
                return new Value( (bigDecimalFrom( this.value ).divide( new BigDecimal( integerFrom( that ) ) )) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value plus( Value that ) throws Exception {

        switch ( operation( "+", that ) ) {

            case "Date + Integer":
                int days = integerFrom( that );

                return new Value( dateTimeFrom( this.value ).plusDays( days ) );

            case "Integer + Integer":
                return new Value( integerFrom( this.value ) + integerFrom( that ) );

            case "Money + Money":
                return new Value( bigDecimalFrom( this.value ).add( bigDecimalFrom( that ) ) );

            case "String + Integer":
            case "String + Money":
            case "String + String":
                return new Value( this.value.getText() + that.getText() );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private Value minus( Value that ) throws Exception {

        switch ( operation( "-", that ) ) {

            case "Date - Integer":
                int days = integerFrom( that );
                return new Value( dateTimeFrom( this.value ).minusDays( days ) );

            case "Integer - Integer":
                return new Value( integerFrom( this.value ) - integerFrom( that ) );

            case "Money - Money":
                return new Value( bigDecimalFrom( this.value ).subtract( bigDecimalFrom( that ) ) );

            default:
                throw binaryOperationNotSupported;
        }
    }

    private boolean booleanFrom( Value right ) {
        return Value.TRUE.equals( right );
    }

    private DateTime dateTimeFrom( Value value ) {

        return new DateTime( Value.SIMPLE_DATE_FORMAT.parseDateTime( value.getText() ) );
    }

    private static Integer integerFrom( Value value ) {
        return Integer.parseInt( value.getText() );
    }

    private static BigDecimal bigDecimalFrom( Value value ) {
        return new BigDecimal( value.getText() );
    }

    private static String stringFrom( Value value ) {
        return value.getText();
    }

    // TELL DONT ASK
    private String operation( String operator, Value operand ) {
        return this.value.getType() + " " + operator + " " + operand.getType();
    }

}