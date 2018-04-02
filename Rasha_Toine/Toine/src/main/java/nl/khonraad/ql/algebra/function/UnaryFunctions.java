package nl.khonraad.ql.algebra.function;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import nl.khonraad.ql.algebra.value.Value;

public class UnaryFunctions {

    private static Map<UnarySignature, Function<Value, Value>> map = new HashMap<>();

    static {

        map.put( UnarySignature.NotBoolean, a -> new Value( !isTrue( a ) ) );
        map.put( UnarySignature.PlusInteger, a -> new Value( integer( a ) ) );
        map.put( UnarySignature.PlusMoney, a -> new Value( money( a ) ) );
        map.put( UnarySignature.MinusInteger, a -> new Value( -integer( a ) ) );
        map.put( UnarySignature.MinusMoney, a -> new Value( money( a ).negate() ) );
    }

    public static Function<Value, Value> function( UnarySignature function ) {
        return map.get( function );
    }

    // inconsistent name  as 'boolean' is reserved by Java!
    private static boolean isTrue( Value value ) {
        return value.equals( new Value( true ) );
    }

    private static Integer integer( Value value ) {
        return Integer.parseInt( value.string() );
    }

    private static BigDecimal money( Value value ) {
        return new BigDecimal( value.string() );
    }
}