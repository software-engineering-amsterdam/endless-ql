package nl.uva.js.qlparser.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

public class NonNullRun {

    /**
     * Run a consumer when its argument is not null, thus eliminating the need for if (var != null) {...} everywhere
     * @param variable Variable to check if not null
     * @param whenNonNull Action to perform
     * @param <T> The consumer will have to be able to handle the type of the variable argument
     */
    public static <T> void consumer(T variable, Consumer<T> whenNonNull) {
        if (variable != null) {
            whenNonNull.accept(variable);
        }
    }

    /**
     * Run a function when its argument is not null, thus eliminating the need for if (var != null) {...} everywhere
     * @param variable Variable to check if not null
     * @param whenNonNull Action to perform
     * @param <T> The function will have to be able to handle the type of the variable argument
     * @param <R> The return type of the function
     * @return Either the result of the whenNonNull function will be returned, or a null value
     */
    public static <T, R> R function(T variable, Function<T, R> whenNonNull) {
        if (variable != null) {
            return whenNonNull.apply(variable);
        } else return null;
    }
}
