package org.uva.ql.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private List<String> errors;
    private List<String> warnings;

    public ValidationResult() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    public void addWarning(String warning){
        warnings.add(warning);
    }

    public void addError(String error){
        errors.add(error);
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public boolean hasWarnings() {
        return warnings.size() > 0;
    }

    @Override
    public String toString() {
        errors.addAll(warnings);
        return errors.toString();
    }
}
