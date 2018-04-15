package ql.validator.checkers;

import ql.issuetracker.Error;
import ql.issuetracker.Warning;

import java.util.List;

public interface Checker {

    /**
     * Returns whether any errors were generated during checker construction
     *
     * @return <code>true</code> if no errors were generated during checker construction
     * <code>false</code> otherwise
     */
    boolean passesTests();

    List<Error> getErrors();

    List<Warning> getWarnings();

    void logErrors();

    void logWarnings();

}