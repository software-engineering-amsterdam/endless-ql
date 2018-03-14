package nl.uva.js.qlparser.helpers;

import nl.uva.js.qlparser.wrappers.arithmetic.Calculatable;

public class ArithHelpers {
    public static <T extends Calculatable>Calculatable plus(T a, T b) {
        return a.plus(b);
    }

    public static <T extends Calculatable>Calculatable min(T a, T b) {
        return a.min(b);
    }

    public static <T extends Calculatable>Calculatable mult(T a, T b) {
        return a.mult(b);
    }

    public static <T extends Calculatable>Calculatable div(T a, T b) {
        return a.div(b);
    }
}
