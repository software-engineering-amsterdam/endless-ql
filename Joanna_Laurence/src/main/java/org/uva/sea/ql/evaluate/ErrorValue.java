package org.uva.sea.ql.evaluate;

public class ErrorValue extends Value {
    private String error;

    public ErrorValue(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
