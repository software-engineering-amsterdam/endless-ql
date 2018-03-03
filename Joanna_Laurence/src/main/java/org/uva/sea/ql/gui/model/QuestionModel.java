package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.QLSpecificationEvaluator;
import org.uva.sea.ql.dataObject.InterpreterResult;
import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.evaluate.valueTypes.Value;
import org.uva.sea.ql.exceptions.StaticAnalysisError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionModel {

    private final SymbolTable symbolTable;
    private final QLSpecificationEvaluator formGenerator;
    private final String questionFileName;
    private InterpreterResult questions;
    private List<ValueChangeListener> listeners = new ArrayList<>();


    public QuestionModel(String questionFileName) {
        this.formGenerator = new QLSpecificationEvaluator();
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
