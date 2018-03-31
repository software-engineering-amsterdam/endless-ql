package nl.khonraad.ql.algebra.function;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;

public class BinaryFunctions {

    private static Map<BinaryFunction, BiFunction<Value, Value, Value>> map = new HashMap<>();

    static {
        
        map.put( BinaryFunction.BooleanAndBoolean, ( a, b ) -> new Value( isTrue( a ) && isTrue( b ) ) );
        map.put( BinaryFunction.BooleanEqualsBoolean, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinaryFunction.BooleanOrBoolean, ( a, b ) -> new Value( isTrue( a ) || isTrue( b ) ) );

        map.put( BinaryFunction.DatePlusInteger, ( a, b ) -> new Value( date( a ).plusDays( integer( b ) ) ) );
        map.put( BinaryFunction.DateMinusInteger, ( a, b ) -> new Value( date( a ).minusDays( integer( b ) ) ) );
        map.put( BinaryFunction.DateLessDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) < 0 ) );
        map.put( BinaryFunction.DateMoreDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) < 1 ) );
        map.put( BinaryFunction.DateEqualsDate, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinaryFunction.DateMoreDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) > 0 ) );
        map.put( BinaryFunction.DateNotLessDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) > -1 ) );

        map.put( BinaryFunction.IntegerTimesInteger, ( a, b ) -> new Value( (integer( a ) * integer( b )) ) );
        map.put( BinaryFunction.IntegerTimesMoney, ( a, b ) -> new Value( money( a ).multiply( money( b ) ) ) );
        map.put( BinaryFunction.IntegerPlusInteger, ( a, b ) -> new Value( (integer( a ) + integer( b )) ) );
        map.put( BinaryFunction.IntegerMinusInteger, ( a, b ) -> new Value( (integer( a ) - integer( b )) ) );
        map.put( BinaryFunction.IntegerDividedByInteger, ( a, b ) -> new Value( (integer( a ) / integer( b )) ) );
        map.put( BinaryFunction.IntegerLessInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) < 0 ) );
        map.put( BinaryFunction.IntegerNotMoreInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) < 1 ) );
        map.put( BinaryFunction.IntegerEqualsInteger, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinaryFunction.IntegerMoreInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) > 0 ) );
        map.put( BinaryFunction.IntegerNotLessInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) > -1 ) );

        map.put( BinaryFunction.MoneyTimesInteger, ( a, b ) -> new Value( money( a ).multiply( money( b ) ) ) );
        map.put( BinaryFunction.MoneyPlusMoney, ( a, b ) -> new Value( money( a ).add( money( b ) ) ) );
        map.put( BinaryFunction.MoneyMinusMoney, ( a, b ) -> new Value( money( a ).subtract( money( b ) ) ) );
        map.put( BinaryFunction.MoneyDividedByInteger, ( a, b ) -> new Value( money( a ).divide( money( b ) ) ) );
        map.put( BinaryFunction.MoneyLessMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) < 0 ) );
        map.put( BinaryFunction.MoneyNotMoreMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) < 1 ) );
        map.put( BinaryFunction.MoneyEqualsMoney, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinaryFunction.MoneyMoreMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) > 0 ) );
        map.put( BinaryFunction.MoneyNotLessMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) > -1 ) );

        map.put( BinaryFunction.StringPlusInteger, ( a, b ) -> new Value( string(a) + string(b) ) );
        map.put( BinaryFunction.StringPlusMoney, ( a, b ) -> new Value( string(a) + string(b) ) );
        map.put( BinaryFunction.StringPlusString, ( a, b ) -> new Value( string(a) + string(b) ) );
        map.put( BinaryFunction.StringLessString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) < 0 ) );
        map.put( BinaryFunction.StringNotMoreString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) < 1 ) );
        map.put( BinaryFunction.StringEqualsString, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinaryFunction.StringMoreString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) > 0 ) );
        map.put( BinaryFunction.StringNotLessString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) > -1 ) );

    }

    // consistent name 'boolean' is reserved by Java
    private static boolean isTrue( Value value ) {
        return new Boolean( value.string() );
    }

    private static DateTime date( Value value ) {
        return new DateTime( SimpleDateFormatter.parseDateTime( value.string() ) );
    }

    private static Integer integer( Value value ) {
        return Integer.parseInt( value.string() );
    }

    private static BigDecimal money( Value value ) {
        return new BigDecimal( value.string() );
    }

    private static String string( Value value ) {
        return value.string();
    }

    public static BiFunction<Value, Value, Value> function( BinaryFunction expression ) {
        return map.get( expression );
    }
}