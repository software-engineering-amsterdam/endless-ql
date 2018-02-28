package org.uva.sea.ql;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        this.errors.add(error);
    }

    public boolean errorsPresent() {
        return !this.errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }
}