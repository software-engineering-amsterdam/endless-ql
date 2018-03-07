package org.uva.sea.gui.model;

import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.ql.interpreter.Interpreter;
import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiModel {

    private final Interpreter formGenerator;
    private SymbolTable symbolTable;
    private String qlFileName;
    private InterpreterResult interpreterResult;
    private List<ValueChangeListener> listeners = new ArrayList<>();


    public GuiModel(String qlFileName) throws IOException, StaticAnalysisError {
        this.formGenerator = new Interpreter();
        this.qlFileName = qlFileName;
        this.symbolTable = new SymbolTable();
        generateQuestions();
    }

    public void attachListener(ValueChangeListener listener) {
        listeners.add(listener);
    }

    public List<QuestionData> getQuestions() {
        return interpreterResult.getQuestions();
    }

    public Messages getWarnings() {
        return interpreterResult.getWarnings();
    }

    public InterpreterResult getInterpreterResult() {
        return interpreterResult;
    }

    public void add(QuestionData questionRow) throws IOException, StaticAnalysisError {
        interpreterResult.add(questionRow);
        generateQuestions();
        notifyListeners();
    }

    public void updateQuestion(String questionName, Value value) throws IOException, StaticAnalysisError {
        symbolTable.addOrUpdateValue(questionName, value);
        generateQuestions();
        notifyListeners();
    }

    private void generateQuestions() throws IOException, StaticAnalysisError {
        interpreterResult = formGenerator.generate(qlFileName, symbolTable);
    }

    public void loadNewForm(String newQlFileName) throws IOException, StaticAnalysisError {
        this.qlFileName = newQlFileName;
        this.symbolTable = new SymbolTable();
        interpreterResult = formGenerator.generate(newQlFileName, symbolTable);
        notifyListeners();
    }

    private void notifyListeners() {
        for (ValueChangeListener listener : listeners) {
            listener.onChange();
        }
    }

    public void detachListener(ValueChangeListener listener) {
        listeners.remove(listener);
    }
}
