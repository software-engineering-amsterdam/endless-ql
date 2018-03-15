package org.uva.sea.gui.model;

import org.uva.sea.languages.QlsInterpreter;
import org.uva.sea.languages.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.exceptions.StaticAnalysisError;

import java.io.IOException;

public class GuiModel {

    private final QlsInterpreter formGenerator;
    private SymbolTable symbolTable;
    private String qlFileName;
    private String qlsFileName;

    public GuiModel(String qlFileName, String qlsFileName) {
        this.formGenerator = new QlsInterpreter();
        this.qlFileName = qlFileName;
        this.qlsFileName = qlsFileName;
        this.symbolTable = new SymbolTable();
    }

    public InterpreterResult getInterpreterResult() throws IOException, StaticAnalysisError, InterruptedException {
        if(this.qlsFileName == null)
            return formGenerator.interpreter(this.qlFileName, symbolTable);

        return formGenerator.interpreter(this.qlFileName, this.qlsFileName, symbolTable);
    }

    public void updateQuestion(String questionName, Value value) {
        symbolTable.addOrUpdateValue(questionName, value);
    }
}
