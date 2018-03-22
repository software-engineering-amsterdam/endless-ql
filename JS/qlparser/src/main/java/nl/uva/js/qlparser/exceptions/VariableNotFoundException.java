package nl.uva.js.qlparser.exceptions;

public class VariableNotFoundException extends RuntimeException {
    public VariableNotFoundException(String varName, int row, int col) {
        super("Variable not found: " + varName + " on " + row + ":" + col);
    }
}
