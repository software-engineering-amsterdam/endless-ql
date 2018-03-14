package org.uva.sea;

import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.qls.interpreter.ApplyQLSStyle;
import org.uva.sea.qls.interpreter.Interpreter;
import org.uva.sea.qls.parser.elements.Stylesheet;

import java.io.IOException;

public class MainInterpreter {

    private static org.uva.sea.ql.interpreter.Interpreter qlInterpreter = new org.uva.sea.ql.interpreter.Interpreter();
    private static ApplyQLSStyle.Linker qlQlsLinker = new ApplyQLSStyle.Linker();
    private static org.uva.sea.qls.interpreter.Interpreter qlsInterpreter = new Interpreter();

    /**
     * Generate InterpreterResult
     * @param ql The ql location
     * @param qls The qls location. NULL when you do not want to use QLS
     * @param symbolTable Symbol table for QL
     * @return InterpreterResult
     * @throws StaticAnalysisError
     * @throws InterruptedException
     */
    public InterpreterResult interpreter(String ql, String qls, SymbolTable symbolTable) throws StaticAnalysisError, InterruptedException, IOException {

        InterpreterResult interpreterResult = qlInterpreter.generate(ql, symbolTable);
        if(qls == null)
            return interpreterResult;

        Stylesheet stylesheet = qlsInterpreter.generate("src/main/resources/basic.qls");
        return qlQlsLinker.link(interpreterResult, stylesheet);
    }

}
