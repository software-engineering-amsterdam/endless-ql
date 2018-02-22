package org.uva.sea.ql;

import java.util.List;

public class Error {
    private List<String> errors;

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
