package nl.uva.js.qlparser.helpers;

import wrappers.NumberWrapper;

public class ArithHelpers {
    public static <T extends NumberWrapper>NumberWrapper plus(T a, T b) {
        return a.plus(b);
    }

    public static <T extends NumberWrapper>NumberWrapper min(T a, T b) {
        return a.min(b);
    }

    public static <T extends NumberWrapper>NumberWrapper mult(T a, T b) {
        return a.mult(b);
    }

    public static <T extends NumberWrapper>NumberWrapper div(T a, T b) {
        return a.div(b);
    }
}
