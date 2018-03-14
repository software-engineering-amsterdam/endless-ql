package org.uva.sea;

import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;

import java.io.IOException;

public abstract class QlMainInterpreter {

    private org.uva.sea.ql.interpreter.Interpreter qlInterpreter = new org.uva.sea.ql.interpreter.Interpreter();

    /**
     * Generate InterpreterResult
     * @param ql The ql location
     * @param symbolTable Symbol table for QL
     * @return InterpreterResult
     * @throws StaticAnalysisError
     * @throws InterruptedException
     */
    public InterpreterResult interpreter(String ql, SymbolTable symbolTable) throws StaticAnalysisError, InterruptedException, IOException {
        return qlInterpreter.generate(ql, symbolTable);
    }

}
