package QL.parsing.checkers;

import QL.parsing.gen.QLParser;

public class Checks {
    public static void checkForm(QLParser.FormContext form) {
        new VariableChecker().checkForm(form);
        new CyclicChecker().checkForm(form);
        new TypeChecker().checkForm(form);
    }
}
