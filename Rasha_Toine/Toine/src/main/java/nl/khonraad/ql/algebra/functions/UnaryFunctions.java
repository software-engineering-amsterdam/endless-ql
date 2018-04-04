package nl.khonraad.ql.algebra.functions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import nl.khonraad.ql.algebra.values.Value;

public class UnaryFunctions {

    private static Map<UnarySignature, Function<Value, Value>> map = new HashMap<>();

    static {

        map.put( UnarySignature.NotBoolean,     a -> Value.of( !bool( a ) ) );
        map.put( UnarySignature.PlusInteger,    a -> Value.of( integer( a ) ) );
        map.put( UnarySignature.PlusMoney,      a -> Value.of( money( a ) ) );
        map.put( UnarySignature.MinusInteger,   a -> Value.of( -integer( a ) ) );
        map.put( UnarySignature.MinusMoney,     a -> Value.of( money( a ).negate() ) );
    }

    public static Function<Value, Value> function( UnarySignature function ) {
        return map.get( function );
    }

    // inconsistent name as 'boolean' is reserved by Java!
    private static boolean bool( Value value ) {
        return value.equals( Value.True );
    }

    private static Integer integer( Value value ) {
        return Integer.parseInt( value.string() );
    }

    private static BigDecimal money( Value value ) {
        return new BigDecimal( value.string() );
    }
}