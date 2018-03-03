package org.uva.sea.ql.staticAnalysis;

import java.util.ArrayList;
import java.util.List;

public class Messages {
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

    public void clear() {
        this.errors.clear();
    }

    @Override
    public String toString() {
        return errors.toString();
    }
}