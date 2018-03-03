package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.QLFormGenerator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.exceptions.StaticAnalysisError;
import org.uva.sea.ql.value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionModel {

    private final SymbolTable symbolTable;
    private final QLFormGenerator formGenerator;
    private final String questionFileName;
    private List<QuestionData> questions = new ArrayList<>();
    private List<ValueChangeListener> listeners = new ArrayList<>();


    public QuestionModel(String questionFileName) {
        this.formGenerator = new QLFormGenerator();
        this.questionFileName = questionFileName;
        this.symbolTable = new SymbolTable();
        generateQuestions();
    }

    public void attachListener(ValueChangeListener listener) {
        listeners.add(listener);
    }

    public List<QuestionData> getQuestions() {
        return questions;
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
