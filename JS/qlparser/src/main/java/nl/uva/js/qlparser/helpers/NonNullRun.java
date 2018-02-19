package nl.uva.js.qlparser.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

public class NonNullRun {

    public static <T> void consumer(T variable, Consumer<T> f) {
        if (variable != null) {
            f.accept(variable);
        }
    }

    public static <T, R> R function(T variable, Function<T, R> f) {
        if (variable != null) {
            return f.apply(variable);
        } else return null;
    }
}
