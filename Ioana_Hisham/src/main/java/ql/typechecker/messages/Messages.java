package ql.typechecker.messages;

import ql.typechecker.messages.error.Error;
import ql.typechecker.messages.warning.Warning;

import java.util.ArrayList;
import java.util.List;

public class Messages {
    private final List<Warning> warnings;
    private final List<Error> errors;

    public Messages(){
        warnings = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public void addWarning(Warning warning){
        warnings.add(warning);
    }

    public void addError(Error error) {
        errors.add(error);
    }

    public List<Warning> warnings(){
        return warnings;
    }

    public List<Error> errors() {
        return errors;
    }
}
