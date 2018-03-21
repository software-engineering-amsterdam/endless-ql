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

    private ValidationResult(List<String> errors, List<String> warnings) {
        this.errors = errors;
        this.warnings = warnings;
    }

    public void addWarning(String warning) {
        warnings.add(warning);
    }

    public void addError(String error) {
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

    public List<String> getWarnings() {
        return warnings;
    }

    public List<String> getErrors() {
        return errors;
    }

    public ValidationResult merge(ValidationResult validationResult) {
        List<String> errors = new ArrayList<String>(this.errors);
        errors.addAll(validationResult.getErrors());

        List<String> warnings = new ArrayList<String>(this.warnings);
        warnings.addAll(validationResult.getWarnings());

        return new ValidationResult(errors, warnings);
    }
}
