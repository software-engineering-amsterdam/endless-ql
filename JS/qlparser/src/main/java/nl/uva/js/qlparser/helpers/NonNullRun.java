package nl.uva.js.qlparser.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

public class NonNullRun {

    public static <T> void consumer(T variable, Consumer<T> whenNonNull) {
        if (variable != null) {
            whenNonNull.accept(variable);
        }
    }

    public static <T, R> R function(T variable, Function<T, R> whenNonNull) {
        if (variable != null) {
            return whenNonNull.apply(variable);
        } else return null;
    }
}
