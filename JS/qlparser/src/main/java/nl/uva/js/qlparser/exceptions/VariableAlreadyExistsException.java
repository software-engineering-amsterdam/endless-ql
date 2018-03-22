package nl.uva.js.qlparser.exceptions;

public class VariableAlreadyExistsException extends RuntimeException {
    public VariableAlreadyExistsException(String varName, int row, int col) {
        super("Variable not found: " + varName + " on " + row + ":" + col);
    }
}
