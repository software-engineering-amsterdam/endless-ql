package nl.khonraad.ql.algebra.function;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import nl.khonraad.ql.algebra.Value;

public class UnaryFunctions {

    private static Map<UnaryFunction, Function<Value, Value>> functionMap = new HashMap<>();

    static {

        functionMap.put( UnaryFunction.NotBoolean, a -> new Value( !isTrue( a ) ) );
        functionMap.put( UnaryFunction.PlusInteger, a -> new Value( integer( a ) ) );
        functionMap.put( UnaryFunction.PlusMoney, a -> new Value( money( a ) ) );
        functionMap.put( UnaryFunction.MinusInteger, a -> new Value( -integer( a ) ) );
        functionMap.put( UnaryFunction.MinusMoney, a -> new Value( money( a ).negate() ) );
    }

    // consistent name 'boolean' is reserved by Java
    private static boolean isTrue( Value value ) {
        return value.equals( new Value( true ) );
    }

    private static Integer integer( Value value ) {
        return Integer.parseInt( value.string() );
    }

    private static BigDecimal money( Value value ) {
        return new BigDecimal( value.string() );
    }

    public static Function<Value, Value> function( UnaryFunction expression ) {

        return functionMap.get( expression );
    }
}