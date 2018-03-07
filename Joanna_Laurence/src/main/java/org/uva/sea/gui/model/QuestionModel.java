package org.uva.sea.gui.model;

import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.ql.interpreter.Interpreter;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionModel {

    private final SymbolTable symbolTable;
    private final Interpreter formGenerator;
    private final String questionFileName;
    private InterpreterResult questions;
    private List<ValueChangeListener> listeners = new ArrayList<>();


    public QuestionModel(String questionFileName) {
        this.formGenerator = new Interpreter();
        this.questionFileName = questionFileName;
        this.symbolTable = new SymbolTable();
        generateQuestions();
    }

    public void attachListener(ValueChangeListener listener) {
        listeners.add(listener);
    }

    public List<QuestionData> getQuestions() {
        return questions.getQuestions();
    }

    public void add(QuestionData questionRow) {
        questions.add(questionRow);
        generateQuestions();
        notifyListeners();
    }

    public void updateQuestion(String questionName, Value value) {
        symbolTable.addOrUpdateValue(questionName, value);
        generateQuestions();
        notifyListeners();
    }

    private void generateQuestions() {
        try {
            questions = formGenerator.generate(getClass().getResource(questionFileName).getFile(), symbolTable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StaticAnalysisError errors) {
            //TODO: handle this error on gui
            errors.printStackTrace();
        }
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
