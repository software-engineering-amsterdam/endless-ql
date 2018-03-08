package org.uva.sea.gui.model;

import org.uva.sea.ql.interpreter.Interpreter;
import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;

import java.io.IOException;

public class GuiModel {

    private final Interpreter formGenerator;
    private SymbolTable symbolTable;
    private String qlFileName;

    public GuiModel(String qlFileName) {
        this.formGenerator = new Interpreter();
        this.qlFileName = qlFileName;
        this.symbolTable = new SymbolTable();
    }

    public InterpreterResult getInterpreterResult() throws IOException, StaticAnalysisError {
        return formGenerator.generate(this.qlFileName, symbolTable);
    }

    public void updateQuestion(String questionName, Value value) {
        symbolTable.addOrUpdateValue(questionName, value);
    }
}
