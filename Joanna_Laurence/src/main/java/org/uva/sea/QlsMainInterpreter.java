package org.uva.sea;

import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.qls.interpreter.ApplyQLSStyle;
import org.uva.sea.qls.interpreter.Interpreter;
import org.uva.sea.qls.parser.elements.Stylesheet;

import java.io.IOException;

public class QlsMainInterpreter extends QlMainInterpreter {
    private ApplyQLSStyle.Linker qlQlsLinker = new ApplyQLSStyle.Linker();
    private org.uva.sea.qls.interpreter.Interpreter qlsInterpreter = new Interpreter();

    /**
     * Generate InterpreterResult
     * @param ql The ql location
     * @param qls The qls location
     * @param symbolTable Symbol table for QL
     * @return InterpreterResult
     * @throws StaticAnalysisError
     * @throws InterruptedException
     */
    public InterpreterResult interpreter(String ql, String qls, SymbolTable symbolTable) throws StaticAnalysisError, InterruptedException, IOException {
        InterpreterResult qlInterpreterResult = this.interpreter(ql, symbolTable);
        Stylesheet stylesheet = qlsInterpreter.generate(qls);
        return qlQlsLinker.link(qlInterpreterResult, stylesheet);
    }
}
