package nl.khonraad.ql.algebra.functions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.formatters.SimpleDateFormatter;
import nl.khonraad.ql.algebra.values.Value;

public class BinaryFunctions {

    private static Map<BinarySignature, BiFunction<Value, Value, Value>> map = new HashMap<>();

    static {

        map.put( BinarySignature.BooleanEqualsBoolean,      ( a, b ) -> Value.of( a.equals( b ) ) );
        
        map.put( BinarySignature.BooleanAndBoolean,         ( a, b ) -> Value.of( bool( a ) && bool( b ) ) );
        map.put( BinarySignature.BooleanOrBoolean,          ( a, b ) -> Value.of( bool( a ) || bool( b ) ) );

        map.put( BinarySignature.DatePlusInteger,           ( a, b ) -> Value.of( date( a ).plusDays( integer( b ) ) ) );
        map.put( BinarySignature.DateMinusInteger,          ( a, b ) -> Value.of( date( a ).minusDays( integer( b ) ) ) );
        map.put( BinarySignature.DateLessDate,              ( a, b ) -> Value.of( date( a ).compareTo( date( b ) ) < 0 ) );
        map.put( BinarySignature.DateMoreDate,              ( a, b ) -> Value.of( date( a ).compareTo( date( b ) ) < 1 ) );
        map.put( BinarySignature.DateEqualsDate,            ( a, b ) -> Value.of( date( a ).equals( date( a ) ) ) );
        map.put( BinarySignature.DateMoreDate,              ( a, b ) -> Value.of( date( a ).compareTo( date( b ) ) > 0 ) );
        map.put( BinarySignature.DateNotLessDate,           ( a, b ) -> Value.of( date( a ).compareTo( date( b ) ) > -1 ) );

        map.put( BinarySignature.IntegerTimesInteger,       ( a, b ) -> Value.of( integer( a ) * integer( b )) );
        map.put( BinarySignature.IntegerTimesMoney,         ( a, b ) -> Value.of( money( a ).multiply( money( b ) ) ) );
        map.put( BinarySignature.IntegerPlusInteger,        ( a, b ) -> Value.of( integer( a ) + integer( b )) );
        map.put( BinarySignature.IntegerMinusInteger,       ( a, b ) -> Value.of( integer( a ) - integer( b ))  );
        map.put( BinarySignature.IntegerDividedByInteger,   ( a, b ) -> Value.of( integer( a ) / integer( b ))  );
        map.put( BinarySignature.IntegerLessInteger,        ( a, b ) -> Value.of( integer( a ).compareTo( integer( b ) ) < 0 ) );
        map.put( BinarySignature.IntegerNotMoreInteger,     ( a, b ) -> Value.of( integer( a ).compareTo( integer( b ) ) < 1 ) );
        map.put( BinarySignature.IntegerEqualsInteger,      ( a, b ) -> Value.of( integer( a ).equals( integer( b ) ) ) );
        map.put( BinarySignature.IntegerMoreInteger,        ( a, b ) -> Value.of( integer( a ).compareTo( integer( b ) ) > 0 ) );
        map.put( BinarySignature.IntegerNotLessInteger,     ( a, b ) -> Value.of( integer( a ).compareTo( integer( b ) ) > -1 ) );

        map.put( BinarySignature.MoneyTimesInteger,         ( a, b ) -> Value.of( money( a ).multiply( money( b ) ) ) );
        map.put( BinarySignature.MoneyPlusMoney,            ( a, b ) -> Value.of( money( a ).add( money( b ) ) ) );
        map.put( BinarySignature.MoneyMinusMoney,           ( a, b ) -> Value.of( money( a ).subtract( money( b ) ) ) );
        map.put( BinarySignature.MoneyDividedByInteger,     ( a, b ) -> Value.of( money( a ).divide( money( b ) ) ) );
        map.put( BinarySignature.MoneyLessMoney,            ( a, b ) -> Value.of( money( a ).compareTo( money( b ) ) < 0 ) );
        map.put( BinarySignature.MoneyNotMoreMoney,         ( a, b ) -> Value.of( money( a ).compareTo( money( b ) ) < 1 ) );
        map.put( BinarySignature.MoneyEqualsMoney,          ( a, b ) -> Value.of( money( a ).equals( money( b ) ) ) );
        map.put( BinarySignature.MoneyMoreMoney,            ( a, b ) -> Value.of( money( a ).compareTo( money( b ) ) > 0 ) );
        map.put( BinarySignature.MoneyNotLessMoney,         ( a, b ) -> Value.of( money( a ).compareTo( money( b ) ) > -1 ) );
        
        map.put( BinarySignature.StringPlusInteger,         ( a, b ) -> Value.of( string( a ) + string( b ) ) );
        map.put( BinarySignature.StringPlusMoney,           ( a, b ) -> Value.of( string( a ) + string( b ) ) );
        map.put( BinarySignature.StringPlusString,          ( a, b ) -> Value.of( string( a ) + string( b ) ) );
        map.put( BinarySignature.StringLessString,          ( a, b ) -> Value.of( string( a ).compareTo( string( b ) ) < 0 ) );
        map.put( BinarySignature.StringNotMoreString,       ( a, b ) -> Value.of( string( a ).compareTo( string( b ) ) < 1 ) );
        map.put( BinarySignature.StringEqualsString,        ( a, b ) -> Value.of( string( a ).equals( string( b ) ) ) );
        map.put( BinarySignature.StringMoreString,          ( a, b ) -> Value.of( string( a ).compareTo( string( b ) ) > 0 ) );
        map.put( BinarySignature.StringNotLessString,       ( a, b ) -> Value.of( string( a ).compareTo( string( b ) ) > -1 ) );
    }

    public static BiFunction<Value, Value, Value> function( BinarySignature function ) {
        return map.get( function );
    }

    // inconsistent name as 'boolean' is reserved by Java!
    private static boolean bool( Value value ) {
        return value.equals( Value.True );
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