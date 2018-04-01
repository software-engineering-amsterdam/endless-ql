package nl.khonraad.ql.algebra.function;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;

public class BinaryFunctions {

    private static Map<BinarySignature, BiFunction<Value, Value, Value>> map = new HashMap<>();

    static {
        
        map.put( BinarySignature.BooleanAndBoolean, ( a, b ) -> new Value( isTrue( a ) && isTrue( b ) ) );
        map.put( BinarySignature.BooleanEqualsBoolean, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinarySignature.BooleanOrBoolean, ( a, b ) -> new Value( isTrue( a ) || isTrue( b ) ) );

        map.put( BinarySignature.DatePlusInteger, ( a, b ) -> new Value( date( a ).plusDays( integer( b ) ) ) );
        map.put( BinarySignature.DateMinusInteger, ( a, b ) -> new Value( date( a ).minusDays( integer( b ) ) ) );
        map.put( BinarySignature.DateLessDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) < 0 ) );
        map.put( BinarySignature.DateMoreDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) < 1 ) );
        map.put( BinarySignature.DateEqualsDate, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinarySignature.DateMoreDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) > 0 ) );
        map.put( BinarySignature.DateNotLessDate, ( a, b ) -> new Value( date( a ).compareTo( date( b ) ) > -1 ) );

        map.put( BinarySignature.IntegerTimesInteger, ( a, b ) -> new Value( (integer( a ) * integer( b )) ) );
        map.put( BinarySignature.IntegerTimesMoney, ( a, b ) -> new Value( money( a ).multiply( money( b ) ) ) );
        map.put( BinarySignature.IntegerPlusInteger, ( a, b ) -> new Value( (integer( a ) + integer( b )) ) );
        map.put( BinarySignature.IntegerMinusInteger, ( a, b ) -> new Value( (integer( a ) - integer( b )) ) );
        map.put( BinarySignature.IntegerDividedByInteger, ( a, b ) -> new Value( (integer( a ) / integer( b )) ) );
        map.put( BinarySignature.IntegerLessInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) < 0 ) );
        map.put( BinarySignature.IntegerNotMoreInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) < 1 ) );
        map.put( BinarySignature.IntegerEqualsInteger, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinarySignature.IntegerMoreInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) > 0 ) );
        map.put( BinarySignature.IntegerNotLessInteger, ( a, b ) -> new Value( integer( a ).compareTo( integer( b ) ) > -1 ) );

        map.put( BinarySignature.MoneyTimesInteger, ( a, b ) -> new Value( money( a ).multiply( money( b ) ) ) );
        map.put( BinarySignature.MoneyPlusMoney, ( a, b ) -> new Value( money( a ).add( money( b ) ) ) );
        map.put( BinarySignature.MoneyMinusMoney, ( a, b ) -> new Value( money( a ).subtract( money( b ) ) ) );
        map.put( BinarySignature.MoneyDividedByInteger, ( a, b ) -> new Value( money( a ).divide( money( b ) ) ) );
        map.put( BinarySignature.MoneyLessMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) < 0 ) );
        map.put( BinarySignature.MoneyNotMoreMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) < 1 ) );
        map.put( BinarySignature.MoneyEqualsMoney, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinarySignature.MoneyMoreMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) > 0 ) );
        map.put( BinarySignature.MoneyNotLessMoney, ( a, b ) -> new Value( money( a ).compareTo( money( b ) ) > -1 ) );

        map.put( BinarySignature.StringPlusInteger, ( a, b ) -> new Value( string(a) + string(b) ) );
        map.put( BinarySignature.StringPlusMoney, ( a, b ) -> new Value( string(a) + string(b) ) );
        map.put( BinarySignature.StringPlusString, ( a, b ) -> new Value( string(a) + string(b) ) );
        map.put( BinarySignature.StringLessString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) < 0 ) );
        map.put( BinarySignature.StringNotMoreString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) < 1 ) );
        map.put( BinarySignature.StringEqualsString, ( a, b ) -> new Value( a.equals( b ) ) );
        map.put( BinarySignature.StringMoreString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) > 0 ) );
        map.put( BinarySignature.StringNotLessString, ( a, b ) -> new Value( string( a ).compareTo( string( b ) ) > -1 ) );

    }

    public static BiFunction<Value, Value, Value> function( BinarySignature function ) {
        return map.get( function );
    }

    // inconsistent name  as 'boolean' is reserved by Java!
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

}