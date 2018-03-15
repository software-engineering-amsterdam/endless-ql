package org.uva.sea.languages;

import org.uva.sea.languages.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.exceptions.StaticAnalysisError;

import java.io.IOException;

/**
 * Converts QL file to a list of questions
 */
public abstract class QlQuestionEvaluator {

    private org.uva.sea.languages.ql.interpreter.Interpreter qlInterpreter = new org.uva.sea.languages.ql.interpreter.Interpreter();

    private SymbolTable symbolTable = new SymbolTable();

    private String qlFileLocation;

    /**
     * Constructor
     * @param fileLocation
     */
    public QlQuestionEvaluator(String fileLocation) {
        this.qlFileLocation = fileLocation;
    }

    /**
     * Generate InterpreterResult
     * @return InterpreterResult
     * @throws StaticAnalysisError
     * @throws InterruptedException
     */
    public InterpreterResult getQuestions() throws StaticAnalysisError, IOException {
        return qlInterpreter.generate(this.qlFileLocation, this.symbolTable);
    }

    /**
     *
     * @param name Name of the variable
     * @param value Value of the variable
     */
    public void setVariable(String name, Value value) {
        this.symbolTable.addOrUpdateValue(name, value);
    }
}
