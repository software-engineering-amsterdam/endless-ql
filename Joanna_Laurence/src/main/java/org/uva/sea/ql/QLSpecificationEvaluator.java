package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.evaluate.ExpressionEvaluator;
import org.uva.sea.ql.evaluate.FormEvaluator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.exceptions.StaticAnalysisError;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.evaluate.valueTypes.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QLSpecificationEvaluator {

    private ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    /**
     * Generates questions with values
     * @param guiSpecification Specification of the GUI
     * @param symbolTable The current state of the program
     * @return List of questions that should be displayed
     */
    public List<QuestionData> generate(String guiSpecification, SymbolTable symbolTable) throws IOException, StaticAnalysisError {
        List<Question> questions = this.getQuestions(guiSpecification, symbolTable);

        List<QuestionData> questionDataList = new ArrayList<>();
        for( Question question : questions) {
            Value value = getQuestionValue(symbolTable, question);
            questionDataList.add(new QuestionData(question, value));
        }

        return questionDataList;
    }


    /**
     * Compute valueTypes or get the valueTypes from the symbol table
     * @param symbolTable
     * @param question
     * @return
     */
    private Value getQuestionValue(SymbolTable symbolTable, Question question) {
        if(question.getValue() != null)
            return this.expressionEvaluator.evaluate(question.getValue(), symbolTable);

        return symbolTable.getValue(question.getVariable().getVariableName());
    }

    /**
     * Generate the GUI
     * @param guiSpecification Specification of the GUI
     */
    private List<Question> getQuestions(String guiSpecification, SymbolTable symbolTable) throws IOException, StaticAnalysisError {
        QLCompiler compiler = new QLCompiler();
        Form rootNode = compiler.compileScriptFile(toCharStream(guiSpecification));
        if(rootNode == null)
            return new ArrayList<>();

        FormEvaluator evaluate = new FormEvaluator();
        return evaluate.evaluate(rootNode, symbolTable);
    }

    /**
     * Convert file name to resource
     * @param fileName The location of the file
     * @return CharStream
     * @throws IOException
     */
    private CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(new FileInputStream(fileName));
    }
}
